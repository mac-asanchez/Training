package com.freelance.ascstb.viewmodelandlivedata.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM Person AS a WHERE a.name = :name")
    Single<Person> getPerson(String name);

    @Query("SELECT * FROM Person")
    Flowable<List<Person>> getAllPerson();

    @Insert
    long updatePerson(Person person);
}
