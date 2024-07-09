package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Buyer;

import java.sql.SQLException;
import java.util.List;

public interface BuyerDAO {
    boolean insertBuyer(Buyer buyer) throws SQLException;
    List<Buyer> listAllBuyers() throws SQLException;
    boolean deleteBuyer(Buyer buyer) throws SQLException;
    boolean updateBuyer(Buyer buyer) throws SQLException;
    Buyer getBuyer(int buyerId) throws SQLException;
}
