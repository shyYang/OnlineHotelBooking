package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.*;

public class Customer {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    private String username;

    private String gender;

    private Integer phone;

    private Integer account;

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
    public Integer getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     * @return account
     */
    public Integer getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(Integer account) {
        this.account = account;
    }
}