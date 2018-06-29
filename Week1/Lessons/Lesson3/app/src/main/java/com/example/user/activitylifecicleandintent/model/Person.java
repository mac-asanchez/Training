package com.example.user.activitylifecicleandintent.model;

import java.io.Serializable;

public class Person implements Serializable {
    String name;
    String age;
    String gender;

    public Person(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
}
