package com.example.delivey.repository;


import com.example.delivey.model.client;
import com.example.delivey.model.livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface livRepo extends JpaRepository<livreur,Long> {
    @Query(value="select * from livreur where email=:email",nativeQuery = true)
    livreur findByEmail(String email);
    @Query(value="select * from livreur where livname=:livname",nativeQuery = true)
    client findByUsername(String livname);



}
