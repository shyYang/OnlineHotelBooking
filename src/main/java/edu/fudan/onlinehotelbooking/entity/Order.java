package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "orders")
public class Order {


    @Column(name = "room_id")
    private int room_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "hotel_id")
    private int hotelId;


    //0表示未支付，1表示已支付但未入住，2表示入住中，3表示已退房但未评论，4表示已评论
    @Column(name = "status")
    private int status;

    @Column(name = "time")
    private Date time;

    @Column(name = "payment")
    private double payment;

    @Id
    @Column(name = "order_id")
    private int order_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
