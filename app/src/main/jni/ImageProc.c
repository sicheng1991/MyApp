#include "ImageProc.h"
//01-24 07:43:06.439: E/TEST(3756): VIDIOC_DQBUF error 22, Invalid argument

int idx = 0;
int errnoexit(const char *s) {
    LOGI("%s error %d, %s", s, errno, strerror (errno));
    return ERROR_LOCAL;
}

int xioctl(int fd, int request, void *arg) {
    int r;

    do {
        r = ioctl (fd, request, arg);
    } while (-1 == r && EINTR == errno);

    return r;
}

//检查video设备名称
int checkCameraBase(void) {
    struct stat st;
    int i;
    int start_from_4 = 1;
    
    /* if /dev/video[0-3] exist, camerabase=4, otherwise, camrerabase = 0 */
    for (i=0 ; i<4 ; i++) {
        sprintf(dev_name, "/dev/video%d", i);
        if (-1 == stat (dev_name, &st)) {
            start_from_4 &= 0;
        } else {
            start_from_4 &= 1;
        }
    }

    if (start_from_4) {
        return 4;
    } else {
        return 0;
    }
}

//打开video设备
int openDevice(int i) {
    struct stat st;
    sprintf(dev_name,"/dev/video%d",i);
    //stat() 获得文件属性，并判断是否为字符设备文件
    if (-1 == stat (dev_name, &st)) {
        LOGI("Cannot identify '%s': %d, %s", dev_name, errno, strerror (errno));
        return ERROR_LOCAL;
    }

    if (!S_ISCHR (st.st_mode)) {
        LOGI("%s is no device", dev_name);
        return ERROR_LOCAL;
    }

    fd = open (dev_name, O_RDWR);

    if (-1 == fd) {
        LOGI("Cannot open '%s': %d, %s", dev_name, errno, strerror (errno));
        return ERROR_LOCAL;
    }
    return SUCCESS_LOCAL;
}

//初始化设备
int initDevice(void) 
{
    struct v4l2_capability cap;
    struct v4l2_cropcap cropcap;
    struct v4l2_crop crop;
    struct v4l2_format fmt;
    unsigned int min;
    
    //VIDIOC_QUERYCAP 命令 来获得当前设备的各个属性
    if (-1 == xioctl (fd, VIDIOC_QUERYCAP, &cap)) {
        if (EINVAL == errno) {
            LOGI("%s is no V4L2 device", dev_name);
            return ERROR_LOCAL;
        } else {
            return errnoexit ("VIDIOC_QUERYCAP");
        }
    }
    
    //V4L2_CAP_VIDEO_CAPTURE 0x00000001
    // 这个设备支持 video capture 的接口，即这个设备具备 video capture 的功能
    if (!(cap.capabilities & V4L2_CAP_VIDEO_CAPTURE)) {
        LOGI("%s is no video capture device", dev_name);
        return ERROR_LOCAL;
    }
    
    //V4L2_CAP_STREAMING 0x04000000
    // 这个设备是否支持 streaming I/O 操作函数
    if (!(cap.capabilities & V4L2_CAP_STREAMING)) {
        LOGI("%s does not support streaming i/o", dev_name);
        return ERROR_LOCAL;
    }
    //获得设备对 Image Cropping 和 Scaling 的支持
    CLEAR (cropcap);

    cropcap.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    /*
    if (0 == xioctl (fd, VIDIOC_CROPCAP, &cropcap)) {
        crop.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        crop.c = cropcap.defrect; 

        if (-1 == xioctl (fd, VIDIOC_S_CROP, &crop)) {
            switch (errno) {
                case EINVAL:
                    break;
                default:
                    break;
            }
        }
    } else {
    }
    */
    
    //设置图形格式
    CLEAR (fmt);

    fmt.type                = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    fmt.fmt.pix.width       = IMG_WIDTH; 
    fmt.fmt.pix.height      = IMG_HEIGHT;

    fmt.fmt.pix.pixelformat = V4L2_PIX_FMT_MJPEG;
    fmt.fmt.pix.field       = V4L2_FIELD_INTERLACED;
    //检查流权限
    if (-1 == xioctl (fd, VIDIOC_S_FMT, &fmt)) {
		LOGD("ImageProc VIDIOC_S_FMT FAILED!!");
        return errnoexit ("VIDIOC_S_FMT");
    }

    min = fmt.fmt.pix.width * 2;
    //每行像素所占的 byte 数
    if (fmt.fmt.pix.bytesperline < min) {
        fmt.fmt.pix.bytesperline = min;
    }
    min = fmt.fmt.pix.bytesperline * fmt.fmt.pix.height;
    if (fmt.fmt.pix.sizeimage < min) {
        fmt.fmt.pix.sizeimage = min;
    }

    return initMMap();

}

//I/O模式选择
int initMMap(void) {
    struct v4l2_requestbuffers req;

    CLEAR (req);

    req.count   = 4;
    req.type    = V4L2_BUF_TYPE_VIDEO_CAPTURE;
    req.memory  = V4L2_MEMORY_MMAP;

    if (-1 == xioctl (fd, VIDIOC_REQBUFS, &req)) {
        if (EINVAL == errno) {
            LOGI("%s does not support memory mapping", dev_name);
            return ERROR_LOCAL;
        } else {
            return errnoexit ("VIDIOC_REQBUFS");
        }
    }

    if (req.count < 2) {
        LOGI("Insufficient buffer memory on %s", dev_name);
        return ERROR_LOCAL;
     }

    buffers = calloc (req.count, sizeof (*buffers));

    if (!buffers) {
        LOGI("Out of memory");
        return ERROR_LOCAL;
    }

    for (n_buffers = 0; n_buffers < req.count; ++n_buffers) {
        struct v4l2_buffer buf;

        CLEAR (buf);

        buf.type        = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        buf.memory      = V4L2_MEMORY_MMAP;
        buf.index       = n_buffers;

        if (-1 == xioctl (fd, VIDIOC_QUERYBUF, &buf)) {
			LOGD("ImageProc VIDIOC_QUERYBUF_FAILED!!");
            return errnoexit ("VIDIOC_QUERYBUF");
        }
        
        buffers[n_buffers].length = buf.length;
        buffers[n_buffers].start =
        mmap (NULL ,
            buf.length,
            PROT_READ | PROT_WRITE,
            MAP_SHARED,
            fd, buf.m.offset);

        if (MAP_FAILED == buffers[n_buffers].start) {
			 LOGD("ImageProc MAP_FAILED!!");
            return errnoexit ("mmap");
        }
    }

    return SUCCESS_LOCAL;
}

