package com.dev.common.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.dev.common.utils.ScreenAdaptiveUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author guolong
 * @since 2019/4/3
 */
public abstract class BaseCompatActivity<T extends ViewModel> extends AppCompatActivity {

    protected String mTag = getClass().getSimpleName();
    protected T mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(mTag, "onCreate()####");
        initWindow();
        initScreenAdaptive();
        mViewModel = initViewModel();
        setContentView(contentLayoutId());
        subscribe();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(mTag, "onDestroy()####");
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupViews();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setupViews();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setupViews();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /**
     * 初始化窗口，沉侵式状态栏
     */
    protected void initWindow() {
        int flag;
        if (Build.VERSION.SDK_INT >= 23) {
            flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        }
        getWindow().getDecorView().setSystemUiVisibility(flag);
    }

    /**
     * 初始化屏幕适配
     */
    protected void initScreenAdaptive() {
        ScreenAdaptiveUtil.adaptive(this);
    }

    /**
     * 初始化ViewModel，通过获取当前类的泛型，然后生成ViewModel
     *
     * @return 指定泛型的ViewModel
     */
    @SuppressWarnings("unchecked")
    protected T initViewModel() {
        ParameterizedType superTYpe = ((ParameterizedType) getClass().getGenericSuperclass());
        if (superTYpe == null) {
            return null;
        }
        Type[] types = superTYpe.getActualTypeArguments();
        if (types.length > 0) {
            Type type = types[0];
            return ViewModelProviders.of(this).get((Class<T>) type);
        }
        return null;
    }

    /**
     * 设置content layout id
     */
    protected abstract int contentLayoutId();

    /**
     * 初始化Views
     */
    protected void setupViews() {
    }

    /**
     * 订阅ViewModel的数据——>更新UI
     */
    protected void subscribe() {
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 隐藏软键盘。
     */
    protected void hideSoftKeyboard() {
        try {
            View view = getCurrentFocus();
            if (view != null) {
                IBinder binder = view.getWindowToken();
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            Log.e(mTag, e.getMessage(), e);
        }
    }

    /**
     * 显示软键盘。
     */
    protected void showSoftKeyboard(EditText editText) {
        try {
            if (editText != null) {
                editText.requestFocus();
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.showSoftInput(editText, 0);
            }
        } catch (Exception e) {
            Log.e(mTag, e.getMessage(), e);
        }
    }
}
