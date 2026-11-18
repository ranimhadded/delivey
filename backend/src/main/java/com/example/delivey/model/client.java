package com.example.delivey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity

public class client {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long ClientId;
     private String ClientName;
     private String Email;
     private String password;
     private String job;
     private String company;
     private Long phone;
     private String Industry;

    public client(Long clientId, String clientName, String Email, String password, String job, Long phone, String industry,String company) {
        ClientId = clientId;
        ClientName = clientName;
        this.Email = Email;
        this.password = password;
        this.job = job;
        this.company=company;
        this.phone = phone;
        Industry = industry;
    }
    public client(){}
    @JsonIgnore
    @OneToMany(fetch= FetchType.LAZY ,mappedBy = "client", cascade = {CascadeType.ALL})
    private List<colis> colis;

    public Long getClientId() {
        return ClientId;
    }

    public void setClientId(Long clientId) {
        ClientId = clientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String Company) {
        this.company = Company;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }
}
