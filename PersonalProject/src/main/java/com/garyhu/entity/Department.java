package com.garyhu.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Administrator
 * @decripetion :
 * @since : 2018/10/28
 **/
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    // 简化模式不需要该属性
//    @OneToMany(mappedBy = "department")
//    private List<User> users = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
