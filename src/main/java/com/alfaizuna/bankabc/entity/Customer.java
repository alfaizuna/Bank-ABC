package com.alfaizuna.bankabc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_bank")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long nik;

    private String accountNumber;

    private Long saldo;

    public Customer() {
    }

    public Customer(Long id, String name, Long saldo, Long nik, String accountNumber) {
        this.id = id;
        this.name = name;
        this.nik = nik;
        this.accountNumber = accountNumber;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNik() {
        return nik;
    }

    public void setNik(Long nik) {
        this.nik = nik;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
