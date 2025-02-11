package com.scaler.practiceservicedec24.Services;

import com.scaler.practiceservicedec24.Exceptions.ProductNotFoundException;
import com.scaler.practiceservicedec24.Models.Category;
import com.scaler.practiceservicedec24.Models.Products;
import com.scaler.practiceservicedec24.Repositories.CategoryRepository;
import com.scaler.practiceservicedec24.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Products> getAllProducts() {
        List<Products> products = productRepository.findAll();
        return products;
    }

    @Override
    public Products getProductById(long id) throws ProductNotFoundException {
        Optional<Products> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with the given id is not found");
        }
        return product.get();
    }

    @Override
    public Products addProduct(String title, String description, String category, double price, String imageurl) {

        Products product = new Products();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(imageurl);

        Category categoryFromDB = categoryRepository.findByTitle(category);

        if(categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);
            product.setCategory(newCategory);
        }else {
            product.setCategory(categoryFromDB);
        }

        Products createdProduct = productRepository.save(product);
        return createdProduct;
    }

    @Override
    public Page<Products> getPaginatedProducts(int pageNo, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by("title").and(Sort.by("price").ascending())));
    }
}
