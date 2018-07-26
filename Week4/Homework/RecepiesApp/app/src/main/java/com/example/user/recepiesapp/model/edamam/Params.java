
package com.example.user.recepiesapp.model.edamam;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params implements Parcelable {

    @SerializedName("sane")
    @Expose
    private List<Object> sane = null;
    @SerializedName("q")
    @Expose
    private List<String> q = null;
    @SerializedName("app_key")
    @Expose
    private List<String> appKey = null;
    @SerializedName("app_id")
    @Expose
    private List<String> appId = null;

    protected Params(Parcel in) {
        q = in.createStringArrayList();
        appKey = in.createStringArrayList();
        appId = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(q);
        dest.writeStringList(appKey);
        dest.writeStringList(appId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Params> CREATOR = new Creator<Params>() {
        @Override
        public Params createFromParcel(Parcel in) {
            return new Params(in);
        }

        @Override
        public Params[] newArray(int size) {
            return new Params[size];
        }
    };

    public List<Object> getSane() {
        return sane;
    }

    public void setSane(List<Object> sane) {
        this.sane = sane;
    }

    public List<String> getQ() {
        return q;
    }

    public void setQ(List<String> q) {
        this.q = q;
    }

    public List<String> getAppKey() {
        return appKey;
    }

    public void setAppKey(List<String> appKey) {
        this.appKey = appKey;
    }

    public List<String> getAppId() {
        return appId;
    }

    public void setAppId(List<String> appId) {
        this.appId = appId;
    }

}
