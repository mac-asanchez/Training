package com.example.user.storedata.model.data.data;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import java.util.List;

import static android.arch.persistence.room.Room.databaseBuilder;

public class RoomHelper {
    private static final String TAG = "BookHelper";
    Context context;
    BookDatabase database;
    BookDAO bookDao;

    public RoomHelper(Context context) {
        this.context = context;
        this.database = databaseBuilder(context, BookDatabase.class, "Book-database").build();

        this.bookDao = database.bookDao();
    }

    public void saveBook(final Book book) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bookDao.saveBook(book);
                Log.d(TAG, "run: " + book.toString());
            }
        }).start();
    }

    public void getBooks() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Book> books = bookDao.getAllBooks();

                Log.d(TAG, "run: " + books.toString());
            }
        }).start();
    }
}
