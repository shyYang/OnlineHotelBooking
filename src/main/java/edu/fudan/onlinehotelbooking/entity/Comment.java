package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "comment_id")
    private int comment_id;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private double rating;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "order_id")
    private int order_id;

    //yyyy-MM-dd hh:MM:ss
    @Column(name = "time")
    private Date time;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment:[content="+content+"]";
    }
}