int startCapturing(void) {
    unsigned int i;
    enum v4l2_buf_type type;

    for (i = 0; i < n_buffers; ++i) {
        struct v4l2_buffer buf;

        CLEAR (buf);

        buf.type        = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        buf.memory      = V4L2_MEMORY_MMAP;
        buf.index       = i;

        if (-1 == xioctl (fd, VIDIOC_QBUF, &buf)) {
            return errnoexit ("VIDIOC_QBUF");
        }
    }

    type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    if (-1 == xioctl (fd, VIDIOC_STREAMON, &type)) {
        return errnoexit ("VIDIOC_STREAMON");
    }

    return SUCCESS_LOCAL;
}

int stopCapturing(void) {
    enum v4l2_buf_type type;

    type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    if (-1 == xioctl (fd, VIDIOC_STREAMOFF, &type)) {
        return errnoexit ("VIDIOC_STREAMOFF");
    }
    
    return SUCCESS_LOCAL;
}

int deinitDevice(void) {
    unsigned int i;
	LOGD("ImageProc deinitDevice buffers=0x%x!!",buffers);
	if(buffers)
	{
		for (i = 0; i < n_buffers; ++i) {
			LOGD("ImageProc deinitDevice buffers[i].start=0x%x!!",buffers[i].start);
			if(buffers[i].start && (buffers[i].start != MAP_FAILED)){
				if (-1 == munmap (buffers[i].start, buffers[i].length)) {
					return errnoexit ("munmap");
				}
			}
		}
		free (buffers);
	}
    return SUCCESS_LOCAL;
}

//关闭设备
int closeDevice(void) {
    if (-1 == close (fd)){
        fd = -1;
		LOGD("ImageProc closeDevice failed!!");
        return errnoexit ("close");
    }

    fd = -1;
    return SUCCESS_LOCAL;
}

JNIEXPORT jint JNICALL Java_com_chimu_myapp_common_ImageProc_CaptureCamera( JNIEnv* env, jobject thiz)
{
	int length = 0;
	unsigned int i=0;
	struct v4l2_buffer buf;
	fd_set fds;
	struct timeval tv;
	int r;
	int savefd=-1;
	int ret;
	char path[64];

    ret = openDevice(0);
	LOGD("ImageProc openDevice ret =  %d",ret);

	if (ret != ERROR_LOCAL)
	{
        ret = initDevice();
		LOGD("ImageProc initDevice ret =  %d",ret);
    }
	else
	{
		LOGD("ImageProc openDevice failure ret =  %d",ret);
		return ERROR_LOCAL;
	}


    if (ret != ERROR_LOCAL)
	{
		LOGD("ImageProc startCapturing");
        ret = startCapturing();
		LOGD("ImageProc startCapturing ret =  %d",ret);

        if (ret != SUCCESS_LOCAL)
		{
			LOGD("ImageProc startCapturing failure,close device!! ret =  %d",ret);
            stopCapturing();
            deinitDevice ();
            closeDevice ();
            LOGD("device resetted");
			return ERROR_LOCAL;
        }
    }
	else
	{
		LOGD("ImageProc initDevice failure,close device!! ret =  %d",ret);
		deinitDevice ();
		closeDevice ();
		return ERROR_LOCAL;
	}


	for(;;)
	{
		FD_ZERO (&fds);
		FD_SET (fd, &fds);

		tv.tv_sec = 2;
		tv.tv_usec = 0;
		LOGD("ImageProc select start!!");
		r = select (fd + 1, &fds, NULL, NULL, &tv);
		LOGD("ImageProc select stop!!");
		if (-1 == r) {
			if (EINTR == errno) {
				LOGD("ImageProc select EINTR!!");
				break;
			}
			LOGD("ImageProc select failed!!");
			//return ERROR_LOCAL;
			break;
		}

		if (0 == r) {
			LOGD("ImageProc select timeout!!");
			//return ERROR_LOCAL;
			break;
		}

		LOGD("ImageProc readFrameOnce r =  %d",r);

		CLEAR (buf);

		buf.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;
		buf.memory = V4L2_MEMORY_MMAP;
		//buf.memory = V4L2_MEMORY_USERPTR;
			//LOGD("fd=%d,request=%d,buf=%d",fd,VIDIOC_DQBUF,&buf);
		if (-1 == xioctl (fd, VIDIOC_DQBUF, &buf)) {
			LOGD("ImageProc VIDIOC_DQBUF failed!!");
			//return ERROR_LOCAL;
			break;
		}

		assert (buf.index < n_buffers);

		for( i = 0; i < buffers[buf.index].length; i++)
		{
			if((buffers[buf.index].start[i] == 0xff) && (buffers[buf.index].start[i + 1] == 0xd9)) //检测MJPEG帧数据结束标志,存储图像
			{
				length = i + 2;
				break;
			}
		}

		if (-1 == xioctl (fd, VIDIOC_QBUF, &buf)) {
			LOGD("ImageProc readFrame VIDIOC_QBUF failed!!");
			//return ERROR_LOCAL;
			break;
		}

		LOGE("length:%d!\n",length);
		if(length<=0)
			continue;
		else
		{
			if(access("/sdcard/cap1.jpg", F_OK) == 0)
			{
				system("rm /sdcard/cap1.jpg");
				LOGD("rm /sdcard/cap1.jpg\n");
			}
			sprintf(path, "/sdcard/cap1%d.jpg", idx);
			savefd = open(path, O_RDWR | O_CREAT, S_IRWXU|S_IRWXG|S_IRWXO);
			if(savefd < 0)
			{
				LOGD("failed to create cap1.jpg:%d!\n",errno);
			}
			else
			{
				write(savefd, (char *)buffers[buf.index].start, length);
				close(savefd);
			}
			//LOGE("buffers[buf.index].start[0]:%x,buffers[buf.index].start[length-1]:%x\n",buffers[buf.index].start[0],buffers[buf.index].start[length-1]);
			break;
		}
	}

	stopCapturing ();
    deinitDevice ();
    closeDevice ();
    fd = -1;

	return 0;
}


