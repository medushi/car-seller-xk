package com.example.carsellerxk.Models;

public class TestModel {
    private String UserEmail;
    private String UserPassword;
    private String Uid;

    public TestModel (String UserEmail,String UserPassword,String Uid){
        this.UserEmail=UserEmail;
        this.UserPassword=UserPassword;
        this.Uid=Uid;
    }
    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String Uid) {
        this.Uid = Uid;
    }

}
