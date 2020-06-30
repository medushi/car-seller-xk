package com.example.carsellerxk.Models;

public class PostsModel {
    private String postOwnerName;
    private String postTitle;
    private String postLocation;
    private String accType;
    private String manufacturer;
    private String img1;
    private String img2;
    private String img3;
    private int yearOfProduction;
    private double price;

    public PostsModel(String postOwnerName, String postTitle, String postLocation, String accType, String manufacturer, String img1, String img2, String img3,int yearOfProduction, double price) {
        this.postOwnerName = postOwnerName;
        this.postTitle = postTitle;
        this.postLocation = postLocation;
        this.accType = accType;
        this.manufacturer = manufacturer;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.yearOfProduction=yearOfProduction;
        this.price = price;
    }

    public String getPostOwnerName() {
        return postOwnerName;
    }

    public void setPostOwnerName(String postOwnerName) {
        this.postOwnerName = postOwnerName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(String postLocation) {
        this.postLocation = postLocation;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