int openDevice2(int i) {
    struct stat st;
    sprintf(dev_name2,"/dev/video%d",i);
    //stat() 获得文件属性，并判断是否为字符设备文件
    if (-1 == stat (dev_name2, &st)) {
        LOGI("2Cannot identify '%s': %d, %s", dev_name2, errno, strerror (errno));
        return ERROR_LOCAL;
    }

    if (!S_ISCHR (st.st_mode)) {
        LOGI("2 %s is no device", dev_name2);
        return ERROR_LOCAL;
    }

    fd2 = open (dev_name2, O_RDWR);

    if (-1 == fd2) {
        LOGI("2 Cannot open '%s': %d, %s", dev_name2, errno, strerror (errno));
        return ERROR_LOCAL;
    }
    return SUCCESS_LOCAL;
}

int initDevice2(void) 
{
    struct v4l2_capability cap;
    struct v4l2_cropcap cropcap;
    struct v4l2_crop crop;
    struct v4l2_format fmt;
    unsigned int min;
    
    //VIDIOC_QUERYCAP 命令 来获得当前设备的各个属性
    if (-1 == xioctl (fd2, VIDIOC_QUERYCAP, &cap)) {
        if (EINVAL == errno) {
            LOGI("2 %s is no V4L2 device", dev_name2);
            return ERROR_LOCAL;
        } else {
            return errnoexit ("VIDIOC_QUERYCAP");
        }
    }
    
    //V4L2_CAP_VIDEO_CAPTURE 0x00000001
    // 这个设备支持 video capture 的接口，即这个设备具备 video capture 的功能
    if (!(cap.capabilities & V4L2_CAP_VIDEO_CAPTURE)) {
        LOGI("2 %s is no video capture device", dev_name2);
        return ERROR_LOCAL;
    }
    
    //V4L2_CAP_STREAMING 0x04000000
    // 这个设备是否支持 streaming I/O 操作函数
    if (!(cap.capabilities & V4L2_CAP_STREAMING)) {
        LOGI("2 %s does not support streaming i/o", dev_name2);
        return ERROR_LOCAL;
    }
    //获得设备对 Image Cropping 和 Scaling 的支持
    CLEAR (cropcap);

    cropcap.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    
    //设置图形格式
    CLEAR (fmt);

    fmt.type                = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    fmt.fmt.pix.width       = IMG_WIDTH; 
    fmt.fmt.pix.height      = IMG_HEIGHT;

    fmt.fmt.pix.pixelformat = V4L2_PIX_FMT_MJPEG;
    fmt.fmt.pix.field       = V4L2_FIELD_INTERLACED;
    //检查流权限
    if (-1 == xioctl (fd2, VIDIOC_S_FMT, &fmt)) {
		LOGD("2 ImageProc VIDIOC_S_FMT FAILED!!");
        return errnoexit ("VIDIOC_S_FMT");
    }

    min = fmt.fmt.pix.width * 2;
    //每行像素所占的 byte 数
    if (fmt.fmt.pix.bytesperline < min) {
        fmt.fmt.pix.bytesperline = min;
    }
    min = fmt.fmt.pix.bytesperline * fmt.fmt.pix.height;
    if (fmt.fmt.pix.sizeimage < min) {
        fmt.fmt.pix.sizeimage = min;
    }

    return initMMap2();

}

int initMMap2(void) {
    struct v4l2_requestbuffers req;

    CLEAR (req);

    req.count   = 4;
    req.type    = V4L2_BUF_TYPE_VIDEO_CAPTURE;
    req.memory  = V4L2_MEMORY_MMAP;

    if (-1 == xioctl (fd2, VIDIOC_REQBUFS, &req)) {
        if (EINVAL == errno) {
            LOGI("2 %s does not support memory mapping", dev_name2);
            return ERROR_LOCAL;
        } else {
            return errnoexit ("VIDIOC_REQBUFS");
        }
    }

    if (req.count < 2) {
        LOGI("2 Insufficient buffer memory on %s", dev_name2);
        return ERROR_LOCAL;
     }

    buffers2 = calloc (req.count, sizeof (*buffers2));

    if (!buffers2) {
        LOGI("2 Out of memory");
        return ERROR_LOCAL;
    }

    for (n_buffers2 = 0; n_buffers2 < req.count; ++n_buffers2) {
        struct v4l2_buffer buf;

        CLEAR (buf);

        buf.type        = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        buf.memory      = V4L2_MEMORY_MMAP;
        buf.index       = n_buffers2;

        if (-1 == xioctl (fd2, VIDIOC_QUERYBUF, &buf)) {
			LOGD("2 ImageProc 2VIDIOC_QUERYBUF_FAILED!!");
            return errnoexit ("VIDIOC_QUERYBUF");
        }
        
        buffers2[n_buffers2].length = buf.length;
        buffers2[n_buffers2].start =
        mmap (NULL ,
            buf.length,
            PROT_READ | PROT_WRITE,
            MAP_SHARED,
            fd2, buf.m.offset);

        if (MAP_FAILED == buffers2[n_buffers2].start) {
			 LOGD("2 ImageProc 2MAP_FAILED!!");
            return errnoexit ("mmap");
        }
    }

    return SUCCESS_LOCAL;
}

