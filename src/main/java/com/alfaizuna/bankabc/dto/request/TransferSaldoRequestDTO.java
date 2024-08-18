package com.alfaizuna.bankabc.dto.request;

public class TransferSaldoRequestDTO {

    private Long fromCustomerId;
    private String fromAccount;
    private String toAccount;
    private Long amount;

    public Long getFromCustomerId() {
        return fromCustomerId;
    }

    public void setFromCustomerId(Long fromCustomerId) {
        this.fromCustomerId = fromCustomerId;
    }

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
