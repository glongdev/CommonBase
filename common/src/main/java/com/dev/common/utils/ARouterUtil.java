package com.dev.common.utils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 路由跳转封装
 *
 * @author guolong
 * @since 2019/4/3
 */
public class ARouterUtil {
    public static class Constant {

        // 注：路径必须以 ‘/’ 开头 至少包含两个 ‘/’
        // Search模块下
        public static class Search {
            public static final String SearchActivity = "/search/search_activity";
            // ...
        }

        // Main模块下
        public static class Main {
            public static final String MainActivity = "/main/main_activity";
            // ...
        }

        // MVVM 模块下
        public static class MVVM {
            public static final String MVVMSampleActiivty = "/mvvm/sample_activity";
            // ...
        }

    }

    /**
     * 由路由跳转
     *
     * @param path 跳转路径
     * @return ..
     */
    public static Postcard go(String path) {
        return ARouter.getInstance().build(path);
    }
}
