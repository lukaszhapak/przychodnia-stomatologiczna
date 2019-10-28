package com.example.przychodnia.util;

import com.example.przychodnia.entity.ContactData;
import com.example.przychodnia.service.ContactDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoData implements CommandLineRunner {

    final private ContactDataService contactDataService;

    @Autowired
    public DemoData(ContactDataService contactDataService) {
        this.contactDataService = contactDataService;
    }

    @Override
    public void run(String... args) {
        if (contactDataService.count() == 0) {
            contactDataService.save(new ContactData());
        }
    }
}