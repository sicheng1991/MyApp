package com.chimu.mylib.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by Longwj on 2017/8/25.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {


    /**
     * @return
     * 1.false：框架认为你作业已经执行完毕了，那么下一个作业就立刻展开了
     2.rue：框架将作业结束状态交给你去处理。因为我们可能会异步的通过线程等方式去执行工作，
     这个时间肯定不能放在主线程里面去控制，这时候需要手动调用
     *
     * @param params
     *
     */
    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
