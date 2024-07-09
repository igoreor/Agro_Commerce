package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public ContactDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

    public boolean insertContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (seller_id, buyer_id, phone_number) VALUES (?, ?, ?)";
        boolean rowInserted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, contact.getSellerId());
            statement.setInt(2, contact.getBuyerId());
            statement.setString(3, contact.getPhoneNumber());

            rowInserted = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowInserted;
    }

    public List<Contact> listAllContacts() throws SQLException {
        List<Contact> listContact = new ArrayList<>();
        String sql = "SELECT * FROM contacts";

        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int contactId = resultSet.getInt("contact_id");
                int sellerId = resultSet.getInt("seller_id");
                int buyerId = resultSet.getInt("buyer_id");
                String phoneNumber = resultSet.getString("phone_number");

                Contact contact = new Contact(contactId, sellerId, buyerId, phoneNumber);
                listContact.add(contact);
            }
        }

        disconnect();

        return listContact;
    }

    public boolean deleteContact(Contact contact) throws SQLException {
        String sql = "DELETE FROM contacts WHERE contact_id = ?";
        boolean rowDeleted = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, contact.getContactId());

            rowDeleted = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowDeleted;
    }

    public boolean updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET seller_id = ?, buyer_id = ?, phone_number = ? WHERE contact_id = ?";
        boolean rowUpdated = false;

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, contact.getSellerId());
            statement.setInt(2, contact.getBuyerId());
            statement.setString(3, contact.getPhoneNumber());
            statement.setInt(4, contact.getContactId());

            rowUpdated = statement.executeUpdate() > 0;
        }

        disconnect();

        return rowUpdated;
    }

    public Contact getContact(int contactId) throws SQLException {
        Contact contact = null;
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, contactId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int sellerId = resultSet.getInt("seller_id");
                    int buyerId = resultSet.getInt("buyer_id");
                    String phoneNumber = resultSet.getString("phone_number");

                    contact = new Contact(contactId, sellerId, buyerId, phoneNumber);
                }
            }
        }

        disconnect();

        return contact;
    }
}
