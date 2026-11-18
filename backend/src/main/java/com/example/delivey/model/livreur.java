package com.example.delivey.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class livreur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long livId;
    private String livName;
    private String Email;
    private String pass;
    private String position;
    private Long phone;

    public livreur(Long livId, String livName, String email, String pass, String position,Long phone) {
        this.livId = livId;
        this.livName = livName;
        Email = email;
        this.pass = pass;
        this.position = position;
        this.phone=phone;
    }
    public livreur(){}
}
