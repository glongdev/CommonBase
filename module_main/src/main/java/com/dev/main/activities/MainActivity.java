package com.dev.main.activities;

import android.view.View;

import com.dev.common.base.BaseCompatActivity;
import com.dev.common.utils.ARouterUtil;
import com.dev.main.R;
import com.dev.main.viewmodel.MainViewModel;

public class MainActivity extends BaseCompatActivity<MainViewModel> {

    @Override
    protected int contentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupViews() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterUtil.go(ARouterUtil.Constant.MVVM.MVVMSampleActiivty).navigation();
            }
        });
    }
}
