package com.ogunkuade.productservice.repository;

import com.ogunkuade.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Boolean existsProductBySellerId(Long id);
    Product findProductByName(String name);
    List<Product> findProductsBySellerId(Long id);

}
