package com.dev.common.scheduler.task;

/**
 * 从任意线程切换的io线程的task
 * 泛型T代表返回值
 *
 * @author guolong
 * @since 2019/4/3
 */
public abstract class IOTask<T> extends ITask<T> {
    public abstract T doOnIoThread();
}
