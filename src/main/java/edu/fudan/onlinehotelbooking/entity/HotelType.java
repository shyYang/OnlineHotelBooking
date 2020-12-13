package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * created by 姜向阳
 * on 2020/12/4
 *
 * 方便注册时接收请求，非实体类
 */
public class HotelType {
    private String password;

    private String hotelName;

    private String address;

    private String phone;

    private String photo;

    private String introduction;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "HotelType: [password="+password+", hotelName="+hotelName+", address="+ address+", introduction="+introduction+"]";
    }
}
