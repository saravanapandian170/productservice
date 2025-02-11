package com.scaler.practiceservicedec24.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Products extends BaseModel{

    /*private long id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String image;


    public Products(long id, String title, String description, double price, Category category, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public Products() {
    }*/



    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public Category getCategory() {
        return this.category;
    }

    public String getImage() {
        return this.image;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String title;
    private String description;
    private double price;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    private String image;
}
