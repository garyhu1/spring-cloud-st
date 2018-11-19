package com.garyhu.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Administrator
 * @decripetion :
 * @since : 2018/11/5
 **/
public class Animal implements Serializable {

    int id;
    String name;
    Date date = new Date();

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Animal getSampleAnimal(){
        Animal animal = new Animal();
        animal.setId(123);
        animal.setName("snobby");
        return animal;
    }
}
