package com.example.delivey.repository;

import com.example.delivey.model.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface adminRepo extends JpaRepository<admin,Long> {

    @Query(value="select * from admin where email=:email",nativeQuery = true)
    admin findByEmail(String email);
}
