package com.example.delivey.dto;

public class LivreurDto {
    private String livName;
    private String email;
    private String pass;
    private String position;
    private Long phone;

    public String getLivName() {
        return livName;
    }

    public void setLivName(String livName) {
        this.livName = livName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
