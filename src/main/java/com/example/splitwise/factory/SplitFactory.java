package com.example.splitwise.factory;

import com.example.splitwise.model.Expense;
import com.example.splitwise.model.SplitType;
import com.example.splitwise.service.EqualSplitService;
import com.example.splitwise.service.ExactSplitService;
import com.example.splitwise.service.PercentSplitService;
import com.example.splitwise.service.SplitService;

public class SplitFactory {

    public SplitService getSplitService(Expense expense) {

        if (expense.getSplitType() == SplitType.EQUAL) {
            return new EqualSplitService(expense);
        } else if (expense.getSplitType() == SplitType.EXACT) {
            return new ExactSplitService(expense);
        } else {
            return new PercentSplitService(expense);
        }
    }
}
