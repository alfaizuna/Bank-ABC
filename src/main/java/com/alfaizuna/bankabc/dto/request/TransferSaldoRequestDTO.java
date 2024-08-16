package com.alfaizuna.bankabc.dto.request;

import java.math.BigDecimal;

public class TransferSaldoRequestDTO {

    private String fromAccount;
    private String toAccount;
    private Long amount;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
