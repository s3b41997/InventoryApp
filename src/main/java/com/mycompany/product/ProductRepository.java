package com.mycompany.product;

import com.mycompany.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.productName = ?1")
    public Product findByProductName(String name);
}
