package com.scaler.practiceservicedec24.Controllers;

import com.scaler.practiceservicedec24.Dtos.CreateProductRequestDto;
import com.scaler.practiceservicedec24.Exceptions.ProductNotFoundException;
import com.scaler.practiceservicedec24.Models.Products;
import com.scaler.practiceservicedec24.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }
     // API = Method in the controller
    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/paginated")
    Page<Products> getPaginatedProducts(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return productService.getPaginatedProducts(pageNo, pageSize);
    }

    @GetMapping("/products/{id}")
    /*public Products getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }*/
    public ResponseEntity<Products> getProductById(@PathVariable("id") int id) throws ProductNotFoundException {
        Products product = productService.getProductById(id);
        ResponseEntity<Products> response;

        if(product == null) {
            response = new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
        else response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }
    //Create a product(POST)
    @PostMapping("/products")
    public Products addProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return productService.addProduct(createProductRequestDto.getTitle(),
                createProductRequestDto.getDescription(),
                createProductRequestDto.getCategory(),
                createProductRequestDto.getPrice(),
                createProductRequestDto.getImage());
    }
}
/*
*                       String title,
                        String description,
                        String category,
                        double price,
                        String imageurl*/