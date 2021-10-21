package com.mycompany.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private  ProductRepository repo;

    // function responsible for get list of the products from datasource products
    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
    }

    // function responsible for add the product to datasource products
    public void save(Product product){
        repo.save(product);
    }

    // function responsible for get id of the product
    public Product get(Integer id) throws ProductNotFoundException{
        Optional<Product> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProductNotFoundException("Could not find any products with ID " + id);
    }

    // function responsible for removing the product from datasource products
    public void delete(Integer id) throws ProductNotFoundException{
        if(id == null || id == 0){
            throw new ProductNotFoundException("Could not find any products with ID" + id);
        }
        repo.deleteById(id);

    }
}
