package com.example.splitwise;

import com.example.splitwise.factory.SplitFactory;
import com.example.splitwise.model.*;
import com.example.splitwise.service.SplitwiseManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Application {

    static SplitwiseManager splitwiseManager;

    public static void main(String[] args) {
        System.out.println("Initializing Splitwise Application");

        List<User> users = new ArrayList<>();
        users.add(new User(1, "User 1"));
        users.add(new User(2, "User 2"));
        users.add(new User(3, "User 3"));
        users.add(new User(4, "User 4"));

        Splitwise splitwise = new Splitwise(users, new ArrayList<>(), new BalanceSheet(new ArrayList<>()));

        List<User> paidTo = new ArrayList<>();
        paidTo.add(users.get(0));
        paidTo.add(users.get(1));
        paidTo.add(users.get(2));
        paidTo.add(users.get(3));
        Expense expense = new Expense(1, 100, users.get(0), paidTo, SplitType.EQUAL, null);

        splitwiseManager = new SplitwiseManager(splitwise, new SplitFactory());
        splitwiseManager.addExpense(expense);

        splitwiseManager.showBalanceSheet();
        System.out.println();
        splitwiseManager.showUserBalance(users.get(1));
        System.out.println();

        HashMap<User, Integer> share = new HashMap<>();
        share.put(users.get(0), 50);
        share.put(users.get(1), 70);
        share.put(users.get(2), 20);
        share.put(users.get(3), 60);
        Expense expense1 = new Expense(2, 200, users.get(1), paidTo, SplitType.EXACT, share);

        splitwiseManager.addExpense(expense1);

        splitwiseManager.showBalanceSheet();
        System.out.println();

        expense = new Expense(1, 100, users.get(0), paidTo, SplitType.EQUAL, null);
        splitwiseManager.addExpense(expense);

        splitwiseManager.showBalanceSheet();
        System.out.println();
        //splitwiseManager.showUserBalance(users.get(1));
    }
}
