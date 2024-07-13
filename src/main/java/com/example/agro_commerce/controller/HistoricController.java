package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.HistoricDAO;
import com.example.agro_commerce.model.Historic;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historics")
public class HistoricController {

    private final HistoricDAO historicDAO;

    @Autowired
    public HistoricController(HistoricDAO historicDAO) {
        this.historicDAO = historicDAO;
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<Historic> createHistoric(@RequestBody Historic historic) {
        if (historicDAO.insertHistoric(historic)) {
            return ResponseEntity.ok(historic);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<Historic>> getAllHistorics() {
        List<Historic> historics = historicDAO.listAllHistorics();
        if (historics.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historics);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Historic> getHistoricById(@PathVariable int id) {
        Historic historic = historicDAO.getHistoric(id);
        if (historic != null) {
            return ResponseEntity.ok(historic);
        }
        return ResponseEntity.notFound().build();
    }
    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Historic> updateHistoric(@PathVariable int id, @RequestBody Historic historic) {
        historic.setHistoricId(id);
        if (historicDAO.updateHistoric(historic)) {
            return ResponseEntity.ok(historic);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoric(@PathVariable int id) {
        Historic historic = new Historic();
        historic.setHistoricId(id);
        if (historicDAO.deleteHistoric(historic)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }
}
