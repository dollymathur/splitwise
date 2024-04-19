package com.example.splitwise.model;

import java.util.HashMap;
import java.util.List;

public class Expense {
    int id;
    int amount;
    User paidBy;
    List<User> paidTo;
    SplitType splitType;
    HashMap<User, Integer> share;

    public Expense(int id, int amount, User paidBy, List<User> paidTo, SplitType splitType, HashMap<User, Integer> share) {
        this.id = id;
        this.amount = amount;
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.splitType = splitType;
        this.share = share;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<User> getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(List<User> paidTo) {
        this.paidTo = paidTo;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public HashMap<User, Integer> getShare() {
        return share;
    }

    public void setShare(HashMap<User, Integer> share) {
        this.share = share;
    }
}
