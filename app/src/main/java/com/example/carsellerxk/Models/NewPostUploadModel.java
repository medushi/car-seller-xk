package com.example.carsellerxk.Models;

import java.util.List;

public class NewPostUploadModel {
    private String Uid;
    private String Title;
    private String City;
    private String YearOfProduction;
    private String TypeOfAccelerate;
    private String Manufacturer;
    private double Price;
    private List<String> Images;

    public NewPostUploadModel(String uid, String title, String city, String yearOfProduction, String typeOfAccelerate, String manufacturer, double price, List<String> images) {
        Uid = uid;
        Title = title;
        City = city;
        YearOfProduction = yearOfProduction;
        TypeOfAccelerate = typeOfAccelerate;
        Manufacturer = manufacturer;
        Price = price;
        Images = images;
    }


    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getYearOfProduction() {
        return YearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        YearOfProduction = yearOfProduction;
    }

    public String getTypeOfAccelerate() {
        return TypeOfAccelerate;
    }

    public void setTypeOfAccelerate(String typeOfAccelerate) {
        TypeOfAccelerate = typeOfAccelerate;
    }


    public List<String> getImages() {
        return Images;
    }

    public void setImages(List<String> images) {
        Images = images;
    }
    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
