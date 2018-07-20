
package com.example.user.acronymsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIResult implements Parcelable {

    @SerializedName("sf")
    @Expose
    private String sf;
    @SerializedName("lfs")
    @Expose
    private List<Lf> lfs = null;

    protected APIResult(Parcel in) {
        sf = in.readString();
    }

    public static final Creator<APIResult> CREATOR = new Creator<APIResult>() {
        @Override
        public APIResult createFromParcel(Parcel in) {
            return new APIResult(in);
        }

        @Override
        public APIResult[] newArray(int size) {
            return new APIResult[size];
        }
    };

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public List<Lf> getLfs() {
        return lfs;
    }

    public void setLfs(List<Lf> lfs) {
        this.lfs = lfs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sf);
    }
}
