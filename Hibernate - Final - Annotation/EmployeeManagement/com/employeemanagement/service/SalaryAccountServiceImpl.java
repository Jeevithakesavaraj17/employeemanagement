package com.employeemanagement.service;

import com.employeemanagement.dao.SalaryAccountDao;
import com.employeemanagement.dao.SalaryAccountDaoImpl;
import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.SalaryAccount;
import com.employeemanagement.service.SalaryAccountService;

/**
 * <p>
 * This class is for inserting and retrieving the salary account details of the employee from the database
 * and implements SalaryAccountDao interface.
 * </p>
 *
 * @author    JeevithaKesavaraj
 * @version   1.0
 * @since     2024/07/30
 */
public class SalaryAccountServiceImpl implements SalaryAccountService {
    SalaryAccountDao salaryAccountDao = new SalaryAccountDaoImpl();

    @Override
    public void addSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException {
        salaryAccountDao.insertSalaryAccount(salaryAccount);   
    }

    @Override
    public SalaryAccount getSalaryAccount(int id) throws EmployeeException {
        return salaryAccountDao.retrieveSalaryAccount(id);
    }

    @Override
    public void updateSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException {
        salaryAccountDao.updateSalaryAccount(salaryAccount);
    }
}