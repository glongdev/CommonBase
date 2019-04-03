package com.dev.mvvm.activities;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dev.common.base.BaseCompatActivity;
import com.dev.common.utils.ARouterUtil;
import com.dev.mvvm.R;
import com.dev.mvvm.model.BookBean;
import com.dev.mvvm.viewmodel.BookViewModel;

import androidx.lifecycle.Observer;

@Route(path = ARouterUtil.Constant.MVVM.MVVMSampleActiivty)
public class MVVMSampleActivity extends BaseCompatActivity<BookViewModel> {

    private TextView textView;

    @Override
    protected int contentLayoutId() {
        return R.layout.activity_mvvm_sample;
    }

    @Override
    protected void setupViews() {
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void subscribe() {
        mViewModel.selectBook.observe(this, new Observer<BookBean>() {
            @Override
            public void onChanged(BookBean bookBean) {
                textView.setText(bookBean.getTitle());
            }
        });
    }
}
