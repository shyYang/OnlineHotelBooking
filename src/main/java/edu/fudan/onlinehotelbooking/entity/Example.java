package edu.fudan.onlinehotelbooking.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * created by 姜向阳
 * on 2020/11/27
 */
public class Example {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "school")
    private String school;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
