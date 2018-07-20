
package com.example.user.acronymsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Var implements Parcelable {

    @SerializedName("lf")
    @Expose
    private String lf;
    @SerializedName("freq")
    @Expose
    private Integer freq;
    @SerializedName("since")
    @Expose
    private Integer since;

    protected Var(Parcel in) {
        lf = in.readString();
        if (in.readByte() == 0) {
            freq = null;
        } else {
            freq = in.readInt();
        }
        if (in.readByte() == 0) {
            since = null;
        } else {
            since = in.readInt();
        }
    }

    public static final Creator<Var> CREATOR = new Creator<Var>() {
        @Override
        public Var createFromParcel(Parcel in) {
            return new Var(in);
        }

        @Override
        public Var[] newArray(int size) {
            return new Var[size];
        }
    };

    public String getLf() {
        return lf;
    }

    public void setLf(String lf) {
        this.lf = lf;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public Integer getSince() {
        return since;
    }

    public void setSince(Integer since) {
        this.since = since;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lf);
        if (freq == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(freq);
        }
        if (since == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(since);
        }
    }
}
