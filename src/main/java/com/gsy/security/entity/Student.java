package com.gsy.security.entity;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1l;

    public String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
