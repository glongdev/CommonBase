package com.dev.common.scheduler.task;

/**
 * 任意线程切换到UI线程的Task
 * 泛型T代表返回值
 * @author guolong
 * @since 2019/4/3
 */
public abstract class UITask<T> extends ITask<T> {
    public abstract T doOnUIThread();
}
