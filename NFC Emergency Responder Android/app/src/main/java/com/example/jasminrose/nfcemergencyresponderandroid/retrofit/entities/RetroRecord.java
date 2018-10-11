package com.example.jasminrose.nfcemergencyresponderandroid.retrofit.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroRecord {

    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("pk")
    @Expose
    private Integer pk;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}