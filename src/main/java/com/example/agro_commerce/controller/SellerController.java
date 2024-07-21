package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.SellerDAO;
import com.example.agro_commerce.model.Seller;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerDAO sellerDAO;

    @Autowired
    public SellerController(SellerDAO sellerDAO) {
        this.sellerDAO = sellerDAO;
    }

    @SneakyThrows
    @PostMapping("/")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        if (sellerDAO.insertSeller(seller)) {
            return ResponseEntity.ok(seller);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerDAO.listAllSellers();
        if (sellers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sellers);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable int id) {
        Seller seller = sellerDAO.getSeller(id);
        if (seller != null) {
            return ResponseEntity.ok(seller);
        }
        return ResponseEntity.notFound().build();
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable int id, @RequestBody Seller seller) {
        seller.setSellerId(id);
        if (sellerDAO.updateSeller(seller)) {
            return ResponseEntity.ok(seller);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable int id) {
        Seller seller = new Seller();
        seller.setSellerId(id);
        if (sellerDAO.deleteSeller(seller)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }
}
