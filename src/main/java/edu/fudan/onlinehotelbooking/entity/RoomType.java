package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.*;

@Table(name = "room_type")
public class RoomType {

    @Column(name = "hotel_id")
    private int hotelId;
    @Column(name = "price")
    private double price;

    private String photo;

    private Integer number;

    private String name;

    private String introduction;

    private Integer freeNumber;

    @Id
    @Column(name = "type_id")
    private Integer typeId;


    /**
     * @return type_id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * @return hotel_id
     */
    public int getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId
     */
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
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
     * @return number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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

    public Integer getFreeNumber() {
        return freeNumber;
    }

    public void setFreeNumber(Integer freeNumber) {
        this.freeNumber = freeNumber;
    }
}
