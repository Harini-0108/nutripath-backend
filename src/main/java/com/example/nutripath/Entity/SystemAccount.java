package com.example.nutripath.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "system_account")
public class SystemAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false)
    private String email;
    private String passwordHash;
    private String role;
    private String status;

    public SystemAccount(){

    }

    public SystemAccount(Long id, String email, String passwordHash, String role, String status) {
      this.id = id;
      this.email = email;
      this.passwordHash = passwordHash;
      this.role = role;
      this.status = status;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPasswordHash() {
      return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
      this.passwordHash = passwordHash;
    }

    public String getRole() {
      return role;
    }

    public void setRole(String role) {
      this.role = role;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    
}