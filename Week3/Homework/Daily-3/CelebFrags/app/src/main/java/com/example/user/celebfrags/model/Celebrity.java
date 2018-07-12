package com.example.user.celebfrags.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Celebrity implements Parcelable {
    int CelebrityId;
    String Name;
    int Picture;
    String Details;

    public Celebrity(int celebrityId, String name, int picture, String details) {
        CelebrityId = celebrityId;
        Name = name;
        Picture = picture;
        Details = details;
    }

    protected Celebrity(Parcel in) {
        CelebrityId = in.readInt();
        Name = in.readString();
        Picture = in.readInt();
        Details = in.readString();
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    @Override
    public String toString() {
        return "Celebrity{" +
                "CelebrityId=" + CelebrityId +
                ", Name='" + Name + '\'' +
                ", Picture=" + Picture +
                ", Details='" + Details + '\'' +
                '}';
    }

    public int getCelebrityId() {
        return CelebrityId;
    }

    public void setCelebrityId(int celebrityId) {
        CelebrityId = celebrityId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(CelebrityId);
        parcel.writeString(Name);
        parcel.writeInt(Picture);
        parcel.writeString(Details);
    }
}
