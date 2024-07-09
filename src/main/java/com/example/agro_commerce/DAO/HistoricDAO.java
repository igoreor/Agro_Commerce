package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Historic;

import java.sql.SQLException;
import java.util.List;

public interface HistoricDAO {
    boolean insertHistoric(Historic historic) throws SQLException;
    List<Historic> listAllHistorics() throws SQLException;
    boolean updateHistoric(Historic historic) throws SQLException;
    boolean deleteHistoric(Historic historic) throws SQLException;
    Historic getHistoric(int historicId) throws SQLException;
}
