package com.example.iqbal.banglarkrishok.Model;

public class Upload {
    private String name;
    private String phone;
    private String upozila;
    private String zila;
    private String agroType;
    private String imageUrl;
    private String date_and_time;
    private String additionalInfo;


    public Upload(){}

    public Upload(String name, String phone, String upozila, String zila, String agroType, String additionalInfo,String imageUrl, String date_and_time) {
        this.name = name;
        this.phone = phone;
        this.upozila = upozila;
        this.zila = zila;
        this.agroType = agroType;
        this.additionalInfo = additionalInfo;
        this.imageUrl = imageUrl;
        this.date_and_time = date_and_time;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUpozila() {
        return upozila;
    }

    public void setUpozila(String upozila) {
        this.upozila = upozila;
    }

    public String getZila() {
        return zila;
    }

    public void setZila(String zila) {
        this.zila = zila;
    }

    public String getAgroType() {
        return agroType;
    }

    public void setAgroType(String agroType) {
        this.agroType = agroType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(String date_and_time) {
        this.date_and_time = date_and_time;
    }
}
