package com.example.user.personsapp.Code;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String Name;
    private String LastName;
    private int Age;

    public Person(String name, String lastName, int age){
        Name = name;
        LastName = lastName;
        Age = age;
    }

    protected Person(Parcel in) {
        Name = in.readString();
        LastName = in.readString();
        Age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        //return super.toString();
        return Name + " " + LastName + " (" + String.valueOf(Age) + " years old)";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(LastName);
        parcel.writeInt(Age);
    }
}
