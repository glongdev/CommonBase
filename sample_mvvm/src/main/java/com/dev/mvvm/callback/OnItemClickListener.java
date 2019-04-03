package com.dev.mvvm.callback;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author guolong
 * @since 2019/4/3
 */
public interface OnItemClickListener {
    void onItemClick(RecyclerView.Adapter adapter, View itemView, int position);
}
