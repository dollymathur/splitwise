package com.example.splitwise.service;

import com.example.splitwise.model.Balance;
import com.example.splitwise.model.Expense;
import com.example.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentSplitService extends SplitService {

    public PercentSplitService(Expense expense) {
        super(expense);
    }

    @Override
    public Boolean validate() {
        int sum = 0;

        for (Map.Entry<User, Integer> mapElement : expense.getShare().entrySet()) {
            sum += mapElement.getValue();
        }

        return sum == 100;
    }

    @Override
    public List<Balance> calculateSplit() {
        List<Balance> balances = new ArrayList<>();

        for (Map.Entry<User, Integer> mapElement : expense.getShare().entrySet()) {
            balances.add(new Balance(expense.getPaidBy(), mapElement.getKey(),
                    (double) (mapElement.getValue() / 100) * expense.getAmount()));
        }
        return balances;
    }
}
