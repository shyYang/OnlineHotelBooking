package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class RoomType {
    @Id
    @Column(name = "type_id")
    private int type_id;

    @Column(name = "hotel_id")
    private int hotel_id;

    @Column(name = "price")
    private int price;

    @Column(name = "photo")
    private int photo;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
