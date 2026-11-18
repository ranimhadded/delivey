package com.example.delivey.repository;

import com.example.delivey.model.client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface clientRepo extends JpaRepository<client,Long> {
    @Query(value="select * from client where email=:email",nativeQuery = true)
    client findByEmail(String email);
    @Query(value="select * from client where clientname=:clientname",nativeQuery = true)
    client findByUsername(String clientname);
    @Query("SELECT c.company FROM client c WHERE c.Email = :email")
    String findCompanyNameByEmail(@Param("email") String email);
    @Query("SELECT c.ClientName FROM client c WHERE c.Email = :email")
    String findClientNameByEmail(@Param("email") String email);

    @Query("SELECT c.job FROM client c WHERE c.Email = :email")
    String findClientJobByEmail(@Param("email") String email);

}
