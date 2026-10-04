package com.rn.lab2.exceptionhandling;
import java.util.Scanner;
/*
 * File Name: MainDriver.java
 * Description: The main entry point of the banking application. It prompts the user
 *              via Scanner to input data and create 3 BankAccount objects inside an array.
 *              It then executes at least one deposit and one withdrawal on each account.
 *              It wraps operations inside try-catch-finally blocks to catch invalid 
 *              input numbers, validation errors, and custom InsufficientFundsExceptions 
 *              without letting the application crash.
 */
public class MainDriver {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccount[] accounts = new BankAccount[3];

        System.out.println("--- Enter Details for 3 Bank Accounts ---");

        // Loop to create 3 bank accounts with validation handling
        for (int i = 0; i < accounts.length; i++) {
            boolean created = false;
            while (!created) {
                try {
                    System.out.println("\nCreating Account #" + (i + 1));

                    System.out.print("Enter Account Number (at least 9 digits): ");
                    String accNum = input.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double bal = Double.parseDouble(input.nextLine());

                    // Try to construct account
                    accounts[i] = new BankAccount(accNum, name, bal);
                    created = true;
                    System.out.println("Account created successfully!");

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: Balance must be a valid number.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        // Loop to process at least 1 deposit and 1 withdrawal per account
        System.out.println("\n--- Starting Transactions ---");

        for (int i = 0; i < accounts.length; i++) {
            BankAccount acc = accounts[i];
            System.out.println("\nTransactions for " + acc.getName() + " (Acc: " + acc.getAccountNumber() + ")");

            // Deposit transaction with try-catch-finally
            try {
                System.out.print("Enter amount to deposit: ");
                double depAmount = Double.parseDouble(input.nextLine());
                acc.deposit(depAmount);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for deposit.");
            } catch (IllegalArgumentException e) {
                System.out.println("Deposit failed: " + e.getMessage());
            } finally {
                System.out.println("[Finally Block] Deposit transaction processed.");
            }

            // Withdrawal transaction with try-catch-finally
            try {
                System.out.print("Enter amount to withdraw: ");
                double withAmount = Double.parseDouble(input.nextLine());
                acc.withdraw(withAmount);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for withdrawal.");
            } catch (InsufficientFundsException e) {
                System.out.println("Withdrawal failed: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Withdrawal failed: " + e.getMessage());
            } finally {
                System.out.println("[Finally Block] Withdrawal transaction processed.");
            }
        }

        // Print final balance report
        System.out.println("\n--- Final Balances ---");
        for (int i = 0; i < accounts.length; i++) {
            System.out.printf("%s (%s): $%.2f%n", 
                accounts[i].getName(), 
                accounts[i].getAccountNumber(), 
                accounts[i].getBalance());
        }

        input.close();
    }
}