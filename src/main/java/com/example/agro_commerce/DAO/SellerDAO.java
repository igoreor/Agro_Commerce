package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Seller;

import java.sql.SQLException;
import java.util.List;

public interface SellerDAO {

    boolean insertSeller(Seller seller) throws SQLException;

    List<Seller> listAllSellers() throws SQLException;

    boolean updateSeller(Seller seller) throws SQLException;

    boolean deleteSeller(Seller seller) throws SQLException;

    Seller getSeller(int sellerId) throws SQLException;
}
