package com.dev.mvvm.repositories;

import com.dev.common.scheduler.RxScheduler;
import com.dev.common.scheduler.task.IOTask;
import com.dev.mvvm.model.BookBean;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * @author guolong
 * @since 2019/4/3
 */
public class BookRepository {
    /**
     * 模仿网络请求，运行在子线程中
     *
     * @return 书列表
     */
    public LiveData<List<BookBean>> requestBookList() {
        final MutableLiveData<List<BookBean>> booksLiveData = new MutableLiveData<>();

        RxScheduler.doOnIoThread(new IOTask<List<BookBean>>() {
            @Override
            public List<BookBean> doOnIoThread() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return localData();
            }

            @Override
            public void onComplete(List<BookBean> bookBeans) {
                super.onComplete(bookBeans);
                booksLiveData.setValue(bookBeans);
            }
        });

        return booksLiveData;
    }

    private List<BookBean> localData() {
        List<BookBean> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            books.add(new BookBean("C", 2));
            books.add(new BookBean("C++", 2));
            books.add(new BookBean("IOS", 2));
            books.add(new BookBean("Android", 2));
            books.add(new BookBean("go", 2));
            books.add(new BookBean("Js", 2));
            books.add(new BookBean("HTTP", 2));
            books.add(new BookBean("Java", 2));
            books.add(new BookBean("Kotlin", 2));
            books.add(new BookBean("objective-C", 2));
            books.add(new BookBean("C#", 2));
            books.add(new BookBean("PHP", 2));
            books.add(new BookBean("Python", 2));
            books.add(new BookBean("Ruby", 2));
            books.add(new BookBean("SQL", 2));
            books.add(new BookBean(".NET", 2));
            books.add(new BookBean("Logo", 2));
        }
        return books;
    }
}
