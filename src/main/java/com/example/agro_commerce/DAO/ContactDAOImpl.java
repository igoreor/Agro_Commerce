package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDAOImpl implements ContactDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insertContact(Contact contact) {
        String sql = "INSERT INTO contacts (seller_id, buyer_id, phone_number) VALUES (?, ?, ?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, contact.getSellerId(), contact.getBuyerId(), contact.getPhoneNumber());
            if (rowsAffected > 0) {

                return true;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Contact> listAllContacts() {
        String sql = "SELECT * FROM contacts";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Contact.class));
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateContact(Contact contact) {
        String sql = "UPDATE contacts SET seller_id = ?, buyer_id = ?, phone_number = ? WHERE contact_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, contact.getSellerId(), contact.getBuyerId(), contact.getPhoneNumber(), contact.getContactId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteContact(Contact contact) {
        String sql = "DELETE FROM contacts WHERE contact_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, contact.getContactId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Contact getContact(int contactId) {
        String sql = "SELECT * FROM contacts WHERE contact_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Contact.class), contactId);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
