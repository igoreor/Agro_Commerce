package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.ProductDAO;
import com.example.agro_commerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(
            @RequestParam("type") String type,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("value") BigDecimal value,
            @RequestParam("image") MultipartFile image) throws IOException, SQLException {

        // Salvar a imagem
        String imagePath = saveImage(image);

        // Criar o produto
        Product product = new Product(0, type, name, value, description, imagePath);

        if (productDAO.insertProduct(product)) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(500).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() throws SQLException {
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

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) throws SQLException {
        Product product = productDAO.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestParam("type") String type,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("value") BigDecimal value,
            @RequestParam("image") MultipartFile image) throws IOException, SQLException {

        String imagePath = saveImage(image);

        Product product = new Product(id, type, name, value, description, imagePath);
        if (productDAO.updateProduct(product)) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(500).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) throws SQLException {
        Product product = new Product();
        product.setProductId(id);
        if (productDAO.deleteProduct(product)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }

    private String saveImage(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            return null;
        }

        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path imagePath = Paths.get("images/" + fileName);

        Files.createDirectories(imagePath.getParent());
        Files.write(imagePath, image.getBytes());

        return imagePath.toString();
    }
}