int startCapturing2(void) {
    unsigned int i;
    enum v4l2_buf_type type;

    for (i = 0; i < n_buffers2; ++i) {
        struct v4l2_buffer buf;

        CLEAR (buf);

        buf.type        = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        buf.memory      = V4L2_MEMORY_MMAP;
        buf.index       = i;

        if (-1 == xioctl (fd2, VIDIOC_QBUF, &buf)) {
            return errnoexit ("VIDIOC_QBUF");
        }
    }

    type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    if (-1 == xioctl (fd2, VIDIOC_STREAMON, &type)) {
        return errnoexit ("VIDIOC_STREAMON");
    }

    return SUCCESS_LOCAL;
}

int stopCapturing2(void) {
    enum v4l2_buf_type type;

    type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    if (-1 == xioctl (fd2, VIDIOC_STREAMOFF, &type)) {
        return errnoexit ("VIDIOC_STREAMOFF");
    }
    
    return SUCCESS_LOCAL;
}

int deinitDevice2(void) {
    unsigned int i;
	LOGD("2 ImageProc deinitDevice2 buffers=0x%x!!",buffers2);
	if(buffers2)
	{
		for (i = 0; i < n_buffers2; ++i) {
			LOGD("2 ImageProc deinitDevice2 buffers[i].start=0x%x!!",buffers2[i].start);
			if(buffers2[i].start && (buffers2[i].start != MAP_FAILED)){
				if (-1 == munmap (buffers2[i].start, buffers2[i].length)) {
					return errnoexit ("munmap");
				}
			}
		}
		free (buffers2);
	}
    return SUCCESS_LOCAL;
}

//关闭设备
int closeDevice2(void) {
    if (-1 == close (fd2)){
        fd2 = -1;
		LOGD("2 ImageProc closeDevice failed!!");
        return errnoexit ("close");
    }

    fd2 = -1;
    return SUCCESS_LOCAL;
}


JNIEXPORT jint JNICALL Java_com_asiatelco_webcamera_CapActivity_CaptureCamera2( JNIEnv* env, jobject thiz)
{
	int length = 0;
	unsigned int i=0;
	struct v4l2_buffer buf;
	fd_set fds;
	struct timeval tv;
	int r;
	int savefd=-1;
	int ret;
	char path[64];

    ret = openDevice2(2);
	LOGD("2 ImageProc openDevice ret =  %d",ret);

	if (ret != ERROR_LOCAL)
	{
        ret = initDevice2();
		LOGD("2 ImageProc initDevice ret =  %d",ret);
    }
	else
	{
		LOGD("2 ImageProc openDevice failure ret =  %d",ret);
		return ERROR_LOCAL;
	}


    if (ret != ERROR_LOCAL)
	{
		LOGD("2 ImageProc startCapturing");
        ret = startCapturing2();
		LOGD("2 ImageProc startCapturing ret =  %d",ret);

        if (ret != SUCCESS_LOCAL)
		{
			LOGD("2 ImageProc startCapturing failure,close device!! ret =  %d",ret);
            stopCapturing2();
            deinitDevice2 ();
            closeDevice2 ();
            LOGD("2 device resetted");
			return ERROR_LOCAL;
        }
    }
	else
	{
		LOGD("2 ImageProc initDevice failure,close device!! ret =  %d",ret);
		deinitDevice2 ();
		closeDevice2 ();
		return ERROR_LOCAL;
	}


	for(;;)
	{
		FD_ZERO (&fds);
		FD_SET (fd2, &fds);

		tv.tv_sec = 2;
		tv.tv_usec = 0;
		LOGD("2 ImageProc select start!!");
		r = select (fd2 + 1, &fds, NULL, NULL, &tv);
		LOGD("2 ImageProc select stop!!");
		if (-1 == r) {
			if (EINTR == errno) {
				LOGD("2 ImageProc select EINTR!!");
				break;
			}
			LOGD("2 ImageProc select failed!!");
			//return ERROR_LOCAL;
			break;
		}

		if (0 == r) {
			LOGD("2 ImageProc select timeout!!");
			//return ERROR_LOCAL;
			break;
		}

		LOGD("2 ImageProc readFrameOnce r =  %d",r);

		CLEAR (buf);

		buf.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;
		buf.memory = V4L2_MEMORY_MMAP;
		//buf.memory = V4L2_MEMORY_USERPTR;
			//LOGD("fd=%d,request=%d,buf=%d",fd,VIDIOC_DQBUF,&buf);
		if (-1 == xioctl (fd2 , VIDIOC_DQBUF, &buf)) {
			LOGD("2 ImageProc VIDIOC_DQBUF failed!!");
			//return ERROR_LOCAL;
			break;
		}

		assert (buf.index < n_buffers2);

		for( i = 0; i < buffers2[buf.index].length; i++)
		{
			if((buffers2[buf.index].start[i] == 0xff) && (buffers2[buf.index].start[i + 1] == 0xd9)) //检测MJPEG帧数据结束标志,存储图像
			{
				length = i + 2;
				break;
			}
		}

		if (-1 == xioctl (fd2, VIDIOC_QBUF, &buf)) {
			LOGD("2 ImageProc readFrame VIDIOC_QBUF failed!!");
			//return ERROR_LOCAL;
			break;
		}

		LOGE("2 length:%d!\n",length);
		if(length<=0)
			continue;
		else
		{
			if(access("/sdcard/cap2.jpg", F_OK) == 0)
			{
				system("rm /sdcard/cap2.jpg");
				LOGD("2 rm /sdcard/cap2.jpg\n");
			}
			sprintf(path, "/sdcard/cap2%d.jpg", idx);
			savefd = open(path, O_RDWR | O_CREAT, S_IRWXU|S_IRWXG|S_IRWXO);
			if(savefd < 0)
			{
				LOGD("2 failed to create cap2.jpg:%d!\n",errno);
			}
			else
			{
				write(savefd, (char *)buffers2[buf.index].start, length);
				close(savefd);
			}
			//LOGE("buffers[buf.index].start[0]:%x,buffers[buf.index].start[length-1]:%x\n",buffers[buf.index].start[0],buffers[buf.index].start[length-1]);
			break;
		}
	}

	stopCapturing2 ();
    deinitDevice2 ();
    closeDevice2 ();
    fd2 = -1;

	return 0;
}


