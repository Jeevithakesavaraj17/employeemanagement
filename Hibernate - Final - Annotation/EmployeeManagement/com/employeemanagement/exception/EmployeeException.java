package com.employeemanagement.exception;

/**
 * <p>
 * This class is for custom exception for employee management system and extends Exception
 * </p>
 *
 * @author  JeevithaKesavaraj
 * @version 1.0
 * @since   2024/07/30
 */
public class EmployeeException extends Exception{

    public EmployeeException(String message, Throwable e) {
        super(message, e);
    }

}