package com.example.user.activitylifecicleandintent.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonP implements Parcelable {
    String name;
    String age;
    String gender;

    public PersonP(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    protected PersonP(Parcel in) {
        name = in.readString();
        age = in.readString();
        gender = in.readString();
    }

    public static final Creator<PersonP> CREATOR = new Creator<PersonP>() {
        @Override
        public PersonP createFromParcel(Parcel in) {
            return new PersonP(in);
        }

        @Override
        public PersonP[] newArray(int size) {
            return new PersonP[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAge() { return age; }

    public String getGender() { return gender; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(age);
        parcel.writeString(gender);
    }
}
