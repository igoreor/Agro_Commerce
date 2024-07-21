package com.example.agro_commerce.controller;

import com.example.agro_commerce.DAO.ContactDAO;
import com.example.agro_commerce.model.Contact;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactDAO contactDAO;

    @Autowired
    public ContactController(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    @SneakyThrows
    @PostMapping("/")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        if (contactDAO.insertContact(contact)) {
            return ResponseEntity.ok(contact);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactDAO.listAllContacts();
        if (contacts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(contacts);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable int id) {
        Contact contact = contactDAO.getContact(id);
        if (contact != null) {
            return ResponseEntity.ok(contact);
        }
        return ResponseEntity.notFound().build();
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable int id, @RequestBody Contact contact) {
        contact.setContactId(id);
        if (contactDAO.updateContact(contact)) {
            return ResponseEntity.ok(contact);
        }
        return ResponseEntity.status(500).build();
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        Contact contact = new Contact();
        contact.setContactId(id);
        if (contactDAO.deleteContact(contact)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(500).build();
    }
}
