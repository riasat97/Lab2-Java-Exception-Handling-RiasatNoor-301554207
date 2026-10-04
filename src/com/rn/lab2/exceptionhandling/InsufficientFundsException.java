package com.rn.lab2.exceptionhandling;

/*
 * File Name: InsufficientFundsException.java
 * Description: Custom checked exception class that gets thrown when a user 
 *              attempts to withdraw an amount that exceeds their current 
 *              account balance. It inherits from the base Exception class.
 */
public class InsufficientFundsException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor that passes a custom error message to the parent Exception class
    public InsufficientFundsException(String message) {
        super(message);
    }
}