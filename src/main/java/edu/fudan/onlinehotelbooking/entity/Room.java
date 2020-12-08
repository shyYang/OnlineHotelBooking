package edu.fudan.onlinehotelbooking.entity;

import io.swagger.models.auth.In;

import javax.persistence.*;

public class Room {
    @Id
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 酒店房间号
     */
    @Column(name = "room_number")
    private Integer roomNumber;

    private Integer status;

    /**
     * @return room_id
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * @param roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

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
     * 获取酒店房间号
     *
     * @return room_number - 酒店房间号
     */
    public Integer getRoomNumber() {
        return roomNumber;
    }

    /**
     * 设置酒店房间号
     *
     * @param roomNumber 酒店房间号
     */
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
