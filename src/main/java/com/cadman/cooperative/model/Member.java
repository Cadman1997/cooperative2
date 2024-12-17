package com.cadman.cooperative.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberId; // OGC001

    private String name;

    private String email;

    private String password;

    private LocalDateTime registrationDate;

    //constructors

    public Member() {
        this.registrationDate = LocalDateTime.now();
    }

    public Member( String memberId, String name, String email, String password) {

        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = LocalDateTime.now();
    }

    // getter and setter


    public Long getId() {
        return id;
    }

        public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
