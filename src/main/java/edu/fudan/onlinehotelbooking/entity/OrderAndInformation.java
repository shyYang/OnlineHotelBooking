package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class OrderAndInformation {

    private int roomId;
    //private int userId;
    //0表示未支付，1表示已支付但未入住，2表示入住中，3表示已退房但未评论，4表示已评论
    private int status;
    //yyyy-MM-dd hh:MM:ss
    private Date time;
    private double payment;
    //private int hotelId;
    //private int orderId;
    private String username;
    private String gender;
    private String phone;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //    @Override
//    public String toString() {
//        return "Order:[orderId="+orderId+", roomId="+roomId+"]";
//    }
}
