package com.example.delivey.servicesImp;

import com.example.delivey.model.admin;
import com.example.delivey.services.adminService;

import java.util.Optional;

public class adminServiceImp implements adminService {

    @Override
    public Optional<admin> findByEmailAndPassword(String email, String password) {
        return Optional.empty();
    }
}
