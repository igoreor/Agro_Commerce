package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Location;

import java.sql.SQLException;
import java.util.List;

public interface LocationDAO {

    boolean insertLocation(Location location) throws SQLException;

    List<Location> listAllLocations() throws SQLException;

    boolean updateLocation(Location location) throws SQLException;

    boolean deleteLocation(Location location) throws SQLException;

    Location getLocation(int locationId) throws SQLException;
}
