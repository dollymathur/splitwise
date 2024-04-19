package com.example.splitwise.model;

import java.util.ArrayList;
import java.util.List;

public class BalanceSheet {
    List<Balance> balances;

    public BalanceSheet(List<Balance> balances) {
        this.balances = balances;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }
}
