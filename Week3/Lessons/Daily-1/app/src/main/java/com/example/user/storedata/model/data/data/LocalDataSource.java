package com.example.user.storedata.model.data.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.storedata.model.data.Person;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource extends SQLiteOpenHelper {

    public LocalDataSource(Context context) {
        super(context, LocalDataContract.NAME, null, LocalDataContract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LocalDataContract.DDL.CREATE_PERSON_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long savePerson(Person person) {
        //getWritableDatabase opens the database
        SQLiteDatabase database = getWritableDatabase();

        //save person with content values
        ContentValues contentValues = new ContentValues();
        contentValues.put(LocalDataContract.Person.NAME, person.getName());
        contentValues.put(LocalDataContract.Person.AGE, person.getAge());
        contentValues.put(LocalDataContract.Person.GENDER, person.getGender());

        //insert the person into the database
        long rowNumber = database.insert(LocalDataContract.TABLE_PERSON, null, contentValues);

        //Close database
        database.close();
        return rowNumber;
    }

    public List<Person> getAllPerson() {
        //getWritableDatabase opens the database
        SQLiteDatabase database = getWritableDatabase();

        //List of persons to return
        List<Person> personList = new ArrayList<>();

        //Cursor to fetch through the results
        Cursor cursor = database.rawQuery(LocalDataContract.DML.GET_ALL_PERSON, null);

        //Loop to create the list of person
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person(
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Person.NAME)),
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Person.AGE)),
                        cursor.getString(cursor.getColumnIndex(LocalDataContract.Person.GENDER))
                );

                personList.add(person);
            } while (cursor.moveToNext());
        }

        //close database
        database.close();
        return personList;
    }
}
