package com.always_in_beta.ecodrive.model;

import java.util.List;

public class User {
    private String userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String worksAt;
    private List<Double> carbonFootprint;

    public User() {
    }

    public User(String userId, String userName, String userPhone, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getWorksAt() {
        return worksAt;
    }

    public List<Double> getCarbonFootprint() {
        return carbonFootprint;
    }
}
