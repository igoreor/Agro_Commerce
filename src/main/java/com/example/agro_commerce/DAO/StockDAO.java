package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockDAO {

    boolean insertStock(Stock stock) throws SQLException;

    List<Stock> listAllStocks() throws SQLException;

    boolean updateStock(Stock stock) throws SQLException;

    boolean deleteStock(Stock stock) throws SQLException;

    Stock getStock(int stockId) throws SQLException;
}
