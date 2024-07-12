package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockDAOImpl implements StockDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StockDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insertStock(Stock stock) {
        String sql = "INSERT INTO stock (product_id, seller_id, amount) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, stock.getProductId(), stock.getSellerId(), stock.getAmount());
        return rowsAffected > 0;
    }

    @Override
    public List<Stock> listAllStocks() {
        String sql = "SELECT * FROM stock";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Stock.class));
    }

    @Override
    public boolean updateStock(Stock stock) {
        String sql = "UPDATE stock SET product_id = ?, seller_id = ?, amount = ? WHERE stock_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, stock.getProductId(), stock.getSellerId(), stock.getAmount(), stock.getStockId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteStock(Stock stock) {
        String sql = "DELETE FROM stock WHERE stock_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, stock.getStockId());
        return rowsAffected > 0;
    }

    @Override
    public Stock getStock(int stockId) {
        String sql = "SELECT * FROM stock WHERE stock_id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Stock.class), stockId);
    }
}
