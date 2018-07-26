package com.example.user.recepiesapp.model.edamam;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit implements Parcelable {

    public static final Creator<Hit> CREATOR = new Creator<Hit>() {
        @Override
        public Hit createFromParcel(Parcel in) {
            return new Hit(in);
        }

        @Override
        public Hit[] newArray(int size) {
            return new Hit[size];
        }
    };
    @SerializedName("recipe")
    @Expose
    private Recipe recipe;
    @SerializedName("bookmarked")
    @Expose
    private Boolean bookmarked;
    @SerializedName("bought")
    @Expose
    private Boolean bought;

    protected Hit(Parcel in) {
        byte tmpBookmarked = in.readByte();
        bookmarked = tmpBookmarked == 0 ? null : tmpBookmarked == 1;
        byte tmpBought = in.readByte();
        bought = tmpBought == 0 ? null : tmpBought == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(recipe, flags);
        dest.writeByte((byte) (bookmarked == null ? 0 : bookmarked ? 1 : 2));
        dest.writeByte((byte) (bought == null ? 0 : bought ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Boolean getBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(Boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }
}