int openDevice3(int i) {
    struct stat st;
    sprintf(dev_name3,"/dev/video%d",i);
    //stat() 获得文件属性，并判断是否为字符设备文件
    if (-1 == stat (dev_name3, &st)) {
        LOGI("3Cannot identify '%s': %d, %s", dev_name3, errno, strerror (errno));
        return ERROR_LOCAL;
    }

    if (!S_ISCHR (st.st_mode)) {
        LOGI("3 %s is no device", dev_name3);
        return ERROR_LOCAL;
    }

    fd3 = open (dev_name3, O_RDWR);

    if (-1 == fd3) {
        LOGI("3 Cannot open '%s': %d, %s", dev_name3, errno, strerror (errno));
        return ERROR_LOCAL;
    }
    return SUCCESS_LOCAL;
}

int initDevice3(void) 
{
    struct v4l2_capability cap;
    struct v4l2_cropcap cropcap;
    struct v4l2_crop crop;
    struct v4l2_format fmt;
    unsigned int min;
    
    //VIDIOC_QUERYCAP 命令 来获得当前设备的各个属性
    if (-1 == xioctl (fd3, VIDIOC_QUERYCAP, &cap)) {
        if (EINVAL == errno) {
            LOGI("3 %s is no V4L2 device", dev_name3);
            return ERROR_LOCAL;
        } else {
            return errnoexit ("VIDIOC_QUERYCAP");
        }
    }
    
    //V4L2_CAP_VIDEO_CAPTURE 0x00000001
    // 这个设备支持 video capture 的接口，即这个设备具备 video capture 的功能
    if (!(cap.capabilities & V4L2_CAP_VIDEO_CAPTURE)) {
        LOGI("3 %s is no video capture device", dev_name3);
        return ERROR_LOCAL;
    }
    
    //V4L2_CAP_STREAMING 0x04000000
    // 这个设备是否支持 streaming I/O 操作函数
    if (!(cap.capabilities & V4L2_CAP_STREAMING)) {
        LOGI("3 %s does not support streaming i/o", dev_name3);
        return ERROR_LOCAL;
    }
    //获得设备对 Image Cropping 和 Scaling 的支持
    CLEAR (cropcap);

    cropcap.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    
    //设置图形格式
    CLEAR (fmt);

    fmt.type                = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    fmt.fmt.pix.width       = IMG_WIDTH; 
    fmt.fmt.pix.height      = IMG_HEIGHT;

    fmt.fmt.pix.pixelformat = V4L2_PIX_FMT_MJPEG;
    fmt.fmt.pix.field       = V4L2_FIELD_INTERLACED;
    //检查流权限
    if (-1 == xioctl (fd3, VIDIOC_S_FMT, &fmt)) {
		LOGD("3 ImageProc VIDIOC_S_FMT FAILED!!");
        return errnoexit ("VIDIOC_S_FMT");
    }

    min = fmt.fmt.pix.width * 2;
    //每行像素所占的 byte 数
    if (fmt.fmt.pix.bytesperline < min) {
        fmt.fmt.pix.bytesperline = min;
    }
    min = fmt.fmt.pix.bytesperline * fmt.fmt.pix.height;
    if (fmt.fmt.pix.sizeimage < min) {
        fmt.fmt.pix.sizeimage = min;
    }

    return initMMap3();

}

int initMMap3(void) {
    struct v4l2_requestbuffers req;

    CLEAR (req);

    req.count   = 4;
    req.type    = V4L2_BUF_TYPE_VIDEO_CAPTURE;
    req.memory  = V4L2_MEMORY_MMAP;

    if (-1 == xioctl (fd3, VIDIOC_REQBUFS, &req)) {
        if (EINVAL == errno) {
            LOGI("3 %s does not support memory mapping", dev_name3);
            return ERROR_LOCAL;
        } else {
            return errnoexit ("VIDIOC_REQBUFS");
        }
    }

    if (req.count < 2) {
        LOGI("3 Insufficient buffer memory on %s", dev_name3);
        return ERROR_LOCAL;
     }

    buffers3 = calloc (req.count, sizeof (*buffers3));

    if (!buffers3) {
        LOGI("3 Out of memory");
        return ERROR_LOCAL;
    }

    for (n_buffers3 = 0; n_buffers3 < req.count; ++n_buffers3) {
        struct v4l2_buffer buf;

        CLEAR (buf);

        buf.type        = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        buf.memory      = V4L2_MEMORY_MMAP;
        buf.index       = n_buffers3;

        if (-1 == xioctl (fd3, VIDIOC_QUERYBUF, &buf)) {
			LOGD("3 ImageProc 2VIDIOC_QUERYBUF_FAILED!!");
            return errnoexit ("VIDIOC_QUERYBUF");
        }
        
        buffers3[n_buffers3].length = buf.length;
        buffers3[n_buffers3].start =
        mmap (NULL ,
            buf.length,
            PROT_READ | PROT_WRITE,
            MAP_SHARED,
            fd3, buf.m.offset);

        if (MAP_FAILED == buffers3[n_buffers3].start) {
			 LOGD("3 ImageProc 2MAP_FAILED!!");
            return errnoexit ("mmap");
        }
    }

    return SUCCESS_LOCAL;
}

