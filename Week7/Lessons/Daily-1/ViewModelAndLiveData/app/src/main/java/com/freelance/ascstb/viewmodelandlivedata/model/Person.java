package com.freelance.ascstb.viewmodelandlivedata.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Person {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    int PersonId;

    String name;
    String age;
    String gender;

    public Person(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "PersonId=" + PersonId +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    @NonNull
    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(@NonNull int personId) {
        PersonId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
