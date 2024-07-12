package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuyerDAOImpl implements BuyerDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BuyerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insertBuyer(Buyer buyer) {
        String sql = "INSERT INTO buyer (user_id) VALUES (?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, buyer.getUserId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Buyer> listAllBuyers() {
        String sql = "SELECT * FROM buyer";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Buyer.class));
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateBuyer(Buyer buyer) {
        String sql = "UPDATE buyer SET user_id = ? WHERE buyer_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, buyer.getUserId(), buyer.getBuyerId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBuyer(Buyer buyer) {
        String sql = "DELETE FROM buyer WHERE buyer_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, buyer.getBuyerId());
            return rowsAffected > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Buyer getBuyer(int buyerId) {
        String sql = "SELECT * FROM buyer WHERE buyer_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Buyer.class), buyerId);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
