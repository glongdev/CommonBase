package com.dev.mvvm.viewmodel;

import com.dev.mvvm.model.BookBean;
import com.dev.mvvm.repository.BookRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author guolong
 * @since 2019/4/3
 */
public class BookViewModel extends ViewModel {

    /**
     * 当前选中的图书
     */
    public MutableLiveData<BookBean> selectBook = new MutableLiveData<>();

    private BookRepository repository = new BookRepository();

    public LiveData<List<BookBean>> requestBookList() {
        return repository.requestBookList();
    }
}
