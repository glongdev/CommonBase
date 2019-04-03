package com.dev.common.base;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author guolong
 * @since 2019/4/3
 */
public abstract class BaseFragment<T extends ViewModel> extends Fragment {
    protected String mTag = getClass().getSimpleName();
    protected View mContentView;

    protected T mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = initViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(contentLayoutId(), container, false);
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        subscribe();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
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
            if (getActivity() == null || getActivity().isFinishing()) {
                return;
            }
            View view = getActivity().getCurrentFocus();
            if (view != null) {
                IBinder binder = view.getWindowToken();
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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
            if (getActivity() == null || getActivity().isFinishing()) {
                return;
            }
            if (editText != null) {
                editText.requestFocus();
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.showSoftInput(editText, 0);
            }
        } catch (Exception e) {
            Log.e(mTag, e.getMessage(), e);
        }
    }
}
