package com.example.delivey.repository;

import com.example.delivey.model.client;
import com.example.delivey.model.contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface contactRepo extends JpaRepository<contact,Long> {
}
