package com.alfaizuna.bankabc.dto.response;

public class CustomerSaldoResponseDTO {

    private String name;
    private Long amountSaldo;

    public CustomerSaldoResponseDTO() {
    }

    public CustomerSaldoResponseDTO(String name, Long amountSaldo) {
        this.name = name;
        this.amountSaldo = amountSaldo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmountSaldo() {
        return amountSaldo;
    }

    public void setAmountSaldo(Long amountSaldo) {
        this.amountSaldo = amountSaldo;
    }
}
