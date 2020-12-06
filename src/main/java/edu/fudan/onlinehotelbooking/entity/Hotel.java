package edu.fudan.onlinehotelbooking.entity;

import java.math.BigDecimal;
import javax.persistence.*;

public class Hotel {
    @Id
    @Column(name = "hotel_id")
    private Integer hotelId;

    /**
     * 负责人id
     */
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "hotel_name")
    private String hotelName;

    private String address;

    private String phone;

    private String photo;

    private BigDecimal rating;

    /**
     * 已入住人数
     */
    @Column(name = "guest_number")
    private Integer guestNumber;

    private String introduction;

    /**
     * @return hotel_id
     */
    public Integer getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId
     */
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * 获取负责人id
     *
     * @return user_id - 负责人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置负责人id
     *
     * @param userId 负责人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return hotel_name
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return rating
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * @param rating
     */
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    /**
     * 获取已入住人数
     *
     * @return guest_number - 已入住人数
     */
    public Integer getGuestNumber() {
        return guestNumber;
    }

    /**
     * 设置已入住人数
     *
     * @param guestNumber 已入住人数
     */
    public void setGuestNumber(Integer guestNumber) {
        this.guestNumber = guestNumber;
    }

    /**
     * @return introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

