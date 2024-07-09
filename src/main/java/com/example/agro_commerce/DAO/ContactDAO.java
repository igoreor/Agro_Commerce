package com.example.agro_commerce.DAO;

import com.example.agro_commerce.model.Contact;

import java.sql.SQLException;
import java.util.List;

public interface ContactDAO {
    boolean insertContact(Contact contact) throws SQLException;
    List<Contact> listAllContacts() throws SQLException;
    boolean deleteContact(Contact contact) throws SQLException;
    boolean updateContact(Contact contact) throws SQLException;
    Contact getContact(int contactId) throws SQLException;
}
