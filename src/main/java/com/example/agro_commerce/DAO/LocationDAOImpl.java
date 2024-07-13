package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertLocation(Location location) {
        String sql = "INSERT INTO locationn (seller_id, street, neighborhood, number_house, city) VALUES (?, ?, ?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, location.getSellerId(), location.getStreet(), location.getNeighborhood(), location.getNumberHouse(), location.getCity());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Location> listAllLocations() {
        String sql = "SELECT * FROM locationn";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Location.class));
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateLocation(Location location) {
        String sql = "UPDATE locationn SET seller_id = ?, street = ?, neighborhood = ?, number_house = ?, city = ? WHERE location_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, location.getSellerId(), location.getStreet(), location.getNeighborhood(), location.getNumberHouse(), location.getCity(), location.getLocationId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteLocation(Location location) {
        String sql = "DELETE FROM locationn WHERE location_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, location.getLocationId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Location getLocation(int locationId) {
        String sql = "SELECT * FROM locationn WHERE location_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Location.class), locationId);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
