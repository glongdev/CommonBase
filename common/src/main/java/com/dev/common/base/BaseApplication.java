package com.dev.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dev.common.utils.ScreenAdaptiveUtil;

/**
 * @author guolong
 * @since 2019/4/3
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        initScreenAdaptive();
    }

    /**
     * 屏幕适配
     */
    protected void initScreenAdaptive() {
        ScreenAdaptiveUtil.adaptive(this);
    }
}
