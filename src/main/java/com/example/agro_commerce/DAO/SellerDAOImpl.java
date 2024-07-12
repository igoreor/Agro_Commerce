package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellerDAOImpl implements SellerDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SellerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insertSeller(Seller seller) {
        String sql = "INSERT INTO seller (user_id) VALUES (?)";
        try {
            int rowsAffected = jdbcTemplate.update(sql, seller.getUserId());
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            // Handle exception (log, throw custom exception, etc.)
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Seller> listAllSellers() {
        String sql = "SELECT * FROM seller";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Seller.class));
        } catch (Exception e) {
            // Handle exception (log, throw custom exception, etc.)
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateSeller(Seller seller) {
        String sql = "UPDATE seller SET user_id = ? WHERE seller_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, seller.getUserId(), seller.getSellerId());
            return rowsAffected > 0;
        } catch (Exception e) {
            // Handle exception (log, throw custom exception, etc.)
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSeller(Seller seller) {
        String sql = "DELETE FROM seller WHERE seller_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, seller.getSellerId());
            return rowsAffected > 0;
        } catch (Exception e) {
            // Handle exception (log, throw custom exception, etc.)
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Seller getSeller(int sellerId) {
        String sql = "SELECT * FROM seller WHERE seller_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Seller.class), sellerId);
        } catch (Exception e) {
            // Handle exception (log, throw custom exception, etc.)
            e.printStackTrace();
            return null;
        }
    }
}
