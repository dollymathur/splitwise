package com.example.splitwise.service;

import com.example.splitwise.model.Balance;
import com.example.splitwise.model.Expense;
import com.example.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitService extends SplitService {

    public EqualSplitService(Expense expense) {
        super(expense);
    }

    @Override
    public Boolean validate() {
        return true;
    }

    @Override
    public List<Balance> calculateSplit() {

        List<Balance> balances = new ArrayList<>();

        for (User user: expense.getPaidTo()) {
            balances.add(new Balance(expense.getPaidBy(), user,
                    (double) (expense.getAmount() / expense.getPaidTo().size())));

        }
        return balances;
    }
}