int startCapturing3(void) {
    unsigned int i;
    enum v4l2_buf_type type;

    for (i = 0; i < n_buffers3; ++i) {
        struct v4l2_buffer buf;

        CLEAR (buf);

        buf.type        = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        buf.memory      = V4L2_MEMORY_MMAP;
        buf.index       = i;

        if (-1 == xioctl (fd3, VIDIOC_QBUF, &buf)) {
            return errnoexit ("VIDIOC_QBUF");
        }
    }

    type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    if (-1 == xioctl (fd3, VIDIOC_STREAMON, &type)) {
        return errnoexit ("VIDIOC_STREAMON");
    }

    return SUCCESS_LOCAL;
}

int stopCapturing3(void) {
    enum v4l2_buf_type type;

    type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    if (-1 == xioctl (fd3, VIDIOC_STREAMOFF, &type)) {
        return errnoexit ("VIDIOC_STREAMOFF");
    }
    
    return SUCCESS_LOCAL;
}

int deinitDevice3(void) {
    unsigned int i;
	LOGD("3 ImageProc deinitDevice2 buffers=0x%x!!",buffers3);
	if(buffers3)
	{
		for (i = 0; i < n_buffers3; ++i) {
			LOGD("3 ImageProc deinitDevice3 buffers[i].start=0x%x!!",buffers3[i].start);
			if(buffers3[i].start && (buffers3[i].start != MAP_FAILED)){
				if (-1 == munmap (buffers3[i].start, buffers3[i].length)) {
					return errnoexit ("munmap");
				}
			}
		}
		free (buffers3);
	}
    return SUCCESS_LOCAL;
}

//关闭设备
int closeDevice3(void) {
    if (-1 == close (fd3)){
        fd3 = -1;
		LOGD("3 ImageProc closeDevice failed!!");
        return errnoexit ("close");
    }

    fd3 = -1;
    return SUCCESS_LOCAL;
}


JNIEXPORT jint JNICALL Java_com_asiatelco_webcamera_CapActivity_CaptureCamera3( JNIEnv* env, jobject thiz)
{
	int length = 0;
	unsigned int i=0;
	struct v4l2_buffer buf;
	fd_set fds;
	struct timeval tv;
	int r;
	int savefd=-1;
	int ret;
	char path[64];

    ret = openDevice3(4);
	LOGD("3 ImageProc openDevice ret =  %d",ret);

	if (ret != ERROR_LOCAL)
	{
        ret = initDevice3();
		LOGD("3 ImageProc initDevice ret =  %d",ret);
    }
	else
	{
		LOGD("3 ImageProc openDevice failure ret =  %d",ret);
		return ERROR_LOCAL;
	}


    if (ret != ERROR_LOCAL)
	{
        ret = startCapturing3();
		LOGD("3 ImageProc startCapturing ret =  %d",ret);

        if (ret != SUCCESS_LOCAL)
		{
			LOGD("3 ImageProc startCapturing failure,close device!! ret =  %d",ret);
            stopCapturing3();
            deinitDevice3 ();
            closeDevice3 ();
            LOGD("3 device resetted");
			return ERROR_LOCAL;
        }
    }
	else
	{
		LOGD("3 ImageProc initDevice failure,close device!! ret =  %d",ret);
		deinitDevice3 ();
		closeDevice3 ();
		return ERROR_LOCAL;
	}


	for(;;)
	{
		FD_ZERO (&fds);
		FD_SET (fd3, &fds);

		tv.tv_sec = 2;
		tv.tv_usec = 0;
		LOGD("3 ImageProc select start!!");
		r = select (fd3 + 1, &fds, NULL, NULL, &tv);
		LOGD("3 ImageProc select stop!!");
		if (-1 == r) {
			if (EINTR == errno) {
				LOGD("3 ImageProc select EINTR!!");
				break;
			}
			LOGD("3 ImageProc select failed!!");
			//return ERROR_LOCAL;
			break;
		}

		if (0 == r) {
			LOGD("3 ImageProc select timeout!!");
			//return ERROR_LOCAL;
			break;
		}

		LOGD("3 ImageProc readFrameOnce r =  %d",r);

		CLEAR (buf);

		buf.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;
		buf.memory = V4L2_MEMORY_MMAP;
		//buf.memory = V4L2_MEMORY_USERPTR;
			//LOGD("fd=%d,request=%d,buf=%d",fd,VIDIOC_DQBUF,&buf);
		if (-1 == xioctl (fd3 , VIDIOC_DQBUF, &buf)) {
			LOGD("3 ImageProc VIDIOC_DQBUF failed!!");
			//return ERROR_LOCAL;
			break;
		}

		assert (buf.index < n_buffers3);

		for( i = 0; i < buffers3[buf.index].length; i++)
		{
			if((buffers3[buf.index].start[i] == 0xff) && (buffers3[buf.index].start[i + 1] == 0xd9)) //检测MJPEG帧数据结束标志,存储图像
			{
				length = i + 2;
				break;
			}
		}

		if (-1 == xioctl (fd3, VIDIOC_QBUF, &buf)) {
			LOGD("3 ImageProc readFrame VIDIOC_QBUF failed!!");
			//return ERROR_LOCAL;
			break;
		}

		LOGE("3 length:%d!\n",length);
		if(length<=0)
			continue;
		else
		{
			if(access("/sdcard/cap3.jpg", F_OK) == 0)
			{
				system("rm /sdcard/cap3.jpg");
				LOGD("3 rm /sdcard/cap3.jpg\n");
			}
			sprintf(path, "/sdcard/cap3%d.jpg", idx);
			savefd = open(path, O_RDWR | O_CREAT, S_IRWXU|S_IRWXG|S_IRWXO);
			if(savefd < 0)
			{
				LOGD("3 failed to create cap3.jpg:%d!\n",errno);
			}
			else
			{
				write(savefd, (char *)buffers3[buf.index].start, length);
				close(savefd);
			}
			//LOGE("buffers[buf.index].start[0]:%x,buffers[buf.index].start[length-1]:%x\n",buffers[buf.index].start[0],buffers[buf.index].start[length-1]);
			break;
		}
	}

	stopCapturing3 ();
    deinitDevice3 ();
    closeDevice3 ();
    fd3 = -1;

	return 0;
}


