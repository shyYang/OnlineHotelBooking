package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.*;

public class Customer {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    private String username;

    private String gender;

    private String phone;

    @Column(name = "account")
    private double account;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return account
     */
    public double getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(double account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer:["+
                "userId="+userId+
                ", username="+username+
                ", gender="+gender+
                ",phone="+phone+
                ",account="+account+"]";
    }
}