package com.alfaizuna.bankabc.dto.request;

public class RegisterCustomerRequestDTO {
    private String name;
    private Long nik;

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
}
