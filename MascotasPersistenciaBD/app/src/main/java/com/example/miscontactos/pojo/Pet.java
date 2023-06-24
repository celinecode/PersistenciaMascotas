package com.example.miscontactos.pojo;

public class Pet {
    private String name;
    private int photo;

    private int id;

    public Pet(String name, int photo) {
        this.name = name;
        this.photo = photo;

    }

    public Pet() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

