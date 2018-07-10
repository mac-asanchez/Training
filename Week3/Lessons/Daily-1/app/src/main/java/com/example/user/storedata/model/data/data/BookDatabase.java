package com.example.user.storedata.model.data.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = Book.class, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDAO bookDao();
}
