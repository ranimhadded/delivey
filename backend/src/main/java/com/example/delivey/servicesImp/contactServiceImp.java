package com.example.delivey.servicesImp;

import com.example.delivey.model.client;
import com.example.delivey.model.contact;
import com.example.delivey.repository.colisRepo;
import com.example.delivey.repository.contactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class contactServiceImp {
    @Autowired
    private contactRepo contRepo;
    public contact AddContact(contact c) {
        return contRepo.save(c);
    }
}
