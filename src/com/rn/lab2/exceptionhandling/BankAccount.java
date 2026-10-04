package com.rn.lab2.exceptionhandling;
/*
 * File Name: BankAccount.java
 * Description: Represents a single bank account entity. It stores the account number,
 *              the owner's name, and the running balance. It validates all data 
 *              upon creation (minimum 9 numeric digits for account number, non-empty 
 *              name, and non-negative balance). It also provides deposit and withdrawal 
 *              operations that enforce validation rules and throw exceptions when 
 *              amounts are zero/negative or when funds are insufficient.
 */
public class BankAccount {

    // Instance variables to store account details
    private String accountNumber;
    private String name;
    private double balance;

    // Constructor to initialize and validate account fields
    public BankAccount(String accountNumber, String name, double balance) {
        // Validate that account number has at least 9 characters
        if (accountNumber == null || accountNumber.length() < 9) {
            throw new IllegalArgumentException("Account number must be at least 9 digits long.");
        }

        // Validate that every character in the account number is a digit
        for (int i = 0; i < accountNumber.length(); i++) {
            if (!Character.isDigit(accountNumber.charAt(i))) {
                throw new IllegalArgumentException("Account number can only contain digits.");
            }
        }

        // Validate that the account holder's name is not empty
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        // Validate that the starting balance is not negative
        if (balance < 0) {
            throw new IllegalArgumentException("Starting balance cannot be negative.");
        }

        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    // Adds money to the account balance
    public void deposit(double amount) {
        // Amount must be positive
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
        balance += amount;
        System.out.printf("Deposited: $%.2f | New balance: $%.2f%n", amount, balance);
    }

    // Deducts money from the account balance
    public void withdraw(double amount) throws InsufficientFundsException {
        // Amount must be positive
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0.");
        }
        // Withdrawal cannot exceed the balance
        if (amount > balance) {
            throw new InsufficientFundsException("Cannot withdraw $" + amount + ". Current balance is only $" + balance);
        }
        balance -= amount;
        System.out.printf("Withdrew: $%.2f | New balance: $%.2f%n", amount, balance);
    }

    // Getter for account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter for account holder name
    public String getName() {
        return name;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }
}
