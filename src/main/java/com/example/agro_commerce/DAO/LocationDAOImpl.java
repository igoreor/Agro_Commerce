package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Location;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public LocationDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("DRIVE_CLASS_NAME");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    @Override
    public boolean insertLocation(Location location) throws SQLException {
        String sql = "INSERT INTO locationn (seller_id, street, neighborhood, number_house, city) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, location.getSellerId());
            statement.setString(2, location.getStreet());
            statement.setString(3, location.getNeighborhood());
            statement.setInt(4, location.getNumberHouse());
            statement.setString(5, location.getCity());

            rowInserted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowInserted;
    }

    @Override
    public List<Location> listAllLocations() throws SQLException {
        List<Location> listLocation = new ArrayList<>();
        String sql = "SELECT * FROM locationn";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int locationId = resultSet.getInt("location_id");
                int sellerId = resultSet.getInt("seller_id");
                String street = resultSet.getString("street");
                String neighborhood = resultSet.getString("neighborhood");
                int numberHouse = resultSet.getInt("number_house");
                String city = resultSet.getString("city");

                Location location = new Location(locationId, sellerId, street, neighborhood, city, numberHouse);
                listLocation.add(location);
            }
        } finally {
            disconnect();
        }

        return listLocation;
    }

    @Override
    public boolean updateLocation(Location location) throws SQLException {
        String sql = "UPDATE locationn SET seller_id = ?, street = ?, neighborhood = ?, number_house = ?, city = ? WHERE location_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, location.getSellerId());
            statement.setString(2, location.getStreet());
            statement.setString(3, location.getNeighborhood());
            statement.setInt(4, location.getNumberHouse());
            statement.setString(5, location.getCity());
            statement.setInt(6, location.getLocationId());

            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowUpdated;
    }

    @Override
    public boolean deleteLocation(Location location) throws SQLException {
        String sql = "DELETE FROM locationn WHERE location_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, location.getLocationId());

            rowDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowDeleted;
    }

    @Override
    public Location getLocation(int locationId) throws SQLException {
        Location location = null;
        String sql = "SELECT * FROM locationn WHERE location_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, locationId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int sellerId = resultSet.getInt("seller_id");
                    String street = resultSet.getString("street");
                    String neighborhood = resultSet.getString("neighborhood");
                    int numberHouse = resultSet.getInt("number_house");
                    String city = resultSet.getString("city");

                    location = new Location(locationId, sellerId, street, neighborhood, city, numberHouse);
                }
            }
        } finally {
            disconnect();
        }

        return location;
    }
}
