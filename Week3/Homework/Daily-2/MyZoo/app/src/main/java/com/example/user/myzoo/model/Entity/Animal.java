package com.example.user.myzoo.model.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    int AnimalId;
    int CategoryId;
    String CategoryDescription;
    String AnimalDescription;
    String Detail;
    String Sound;

    public Animal(int animalId, int categoryId, String animalDescription, String detail, String categoryDescription, String sound) {
        AnimalId = animalId;
        CategoryId = categoryId;
        AnimalDescription = animalDescription;
        Detail = detail;
        CategoryDescription = categoryDescription;
        Sound = sound;
    }

    protected Animal(Parcel in) {
        AnimalId = in.readInt();
        CategoryId = in.readInt();
        CategoryDescription = in.readString();
        AnimalDescription = in.readString();
        Detail = in.readString();
        Sound = in.readString();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    @Override
    public String toString() {
        return "Animal{" +
                "AnimalId=" + AnimalId +
                ", CategoryId=" + CategoryId +
                ", CategoryDescription='" + CategoryDescription + '\'' +
                ", AnimalDescription='" + AnimalDescription + '\'' +
                ", Detail='" + Detail + '\'' +
                ", Sound='" + Sound + '\'' +
                '}';
    }

    public int getAnimalId() {
        return AnimalId;
    }

    public void setAnimalId(int animalId) {
        AnimalId = animalId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getAnimalDescription() {
        return AnimalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        AnimalDescription = animalDescription;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }

    public String getSound() {
        return Sound;
    }

    public void setSound(String sound) {
        Sound = sound;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(AnimalId);
        parcel.writeInt(CategoryId);
        parcel.writeString(CategoryDescription);
        parcel.writeString(AnimalDescription);
        parcel.writeString(Detail);
        parcel.writeString(Sound);
    }
}
