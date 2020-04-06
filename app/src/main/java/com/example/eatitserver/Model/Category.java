package com.example.eatitserver.Model;

public class Category {
    private String name;
    private String image;

    public Category(){

    }

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
