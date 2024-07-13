package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.StockDAO;
import com.example.agro_commerce.model.Stock;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockDAO stockDAO;

    @Autowired
    public StockController(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        if (stockDAO.insertStock(stock)) {
            return ResponseEntity.ok(stock);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockDAO.listAllStocks();
        if (stocks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(stocks);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable int id) {
        Stock stock = stockDAO.getStock(id);
        if (stock != null) {
            return ResponseEntity.ok(stock);
        }
        return ResponseEntity.notFound().build();
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable int id, @RequestBody Stock stock) {
        stock.setStockId(id);
        if (stockDAO.updateStock(stock)) {
            return ResponseEntity.ok(stock);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable int id) {
        Stock stock = new Stock();
        stock.setStockId(id);
        if (stockDAO.deleteStock(stock)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }
}
