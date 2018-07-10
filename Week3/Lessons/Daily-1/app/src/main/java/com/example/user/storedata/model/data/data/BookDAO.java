package com.example.user.storedata.model.data.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BookDAO {

    @Insert
    void saveBook(Book book);

    @Query("SELECT * FROM Book")
    List<Book> getAllBooks();
}
