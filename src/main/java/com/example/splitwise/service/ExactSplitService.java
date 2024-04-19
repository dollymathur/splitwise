package com.example.splitwise.service;

import com.example.splitwise.model.Balance;
import com.example.splitwise.model.Expense;
import com.example.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExactSplitService extends SplitService {

    public ExactSplitService(Expense expense) {
        super(expense);
    }

    @Override
    public Boolean validate() {
        Double sum = (double) 0;

        for (Map.Entry<User, Double> mapElement : expense.getShare().entrySet()) {
            sum += mapElement.getValue();
        }

        return sum.equals(expense.getAmount());
    }

    @Override
    public List<Balance> calculateSplit() {
        List<Balance> balances = new ArrayList<>();

        for (Map.Entry<User, Double> mapElement : expense.getShare().entrySet()) {
            balances.add(new Balance(expense.getPaidBy(), mapElement.getKey(),
                    (double) mapElement.getValue()));
        }
        return balances;
    }
}