int openDevice4(int i) {
    struct stat st;
    sprintf(dev_name4,"/dev/video%d",i);
    //stat() 获得文件属性，并判断是否为字符设备文件
    if (-1 == stat (dev_name4, &st)) {
        LOGI("4Cannot identify '%s': %d, %s", dev_name4, errno, strerror (errno));
        return ERROR_LOCAL;
    }

    if (!S_ISCHR (st.st_mode)) {
        LOGI("4 %s is no device", dev_name4);
        return ERROR_LOCAL;
    }

    fd4 = open (dev_name4, O_RDWR);

    if (-1 == fd4) {
        LOGI("4 Cannot open '%s': %d, %s", dev_name4, errno, strerror (errno));
        return ERROR_LOCAL;
    }
    return SUCCESS_LOCAL;
}

int initDevice4(void) 
{
    struct v4l2_capability cap;
    struct v4l2_cropcap cropcap;
    struct v4l2_crop crop;
    struct v4l2_format fmt;
    unsigned int min;
    
    //VIDIOC_QUERYCAP 命令 来获得当前设备的各个属性
    if (-1 == xioctl (fd4, VIDIOC_QUERYCAP, &cap)) {
        if (EINVAL == errno) {
            LOGI("4 %s is no V4L2 device", dev_name4);
            return ERROR_LOCAL;
        } else {
            return errnoexit ("VIDIOC_QUERYCAP");
        }
    }
    
    //V4L2_CAP_VIDEO_CAPTURE 0x00000001
    // 这个设备支持 video capture 的接口，即这个设备具备 video capture 的功能
    if (!(cap.capabilities & V4L2_CAP_VIDEO_CAPTURE)) {
        LOGI("4 %s is no video capture device", dev_name4);
        return ERROR_LOCAL;
    }
    
    //V4L2_CAP_STREAMING 0x04000000
    // 这个设备是否支持 streaming I/O 操作函数
    if (!(cap.capabilities & V4L2_CAP_STREAMING)) {
        LOGI("4 %s does not support streaming i/o", dev_name4);
        return ERROR_LOCAL;
    }
    //获得设备对 Image Cropping 和 Scaling 的支持
    CLEAR (cropcap);

    cropcap.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    
    //设置图形格式
    CLEAR (fmt);

    fmt.type                = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    fmt.fmt.pix.width       = IMG_WIDTH; 
    fmt.fmt.pix.height      = IMG_HEIGHT;

    fmt.fmt.pix.pixelformat = V4L2_PIX_FMT_MJPEG;
    fmt.fmt.pix.field       = V4L2_FIELD_INTERLACED;
    //检查流权限
    if (-1 == xioctl (fd4, VIDIOC_S_FMT, &fmt)) {
		LOGD("4 ImageProc VIDIOC_S_FMT FAILED!!");
        return errnoexit ("VIDIOC_S_FMT");
    }

    min = fmt.fmt.pix.width * 2;
    //每行像素所占的 byte 数
    if (fmt.fmt.pix.bytesperline < min) {
        fmt.fmt.pix.bytesperline = min;
    }
    min = fmt.fmt.pix.bytesperline * fmt.fmt.pix.height;
    if (fmt.fmt.pix.sizeimage < min) {
        fmt.fmt.pix.sizeimage = min;
    }

    return initMMap4();

}

int initMMap4(void) {
    struct v4l2_requestbuffers req;

    CLEAR (req);

    req.count   = 4;
    req.type    = V4L2_BUF_TYPE_VIDEO_CAPTURE;
    req.memory  = V4L2_MEMORY_MMAP;

    if (-1 == xioctl (fd4, VIDIOC_REQBUFS, &req)) {
        if (EINVAL == errno) {
            LOGI("4 %s does not support memory mapping", dev_name4);
            return ERROR_LOCAL;
        } else {
            return errnoexit ("VIDIOC_REQBUFS");
        }
    }

    if (req.count < 2) {
        LOGI("4 Insufficient buffer memory on %s", dev_name4);
        return ERROR_LOCAL;
     }

    buffers4 = calloc (req.count, sizeof (*buffers4));

    if (!buffers4) {
        LOGI("4 Out of memory");
        return ERROR_LOCAL;
    }

    for (n_buffers4 = 0; n_buffers4 < req.count; ++n_buffers4) {
        struct v4l2_buffer buf;

        CLEAR (buf);

        buf.type        = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        buf.memory      = V4L2_MEMORY_MMAP;
        buf.index       = n_buffers4;

        if (-1 == xioctl (fd4, VIDIOC_QUERYBUF, &buf)) {
			LOGD("4 ImageProc 2VIDIOC_QUERYBUF_FAILED!!");
            return errnoexit ("VIDIOC_QUERYBUF");
        }
        
        buffers4[n_buffers4].length = buf.length;
        buffers4[n_buffers4].start =
        mmap (NULL ,
            buf.length,
            PROT_READ | PROT_WRITE,
            MAP_SHARED,
            fd4, buf.m.offset);

        if (MAP_FAILED == buffers4[n_buffers4].start) {
			 LOGD("4 ImageProc 2MAP_FAILED!!");
            return errnoexit ("mmap");
        }
    }

    return SUCCESS_LOCAL;
}

int startCapturing4(void) {
    unsigned int i;
    enum v4l2_buf_type type;

    for (i = 0; i < n_buffers4; ++i) {
        struct v4l2_buffer buf;

        CLEAR (buf);

        buf.type        = V4L2_BUF_TYPE_VIDEO_CAPTURE;
        buf.memory      = V4L2_MEMORY_MMAP;
        buf.index       = i;

        if (-1 == xioctl (fd4, VIDIOC_QBUF, &buf)) {
            return errnoexit ("VIDIOC_QBUF");
        }
    }

    type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    if (-1 == xioctl (fd4, VIDIOC_STREAMON, &type)) {
        return errnoexit ("VIDIOC_STREAMON");
    }

    return SUCCESS_LOCAL;
}

