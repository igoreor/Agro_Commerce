package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.BuyerDAO;
import com.example.agro_commerce.model.Buyer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyers")
public class BuyerController {

    private final BuyerDAO buyerDAO;

    @Autowired
    public BuyerController(BuyerDAO buyerDAO) {
        this.buyerDAO = buyerDAO;
    }

    @SneakyThrows
    @PostMapping ("/")
    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
        if (buyerDAO.insertBuyer(buyer)) {
            return ResponseEntity.ok(buyer);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<List<Buyer>> getAllBuyers() {
        List<Buyer> buyers = buyerDAO.listAllBuyers();
        if (buyers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(buyers);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable int id) {
        Buyer buyer = buyerDAO.getBuyer(id);
        if (buyer != null) {
            return ResponseEntity.ok(buyer);
        }
        return ResponseEntity.notFound().build();
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Buyer> updateBuyer(@PathVariable int id, @RequestBody Buyer buyer) {
        buyer.setBuyerId(id);
        if (buyerDAO.updateBuyer(buyer)) {
            return ResponseEntity.ok(buyer);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable int id) {
        Buyer buyer = new Buyer();
        buyer.setBuyerId(id);
        if (buyerDAO.deleteBuyer(buyer)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }
}
