package com.employeemanagement.exception;

/**
 *<p>
 * This class extends Exception
 *</p>
 */
public class EmployeeException extends Exception{

    public EmployeeException(String message, Throwable e) {
        super(message, e);
    }

}