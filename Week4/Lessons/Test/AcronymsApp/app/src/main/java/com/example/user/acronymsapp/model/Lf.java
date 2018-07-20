
package com.example.user.acronymsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lf implements Parcelable {

    @SerializedName("lf")
    @Expose
    private String lf;
    @SerializedName("freq")
    @Expose
    private Integer freq;
    @SerializedName("since")
    @Expose
    private Integer since;
    @SerializedName("vars")
    @Expose
    private List<Var> vars = null;

    protected Lf(Parcel in) {
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

    public static final Creator<Lf> CREATOR = new Creator<Lf>() {
        @Override
        public Lf createFromParcel(Parcel in) {
            return new Lf(in);
        }

        @Override
        public Lf[] newArray(int size) {
            return new Lf[size];
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

    public List<Var> getVars() {
        return vars;
    }

    public void setVars(List<Var> vars) {
        this.vars = vars;
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
