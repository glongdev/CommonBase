package com.dev.mvvm.fragments;

import android.view.View;

import com.dev.common.base.BaseFragment;
import com.dev.mvvm.R;
import com.dev.mvvm.adapters.BookAdapter;
import com.dev.mvvm.callback.OnItemClickListener;
import com.dev.mvvm.model.BookBean;
import com.dev.mvvm.viewmodel.BookViewModel;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author guolong
 * @since 2019/4/3
 */
public class BookFragment extends BaseFragment<BookViewModel> implements OnItemClickListener {

    private BookAdapter mAdapter;

    @Override
    protected int contentLayoutId() {
        return R.layout.fragment_book;
    }

    @Override
    protected void setupViews() {
        RecyclerView mRecyclerView = mContentView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter = new BookAdapter();
        mAdapter.setListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void subscribe() {
        // ...
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, View itemView, int position) {
        BookBean bookBean = mAdapter.getmData().get(position);
        mViewModel.selectBook.setValue(bookBean);
    }

    @Override
    protected void initData() {
        // 模仿网络请求数据
        mViewModel.requestBookList().observe(this, new Observer<List<BookBean>>() {
            @Override
            public void onChanged(List<BookBean> bookBeans) {
                mContentView.findViewById(R.id.loading).setVisibility(View.GONE); // dismiss loading...
                mAdapter.setmData(bookBeans);
            }
        });
    }
}
