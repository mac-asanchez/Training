
package com.example.user.recepiesapp.model.edamam;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Digest implements Parcelable {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("schemaOrgTag")
    @Expose
    private Object schemaOrgTag;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("hasRDI")
    @Expose
    private Boolean hasRDI;
    @SerializedName("daily")
    @Expose
    private Double daily;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("sub")
    @Expose
    private List<Sub> sub = null;

    protected Digest(Parcel in) {
        label = in.readString();
        tag = in.readString();
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readDouble();
        }
        byte tmpHasRDI = in.readByte();
        hasRDI = tmpHasRDI == 0 ? null : tmpHasRDI == 1;
        if (in.readByte() == 0) {
            daily = null;
        } else {
            daily = in.readDouble();
        }
        unit = in.readString();
    }

    public static final Creator<Digest> CREATOR = new Creator<Digest>() {
        @Override
        public Digest createFromParcel(Parcel in) {
            return new Digest(in);
        }

        @Override
        public Digest[] newArray(int size) {
            return new Digest[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getSchemaOrgTag() {
        return schemaOrgTag;
    }

    public void setSchemaOrgTag(Object schemaOrgTag) {
        this.schemaOrgTag = schemaOrgTag;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getHasRDI() {
        return hasRDI;
    }

    public void setHasRDI(Boolean hasRDI) {
        this.hasRDI = hasRDI;
    }

    public Double getDaily() {
        return daily;
    }

    public void setDaily(Double daily) {
        this.daily = daily;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Sub> getSub() {
        return sub;
    }

    public void setSub(List<Sub> sub) {
        this.sub = sub;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(label);
        parcel.writeString(tag);
        if (total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(total);
        }
        parcel.writeByte((byte) (hasRDI == null ? 0 : hasRDI ? 1 : 2));
        if (daily == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(daily);
        }
        parcel.writeString(unit);
    }
}
