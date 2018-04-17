#include <jni.h>
#include <android/log.h>
#include <android/bitmap.h>

#include <string.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include <fcntl.h>              /* low-level i/o */
#include <unistd.h>
#include <errno.h>
#include <malloc.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/time.h>
#include <sys/mman.h>
#include <sys/ioctl.h>

#include <asm/types.h>          /* for videodev2.h */

#include <linux/videodev2.h>
#include <linux/usbdevice_fs.h>

#define  LOG_TAG    "ImageProc"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

#define CLEAR(x) memset (&(x), 0, sizeof (x))

#define IMG_WIDTH 1920
#define IMG_HEIGHT 1080

#define ERROR_LOCAL -1
#define SUCCESS_LOCAL 0

struct buffer {
        char *                  start;
        size_t                  length;
};

static char            dev_name[16];
static int              fd              = -1;
struct buffer *         buffers         = NULL;
static unsigned int     n_buffers       = 0;

int camerabase = -1;

//int *rgb = NULL;
//int *ybuf = NULL;

//int yuv_tbl_ready = 0;
//int y1192_tbl[256];
//int v1634_tbl[256];
//int v833_tbl[256];
//int u400_tbl[256];
//int u2066_tbl[256];

int errnoexit(const char *s);

int xioctl(int fd, int request, void *arg);

int checkCamerabase(void);
int openDevice(int videoid);
int initDevice(void);
int initMMap(void);
int startCapturing(void);

//int readFrameOnce(void);
//int readFrame(void);
//void processImage (const void *p);

int stopCapturing(void);
int deinitDevice(void);
int closeDevice(void);

//void yuyv422toABGRY(unsigned char *src);
static char            dev_name2[16];
static int              fd2              = -1;
struct buffer *         buffers2         = NULL;
static unsigned int     n_buffers2       = 0;
int openDevice2(int videoid);
int initDevice2(void);
int initMMap2(void);
int startCapturing2(void);
int stopCapturing2(void);
int deinitDevice2(void);
int closeDevice2(void);

static char            dev_name3[16];
static int              fd3              = -1;
struct buffer *         buffers3         = NULL;
static unsigned int     n_buffers3       = 0;
int openDevice3(int videoid);
int initDevice3(void);
int initMMap3(void);
int startCapturing3(void);
int stopCapturing3(void);
int deinitDevice3(void);
int closeDevice3(void);

static char            dev_name4[16];
static int              fd4              = -1;
struct buffer *         buffers4         = NULL;
static unsigned int     n_buffers4       = 0;
int openDevice4(int videoid);
int initDevice4(void);
int initMMap4(void);
int startCapturing4(void);
int stopCapturing4(void);
int deinitDevice4(void);
int closeDevice4(void);

