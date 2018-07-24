package com.example.user.firebaseauthentication.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;

public class MyUser implements Parcelable {
    String email;
    String name;

    public MyUser(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public MyUser(FirebaseUser user){
        this.email = user.getEmail();
        this.name = user.getDisplayName();
    }

    public MyUser(GoogleSignInAccount user){
        this.email = user.getEmail();
        this.name = user.getDisplayName();
    }

    protected MyUser(Parcel in) {
        email = in.readString();
        name = in.readString();
    }

    public static final Creator<MyUser> CREATOR = new Creator<MyUser>() {
        @Override
        public MyUser createFromParcel(Parcel in) {
            return new MyUser(in);
        }

        @Override
        public MyUser[] newArray(int size) {
            return new MyUser[size];
        }
    };

    @Override
    public String toString() {
        return "MyUser{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(name);
    }
}
