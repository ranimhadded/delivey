package com.example.delivey.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class admin {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;
   private String Email;
   private String pass;

    public admin(Long idAdmin, String email, String pass) {
        this.idAdmin = idAdmin;
        Email = email;
        this.pass = pass;
    }
    public admin(){}

    public Long getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
