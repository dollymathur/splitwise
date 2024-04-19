package com.example.splitwise.model;

public class Balance {
    User paidBy;
    User paidTo;
    Double amount;

    public Balance(User paidBy, User paidTo, Double amount) {
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public User getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(User paidTo) {
        this.paidTo = paidTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
