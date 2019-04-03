package com.dev.common.scheduler;

import android.annotation.SuppressLint;

import com.dev.common.scheduler.task.IOTask;
import com.dev.common.scheduler.task.UITask;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 切换线程的执行类
 *
 * @author guolong
 * @since 2019/4/3
 */
@SuppressLint("CheckResult")
public class RxScheduler {

    /**
     * 线程切换至 io线程 执行特定任务
     */
    public static <T> void doOnIoThread(final IOTask<T> task) {
        Observable.just(task)
                .subscribeOn(Schedulers.io())
                .map(new Function<IOTask<T>, T>() {

                    @Override
                    public T apply(IOTask<T> tioTask) throws Exception {
                        return tioTask.doOnIoThread();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        task.onComplete(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        task.onFail(throwable);
                    }
                });
    }

    /**
     * 线程切换至UI线程执行特定任务
     */
    public static <T> void doOnUIThread(final UITask<T> task) {
        Observable.just(task)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UITask<T>>() {
                    @Override
                    public void accept(UITask<T> tuiTask) throws Exception {
                        task.onComplete(tuiTask.doOnUIThread());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        task.onFail(throwable);
                    }
                });
    }
}
