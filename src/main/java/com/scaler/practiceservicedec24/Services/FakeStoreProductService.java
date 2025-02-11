package com.scaler.practiceservicedec24.Services;

import com.scaler.practiceservicedec24.Dtos.CreateProductRequestDto;
import com.scaler.practiceservicedec24.Dtos.FakeStoreProductDto;
import com.scaler.practiceservicedec24.Models.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public List<Products> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);

        List<Products> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Products product = fakeStoreProductDto.toProducts();
            products.add(product);
        }
        return products;
    }

    @Override
    public Products getProductById(long id) {

        //FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
        //        FakeStoreProductDto.class);

        Products cacheProduct = (Products) redisTemplate.opsForHash().get("Products", "products_" + id);

        if(cacheProduct != null){
            return cacheProduct;
        }

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity
                ("https://fakestoreapi.com/products/" + id,
                    FakeStoreProductDto.class);

        // Response Entity includes 1xx, 2xx, 3xx, 4xx, 5xx
        if(fakeStoreProductDtoResponseEntity.getStatusCode() != HttpStatus.valueOf(200)){
            //handle this exception
        }
        //fakeStoreProductDtoResponseEntity.getHeaders();

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDto == null){
            return null;
        }

        Products response = fakeStoreProductDto.toProducts();
        redisTemplate.opsForHash().put("Products", "products_"+ id, response);
        return response;
    }

    @Override
    public Products addProduct(String title,
                               String description,
                               String category,
                               double price,
                               String imageurl) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(imageurl);
        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto, FakeStoreProductDto.class);

        return fakeStoreProductDto1.toProducts();
    }

    @Override
    public Page<Products> getPaginatedProducts(int pageNo, int pageSize) {
        return null;
    }
}
