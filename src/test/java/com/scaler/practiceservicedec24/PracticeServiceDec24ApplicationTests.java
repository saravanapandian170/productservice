package com.scaler.practiceservicedec24;

import com.scaler.practiceservicedec24.Models.Category;
import com.scaler.practiceservicedec24.Models.Products;
import com.scaler.practiceservicedec24.Projections.ProductWithIdAndPriceProjection;
import com.scaler.practiceservicedec24.Repositories.CategoryRepository;
import com.scaler.practiceservicedec24.Repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class PracticeServiceDec24ApplicationTests {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Test
    void contextLoads() {
    }
    @Test
    void testProductRepository() {
        //List<Products> products = productRepository.findAllByCategory_Title("books");
        //System.out.println(products);

        List<ProductWithIdAndPriceProjection> product = productRepository.getProductWithTitleAndPriceAndGivenCategory("books");
        for(ProductWithIdAndPriceProjection productWithIdAndPriceProjection : product) {
            System.out.println(productWithIdAndPriceProjection.getPrice() + " " + productWithIdAndPriceProjection.getId());
        }

        //Optional<Products> product1 = productRepository.findById(2l);
        //System.out.println(product1);

        Optional<Category> category1 = categoryRepository.findById(1l);
        System.out.println(category1.get());
     }
}
