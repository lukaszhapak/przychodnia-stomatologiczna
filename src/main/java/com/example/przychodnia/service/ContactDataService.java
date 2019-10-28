package com.example.przychodnia.service;

import com.example.przychodnia.entity.ContactData;
import com.example.przychodnia.repository.ContactDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactDataService {

    private final ContactDataRepository contactDataRepository;

    @Autowired
    public ContactDataService(ContactDataRepository contactDataRepository) {
        this.contactDataRepository = contactDataRepository;
    }

    public List<ContactData> findAll() {
        return contactDataRepository.findAll();
    }

    public void save(ContactData contactData) {
        contactDataRepository.save(contactData);
    }
}
