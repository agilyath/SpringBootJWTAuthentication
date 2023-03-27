package com.example.techexamcodelantic.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_login_details")
public class UserLogin {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false, length = 50)
    private String username;
 
    @Column(nullable = false, length = 70)
    private String loginDateTimeString;    

    public UserLogin(String username, String loginDateTimeString) {
        this.username = username;
        this.loginDateTimeString = loginDateTimeString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginDateTimeString() {
        return loginDateTimeString;
    }

    public void setLoginDateTimeString(String loginDateTimeString) {
        this.loginDateTimeString = loginDateTimeString;
    }    

}
