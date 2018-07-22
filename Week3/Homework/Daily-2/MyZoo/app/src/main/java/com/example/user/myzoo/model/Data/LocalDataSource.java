package com.example.user.myzoo.model.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.myzoo.model.Entity.Animal;
import com.example.user.myzoo.model.Entity.Category;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource extends SQLiteOpenHelper {
    Context context;

    public LocalDataSource(Context context) {
        super(context, LocalDataContract.Table.ANIMAL, null, LocalDataContract.VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LocalDataContract.DDL.CREATE_CATEGORY_TABLE);
        sqLiteDatabase.execSQL(LocalDataContract.DDL.CREATE_ANIMAL_TABLE);

        //region fill categories
        for (String insert : LocalDataContract.DML.INSERT_CATEGORIES_QUERIES()) {
            sqLiteDatabase.execSQL(insert);
        }
        //endregion

        //region fill animals
        for (String insert : LocalDataContract.DML.INSERT_ANIMALS_QUERIES(context)) {
            sqLiteDatabase.execSQL(insert);
        }
        //endregion
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveCategory(Category category) {
        //getWritableDatabase opens the database
        SQLiteDatabase database = getWritableDatabase();

        //save person with content values
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDataContract.Category.ID, category.getCategoryId());
        contentValues.put(LocalDataContract.Category.DESCRIPTION, category.getCategoryDescription());

        //insert the person into the database
        long rowNumber = database.insert(LocalDataContract.Table.CATEGORY_NAME, null, contentValues);

        //Close database
        database.close();
        return rowNumber;
    }

    public List<Category> getAllCategories() {
        //getWritableDatabase opens the database
        SQLiteDatabase database = getWritableDatabase();

        //List of categories to return
        List<Category> categoryList = new ArrayList<>();

        //Cursor to fetch through the results
        Cursor cursor = database.rawQuery(LocalDataContract.DML.GET_ALL_CATEGORIES, null);

        //Loop to create the list of categories
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category(
                        cursor.getInt(cursor.getColumnIndex(LocalDataContract.Category.ID)),
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Category.DESCRIPTION))
                );

                categoryList.add(category);
            } while (cursor.moveToNext());
        }

        //close database
        database.close();
        return categoryList;
    }

    public List<Animal> getAnimalsByCategory(int CategoryId) {
        //getWritableDatabase opens the database
        SQLiteDatabase database = getWritableDatabase();

        //List of animals to return
        List<Animal> animalList = new ArrayList<>();

        //Cursor to fetch through the results
        Cursor cursor = database.rawQuery(LocalDataContract.DML.GET_ANIMALS_BY_CATEGORY, new String[]{String.valueOf(CategoryId)});

        //Loop to create the list of Animals
        if (cursor.moveToFirst()) {
            do {
                Animal animal = new Animal(
                        cursor.getInt(cursor.getColumnIndex(LocalDataContract.Animal.ID)),
                        cursor.getInt(cursor.getColumnIndex(LocalDataContract.Animal.CATEGORYID)),
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Category.DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Animal.DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Animal.DETAIL)),
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Animal.SOUND)),
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Animal.IMAGEURL))
                );

                animalList.add(animal);
            } while (cursor.moveToNext());
        }

        //close database
        database.close();
        return animalList;
    }
}
