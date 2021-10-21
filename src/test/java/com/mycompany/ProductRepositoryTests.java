package com.mycompany;

import com.mycompany.product.Product;
import com.mycompany.product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired private ProductRepository repo;

    // add new object to datasource products
    @Test
    public void testAddNew(){
        Product product = new Product();
        product.setProductName("Chleb");
        product.setPrice(4);
        product.setQuantity(50);
        product.setComment("Smaczne pieczywo");
        Product savedProduct = repo.save(product);


        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }

    // display in console all products attributes from datasource products
    @Test
    public void testListAll(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);

        for(Product product: products){
            System.out.println(product);
        }
    }

    // modify product name of specific object in datasource products
    @Test
    public void testUpdate(){
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);
        Product product = optionalProduct.get();
        product.setProductName("Jajka");
        repo.save(product);

        Product updatedProduct = repo.findById(productId).get();
        Assertions.assertThat(updatedProduct.getProductName()).isEqualTo("Jajka");

    }

    // check if there is a product with the specific id in datasource products and display in console
    @Test
    public void testGetId(){
        Integer productId = 1;
        Optional<Product> optionalProduct = repo.findById(productId);

        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }

    // check if there is a product with the specific name in datasource products
    @Test
    public void testGetProductName(){
        String productName = "Chleb";
        Product product = repo.findByProductName(productName);
        Assertions.assertThat(product).isNotNull();
        System.out.println(product);
    }

    // delete specific object from datasource products
    @Test
    public void testDelete(){
        Integer productId = 2;
        repo.deleteById(productId);

        Optional<Product> optionalProduct = repo.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
}
