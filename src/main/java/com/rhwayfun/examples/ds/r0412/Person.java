package com.rhwayfun.examples.ds.r0412;

import java.io.Serializable;

/**
 * Created by rhwayfun on 16-4-12.
 */
public class Person implements Serializable{
    private int id;
    private String name;

    public Person(){}

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
