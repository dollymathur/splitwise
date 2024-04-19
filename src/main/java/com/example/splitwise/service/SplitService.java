package com.example.splitwise.service;

import com.example.splitwise.model.Balance;
import com.example.splitwise.model.Expense;

import java.util.List;

public abstract class SplitService {
    Expense expense;

    public SplitService(Expense expense) {
        this.expense = expense;
    }

    public abstract Boolean validate();
    public abstract List<Balance> calculateSplit();
}
