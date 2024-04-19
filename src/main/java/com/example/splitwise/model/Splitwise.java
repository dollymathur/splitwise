package com.example.splitwise.model;

import java.util.List;

public class Splitwise {
    List<User> users;
    List<Expense> expenses;
    BalanceSheet balanceSheet;

    public Splitwise(List<User> users, List<Expense> expenses, BalanceSheet balanceSheet) {
        this.users = users;
        this.expenses = expenses;
        this.balanceSheet = balanceSheet;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public BalanceSheet getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(BalanceSheet balanceSheet) {
        this.balanceSheet = balanceSheet;
    }
}
