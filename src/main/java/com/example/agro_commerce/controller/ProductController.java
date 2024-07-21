package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.ProductDAO;
import com.example.agro_commerce.model.Product;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @SneakyThrows
    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (productDAO.insertProduct(product)) {

            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(500).build();
    }
    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productDAO.listAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getAllProductsByName(@PathVariable String name) {
        List<Product> products = productDAO.listAllProductsByName(name);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(products);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productDAO.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setProductId(id);
        if (productDAO.updateProduct(product)) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        Product product = new Product();
        product.setProductId(id);
        if (productDAO.deleteProduct(product)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }
}


