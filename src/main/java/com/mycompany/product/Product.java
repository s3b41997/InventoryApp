package com.mycompany.product;

import javax.persistence.*;

// creating table 'products' for database 'inventorydb'
@Entity
@Table(name="products")
public class Product {
    // insert the columns in the table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // insert name of the product, varchar(50), NOT NULL, UNIQUE Constraint
    @Column(length = 50, nullable = false, unique = true)
    private String productName;

    // insert price of the product, NOT NULL
    @Column(nullable = false)
    private Integer price;

    // insert amount of the product, NOT NULL
    @Column(nullable = false)
    private Integer quantity;

    // insert comment to the product, varchar(200), NOT NULL
    @Column(length = 200, nullable = false)
    private String comment;

    // Getters + setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", comment='" + comment + '\'' +
                '}';
    }
}
