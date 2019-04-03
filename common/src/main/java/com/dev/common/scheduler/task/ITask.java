package com.dev.common.scheduler.task;

/**
 * @author guolong
 * @since 2019/4/3
 */
public abstract class ITask<T> {

    /**
     * 运行在主线程
     */
    public void onComplete(T t) {
    }

    /**
     * 运行在主线程
     */
    public void onFail(Throwable e) {
    }
}
