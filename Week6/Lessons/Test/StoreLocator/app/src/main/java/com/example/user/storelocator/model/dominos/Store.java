package com.example.user.storelocator.model.dominos;

public class Store {
    int storeId;
    String AddressDescription;
    String HoursDescription;

    public Store(int storeId, String addressDescription, String hoursDescription) {
        this.storeId = storeId;
        AddressDescription = addressDescription;
        HoursDescription = hoursDescription;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", AddressDescription='" + AddressDescription + '\'' +
                ", HoursDescription='" + HoursDescription + '\'' +
                '}';
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getAddressDescription() {
        return AddressDescription;
    }

    public void setAddressDescription(String addressDescription) {
        AddressDescription = addressDescription;
    }

    public String getHoursDescription() {
        return HoursDescription;
    }

    public void setHoursDescription(String hoursDescription) {
        HoursDescription = hoursDescription;
    }
}