int stopCapturing4(void) {
    enum v4l2_buf_type type;

    type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    if (-1 == xioctl (fd4, VIDIOC_STREAMOFF, &type)) {
        return errnoexit ("VIDIOC_STREAMOFF");
    }
    
    return SUCCESS_LOCAL;
}

int deinitDevice4(void) {
    unsigned int i;
	LOGD("4 ImageProc deinitDevice2 buffers=0x%x!!",buffers4);
	if(buffers4)
	{
		for (i = 0; i < n_buffers4; ++i) {
			LOGD("4 ImageProc deinitDevice4 buffers[i].start=0x%x!!",buffers4[i].start);
			if(buffers4[i].start && (buffers4[i].start != MAP_FAILED)){
				if (-1 == munmap (buffers4[i].start, buffers4[i].length)) {
					return errnoexit ("munmap");
				}
			}
		}
		free (buffers4);
	}
    return SUCCESS_LOCAL;
}

//关闭设备
int closeDevice4(void) {
    if (-1 == close (fd4)){
        fd4 = -1;
		LOGD("4 ImageProc closeDevice failed!!");
        return errnoexit ("close");
    }

    fd4 = -1;
    return SUCCESS_LOCAL;
}


JNIEXPORT jint JNICALL Java_com_asiatelco_webcamera_CapActivity_CaptureCamera4( JNIEnv* env, jobject thiz)
{
	int length = 0;
	unsigned int i=0;
	struct v4l2_buffer buf;
	fd_set fds;
	struct timeval tv;
	int r;
	int savefd=-1;
	int ret;
	char path[64];

    ret = openDevice4(6);
	LOGD("4 ImageProc openDevice ret =  %d",ret);

	if (ret != ERROR_LOCAL)
	{
        ret = initDevice4();
		LOGD("4 ImageProc initDevice ret =  %d",ret);
    }
	else
	{
		LOGD("4 ImageProc openDevice failure ret =  %d",ret);
		return ERROR_LOCAL;
	}


    if (ret != ERROR_LOCAL)
	{
        ret = startCapturing4();
		LOGD("4 ImageProc startCapturing ret =  %d",ret);

        if (ret != SUCCESS_LOCAL)
		{
			LOGD("4 ImageProc startCapturing failure,close device!! ret =  %d",ret);
            stopCapturing4();
            deinitDevice4 ();
            closeDevice4 ();
            LOGD("4 device resetted");
			return ERROR_LOCAL;
        }
    }
	else
	{
		LOGD("4 ImageProc initDevice failure,close device!! ret =  %d",ret);
		deinitDevice4 ();
		closeDevice4 ();
		return ERROR_LOCAL;
	}


	for(;;)
	{
		FD_ZERO (&fds);
		FD_SET (fd4, &fds);

		tv.tv_sec = 2;
		tv.tv_usec = 0;
		LOGD("4 ImageProc select start!!");
		r = select (fd4 + 1, &fds, NULL, NULL, &tv);
		LOGD("4 ImageProc select stop!!");
		if (-1 == r) {
			if (EINTR == errno) {
				LOGD("4 ImageProc select EINTR!!");
				break;
			}
			LOGD("4 ImageProc select failed!!");
			//return ERROR_LOCAL;
			break;
		}

		if (0 == r) {
			LOGD("4 ImageProc select timeout!!");
			//return ERROR_LOCAL;
			break;
		}

		LOGD("4 ImageProc readFrameOnce r =  %d",r);

		CLEAR (buf);

		buf.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;
		buf.memory = V4L2_MEMORY_MMAP;
		//buf.memory = V4L2_MEMORY_USERPTR;
			//LOGD("fd=%d,request=%d,buf=%d",fd,VIDIOC_DQBUF,&buf);
		if (-1 == xioctl (fd4 , VIDIOC_DQBUF, &buf)) {
			LOGD("4 ImageProc VIDIOC_DQBUF failed!!");
			//return ERROR_LOCAL;
			break;
		}

		assert (buf.index < n_buffers4);

		for( i = 0; i < buffers4[buf.index].length; i++)
		{
			if((buffers4[buf.index].start[i] == 0xff) && (buffers4[buf.index].start[i + 1] == 0xd9)) //检测MJPEG帧数据结束标志,存储图像
			{
				length = i + 2;
				break;
			}
		}

		if (-1 == xioctl (fd4, VIDIOC_QBUF, &buf)) {
			LOGD("4 ImageProc readFrame VIDIOC_QBUF failed!!");
			//return ERROR_LOCAL;
			break;
		}

		LOGE("4 length:%d!\n",length);
		if(length<=0)
			continue;
		else
		{
			if(access("/sdcard/cap4.jpg", F_OK) == 0)
			{
				system("rm /sdcard/cap4.jpg");
				LOGD("4 rm /sdcard/cap4.jpg\n");
			}
			sprintf(path, "/sdcard/cap4%d.jpg", idx);
			idx ++;
			savefd = open(path, O_RDWR | O_CREAT, S_IRWXU|S_IRWXG|S_IRWXO);
			if(savefd < 0)
			{
				LOGD("4 failed to create cap4.jpg:%d!\n",errno);
			}
			else
			{
				write(savefd, (char *)buffers4[buf.index].start, length);
				close(savefd);
			}
			//LOGE("buffers[buf.index].start[0]:%x,buffers[buf.index].start[length-1]:%x\n",buffers[buf.index].start[0],buffers[buf.index].start[length-1]);
			break;
		}
	}

	stopCapturing4 ();
    deinitDevice4 ();
    closeDevice4 ();
    fd4 = -1;

	return 0;
}