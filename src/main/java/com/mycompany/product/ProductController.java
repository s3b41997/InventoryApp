package com.mycompany.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired private ProductService service;

    // handle the http request and return products.html
    // add list of products to attribute for display in products.html
    @GetMapping("/products")
    public String showProductList(Model model){
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "products";
    }

    // handle the http request and return product_form.html
    // for product_form set attribute product as new product
    @GetMapping("/products/new")
    public String showNewForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle","Add New Product");
        return "product_form";
    }

    // handle the http request and return products.html
    // save the object product in database and display success message
    @PostMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes ra){
        service.save(product);
        ra.addFlashAttribute("message","The product has been saved successfully");

        return "redirect:/products";
    }

    // handle the http request and return (product_form.html or products)
    // get the product id and display in edit form
    @GetMapping("/products/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            Product product = service.get(id);
            // for product_form.html set attribute product to display specific product
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit Product (ID: " + id + ")");
            return "product_form";
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/products";
        }
    }

    // handle the http delete request and return products.html
    // delete the product from database and display delete message
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "The product ID " + id + " has been deleted.");
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products";
    }
}