package com.example.splitwise.service;

import com.example.splitwise.factory.SplitFactory;
import com.example.splitwise.model.Balance;
import com.example.splitwise.model.Expense;
import com.example.splitwise.model.Splitwise;
import com.example.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;

public class SplitwiseManager {

    Splitwise splitwise;
    SplitFactory splitFactory;

    public SplitwiseManager(Splitwise splitwise, SplitFactory splitFactory) {
        this.splitwise = splitwise;
        this.splitFactory = splitFactory;
    }

    public void addExpense(Expense expense) {
        splitwise.getExpenses().add(expense);
        evaluateExpense(expense);
    }

    public void evaluateExpense(Expense expense) {
        SplitService splitService = splitFactory.getSplitService(expense);
        List<Balance> balances = splitService.calculateSplit();
        splitwise.getBalanceSheet().setBalances(combineBalances(balances, splitwise.getBalanceSheet().getBalances()));
    }

    public void showBalanceSheet() {

        if (splitwise.getBalanceSheet().getBalances().isEmpty()) {
            System.out.println("No balances");
        }

        for (Balance balance : splitwise.getBalanceSheet().getBalances()) {
            if (balance.getPaidBy() == balance.getPaidTo()) {
                continue;
            }
            System.out.println(balance.getPaidTo().getName() + " owes " +
                    balance.getPaidBy().getName() + " " + balance.getAmount());
        }
    }

    public void showUserBalance(User user) {

        if (splitwise.getBalanceSheet().getBalances().isEmpty()) {
            System.out.println("No balances");
        }

        for (Balance balance : splitwise.getBalanceSheet().getBalances()) {
            if (balance.getPaidBy() == balance.getPaidTo()) {
                continue;
            }
            if (balance.getPaidTo().getName().equals(user.getName())) {
                System.out.println(balance.getPaidTo().getName() + " owes " +
                        balance.getPaidBy().getName() + " " + balance.getAmount());
            }
        }
    }

    private List<Balance> combineBalances(List<Balance> updated, List<Balance> existing) {

        if (existing.isEmpty()) {
            return updated.stream().filter(balance -> balance.getPaidBy() != balance.getPaidTo()).toList();
        }

        List<Balance> balances = new ArrayList<>();

        for (Balance balance : updated) {
            if (balance.getPaidBy() == balance.getPaidTo()) {
                continue;
            }
            boolean flag = false;
            for (Balance existingBalance : existing) {
                if (existingBalance.getPaidBy() == existingBalance.getPaidTo()) {
                    continue;
                }
                if (balance.getPaidBy().getName().equals(existingBalance.getPaidBy().getName()) &&
                        balance.getPaidTo().getName().equals(existingBalance.getPaidTo().getName())) {
                    flag = true;
                    existingBalance.setAmount(existingBalance.getAmount() + balance.getAmount());
                    break;
                }
            }
            if (!flag) {
                balances.add(balance);
            }
        }

        if (balances.isEmpty()) {
            return existing;
        }
        balances.addAll(existing);
        return balances;
    }
}
