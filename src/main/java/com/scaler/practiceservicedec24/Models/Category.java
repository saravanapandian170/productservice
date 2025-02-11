package com.scaler.practiceservicedec24.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Category extends BaseModel{


    private String title;


    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Products> products;



    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public List<Products> getProducts() {
        return this.products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

}

