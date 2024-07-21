package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.LocationDAO;
import com.example.agro_commerce.model.Location;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationDAO locationDAO;

    @Autowired
    public LocationController(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    @SneakyThrows
    @PostMapping("/")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        if (locationDAO.insertLocation(location)) {
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationDAO.listAllLocations();
        if (locations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(locations);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationDAO.getLocation(id);
        if (location != null) {
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable int id, @RequestBody Location location) {
        location.setLocationId(id);
        if (locationDAO.updateLocation(location)) {
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        Location location = new Location();
        location.setLocationId(id);
        if (locationDAO.deleteLocation(location)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }
}
