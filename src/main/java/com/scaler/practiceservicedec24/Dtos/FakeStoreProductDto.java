package com.scaler.practiceservicedec24.Dtos;

import com.scaler.practiceservicedec24.Models.Category;
import com.scaler.practiceservicedec24.Models.Products;

public class FakeStoreProductDto {

    private long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;




    public Products toProducts(){
        Products products = new Products();

        products.setId(id);
        products.setTitle(title);
        products.setDescription(description);
        products.setPrice(price);
        products.setImage(image);

        Category category1 = new Category();
        category1.setTitle(category);
        products.setCategory(category1);

        return products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
