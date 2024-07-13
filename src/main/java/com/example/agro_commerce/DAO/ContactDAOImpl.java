package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDAOImpl implements ContactDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean insertContact(Contact contact) {
        String sql = "INSERT INTO contact (buyer_id, seller_id, phone_number) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, contact.getBuyerId(), contact.getSellerId(), contact.getPhoneNumber());
        return rowsAffected > 0;
    }

    @Override
    public List<Contact> listAllContacts() {
        String sql = "SELECT * FROM contact";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Contact.class));
    }

    @Override
    public boolean updateContact(Contact contact) {
        String sql = "UPDATE contact SET buyer_id = ?, seller_id = ?, phone_number = ? WHERE contact_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, contact.getBuyerId(), contact.getSellerId(), contact.getPhoneNumber(), contact.getContactId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteContact(Contact contact) {
        String sql = "DELETE FROM contact WHERE contact_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, contact.getContactId());
        return rowsAffected > 0;
    }

    @Override
    public Contact getContact(int contactId) {
        String sql = "SELECT * FROM contact WHERE contact_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Contact.class), contactId);
    }
}
