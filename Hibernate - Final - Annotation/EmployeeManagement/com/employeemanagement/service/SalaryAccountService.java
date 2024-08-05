package com.employeemanagement.service;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.SalaryAccount;

/**
 * <p>
 * This interface has method declaration for inserting and retrieving the salary 
 * account details of the employee from the database
 * </p>
 *
 * @author    JeevithaKesavaraj
 * @version   1.0
 * @since     2024/07/30
 */
public interface SalaryAccountService {

    /**
     * Add salary account details to the salary account table
     * @param salaryAccount        salary account details of the employee
     * @throws EmployeeException   If exception occurs, while adding the salary account details
     */
    public void addSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException;

    /**
     * Get salary account details of the employee
     * @param  id               employee's aaccount Id
     * @return SalaryAccount    salary account details
     * @throws EmployeeException  If exception occurs, while getting the salary account details
     */
    public SalaryAccount getSalaryAccount(int id) throws EmployeeException;

    /**
     * Update salary account details to the salary account table
     * @param salaryAccount        salary account details of the employee
     * @throws EmployeeException   If exception occurs, while updating the salary account details
     */
    public void updateSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException;
}