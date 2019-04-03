package com.dev.common.scheduler;

import com.dev.common.scheduler.task.IOTask;
import com.dev.common.scheduler.task.UITask;

/**
 * @author guolong
 * @since 2019/4/3
 */
public class Sample {

    public static void main(String[] args) {
        RxScheduler.doOnIoThread(new IOTask<Void>() {
            @Override
            public Void doOnIoThread() {
                // 耗时任务
                return null;
            }

            @Override
            public void onComplete(Void aVoid) {
                // 返回值
            }

            @Override
            public void onFail(Throwable e) {
                //错误之后的回调
            }
        });

        RxScheduler.doOnUIThread(new UITask<String>() {
            @Override
            public String doOnUIThread() {
                // 在UI线程执行
                return "";
            }
        });
    }
}
