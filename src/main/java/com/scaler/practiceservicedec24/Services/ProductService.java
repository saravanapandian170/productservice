package com.scaler.practiceservicedec24.Services;

import com.scaler.practiceservicedec24.Dtos.CreateProductRequestDto;
import com.scaler.practiceservicedec24.Exceptions.ProductNotFoundException;
import com.scaler.practiceservicedec24.Models.Products;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Products> getAllProducts();
    Products getProductById(long id) throws ProductNotFoundException;
    Products addProduct(String title,
                        String description,
                        String category,
                        double price,
                        String imageurl);

    Page<Products> getPaginatedProducts(int pageNo, int pageSize);
}
