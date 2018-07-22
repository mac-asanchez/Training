package com.example.user.myzoo.model.Entity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    int AnimalId;
    int CategoryId;
    String CategoryDescription;
    String AnimalDescription;
    String Detail;
    String SoundUrl;
    String ImageUrl;

    public Animal(int animalId, int categoryId, String categoryDescription, String animalDescription, String detail, String soundUrl, String imageUrl) {
        AnimalId = animalId;
        CategoryId = categoryId;
        CategoryDescription = categoryDescription;
        AnimalDescription = animalDescription;
        Detail = detail;
        SoundUrl = soundUrl;
        ImageUrl = imageUrl;
    }

    protected Animal(Parcel in) {
        AnimalId = in.readInt();
        CategoryId = in.readInt();
        CategoryDescription = in.readString();
        AnimalDescription = in.readString();
        Detail = in.readString();
        SoundUrl = in.readString();
        ImageUrl = in.readString();
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

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
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

    public String getSoundUrl() {
        return SoundUrl;
    }

    public Uri getSoundUri() {
        return Uri.parse(SoundUrl);
    }

    public void setSoundUrl(String soundUrl) {
        SoundUrl = soundUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
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
        parcel.writeString(SoundUrl);
        parcel.writeString(ImageUrl);
    }
}
