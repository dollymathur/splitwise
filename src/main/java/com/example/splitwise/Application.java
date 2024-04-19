package com.example.splitwise;

import com.example.splitwise.factory.SplitFactory;
import com.example.splitwise.model.*;
import com.example.splitwise.service.SplitwiseManager;

import java.util.*;

public class Application {

    static SplitwiseManager splitwiseManager;

    public static void main(String[] args) {
        /*SHOW
        SHOW 1
        EXPENSE 1 1000 4 1 2 3 4 EQUAL
        SHOW 4
        SHOW 1
        EXPENSE 1 1250 2 2 3 EXACT 370 880
        SHOW
        EXPENSE 4 1200 4 1 2 3 4 PERCENT 40 20 20 20
        SHOW 1
        SHOW*/


        System.out.println("Initializing Splitwise Application");
        List<User> users = new ArrayList<>();
        users.add(new User(1, "User 1"));
        users.add(new User(2, "User 2"));
        users.add(new User(3, "User 3"));
        users.add(new User(4, "User 4"));

        Splitwise splitwise = new Splitwise(users, new ArrayList<>(), new BalanceSheet(new ArrayList<>()));
        splitwiseManager = new SplitwiseManager(splitwise, new SplitFactory());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            System.out.println(command);
            String[] commands = command.split(" ");
            String operation = commands[0];

            if (Objects.equals(operation, "EXPENSE")) {
                processExpense(users, commands);
            } else if (Objects.equals(operation, "SHOW")) {
                processShow(users, commands);
            } else {
                System.out.println("Invalid Operation");
            }
        }
    }

    static void processExpense(List<User> users, String[] commands) {
        int paidBy = Integer.parseInt(commands[1]);
        Double totalAmount = Double.parseDouble(commands[2]);
        int noOfUsers = Integer.parseInt(commands[3]);
        List<User> paidTo = new ArrayList<>();
        for (int i = 0; i < noOfUsers; i++) {
            int index = Integer.parseInt(commands[4 + i]) - 1;
            paidTo.add(users.get(index));
        }
        String expenseType = commands[4 + noOfUsers];

        Expense expense = null;

        if (Objects.equals(expenseType, "EQUAL")) {
            expense = new Expense(1, totalAmount, users.get(paidBy - 1), paidTo, SplitType.EQUAL, null);
        } else if (Objects.equals(expenseType, "EXACT")) {
            HashMap<User, Double> share = new HashMap<>();
            for (int i = 0; i < noOfUsers; i++) {
                share.put(paidTo.get(i), Double.parseDouble(commands[5 + noOfUsers + i]));
            }
            expense = new Expense(1, totalAmount, users.get(paidBy - 1), paidTo, SplitType.EXACT, share);
        } else if (Objects.equals(expenseType, "PERCENT")) {
            HashMap<User, Double> share = new HashMap<>();
            for (int i = 0; i < noOfUsers; i++) {
                share.put(paidTo.get(i), Double.parseDouble(commands[5 + noOfUsers + i]));
            }
            expense = new Expense(1, totalAmount, users.get(paidBy - 1), paidTo, SplitType.PERCENT, share);
        } else {
            System.out.println("Invalid Expense Type");
        }

        splitwiseManager.addExpense(expense);
    }

    static void processShow(List<User> users, String[] commands) {

        if (commands.length == 1) {
            splitwiseManager.showBalanceSheet();
            System.out.println();
            return;
        }

        int userId = Integer.parseInt(commands[1]);
        User user = users.get(userId - 1);
        splitwiseManager.showUserBalance(user);
        System.out.println();
    }
}
