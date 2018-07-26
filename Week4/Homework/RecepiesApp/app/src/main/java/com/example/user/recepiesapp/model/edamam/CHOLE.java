
package com.example.user.recepiesapp.model.edamam;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CHOLE implements Parcelable {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("unit")
    @Expose
    private String unit;

    protected CHOLE(Parcel in) {
        label = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readDouble();
        }
        unit = in.readString();
    }

    public static final Creator<CHOLE> CREATOR = new Creator<CHOLE>() {
        @Override
        public CHOLE createFromParcel(Parcel in) {
            return new CHOLE(in);
        }

        @Override
        public CHOLE[] newArray(int size) {
            return new CHOLE[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(label);
        if (quantity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(quantity);
        }
        parcel.writeString(unit);
    }
}
