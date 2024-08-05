package com.employeemanagement.dao;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.SalaryAccount;

/**
 * <p>
 * This interface has method declaration for inserting and retrieving the salary  
 * account details of the employee from the database.
 * </p>
 *
 * @author    JeevithaKesavaraj
 * @version   1.0
 * @since     2024/07/30
 */
public interface SalaryAccountDao {

    /**
     * <p>
     * Insert salary account details to the salary account table
     * </p>
     *
     * @param salaryAccount        salary account details of the employee
     * @throws EmployeeException   If exception occurs, while inserting the salary account details
     */
    public void insertSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException;

    /**
     * <p>
     * Retrieve salary account details of the employee
     * </p>
     *
     * @param  id               employee's aaccount Id
     * @return SalaryAccount    salary account details
     * @throws EmployeeException  If exception occurs, while retrieving the salary account details
     */
    public SalaryAccount retrieveSalaryAccount(int id) throws EmployeeException;

    /**
     * <p>
     * Update salary account details to the salary account table
     * </p>
     *
     * @param salaryAccount        salary account details of the employee
     * @throws EmployeeException   If exception occurs, while inserting the salary account details
     */
    public void updateSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException;

}