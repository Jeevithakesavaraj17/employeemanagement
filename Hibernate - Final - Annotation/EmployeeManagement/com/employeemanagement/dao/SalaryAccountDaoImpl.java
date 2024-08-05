package com.employeemanagement.dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.employeemanagement.connectionmanager.HibernateConnection;
import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.SalaryAccount;

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
public class SalaryAccountDaoImpl implements SalaryAccountDao {

    @Override
    public void insertSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(salaryAccount);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to add the account details : " + salaryAccount.getAccountNumber(), e);
        } finally {
            session.close();
        }
    }

    @Override 
    public SalaryAccount retrieveSalaryAccount(int id) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        SalaryAccount salaryAccount = null;
        try {
            transaction = session.beginTransaction();
            salaryAccount = session.get(SalaryAccount.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            throw new EmployeeException("Unable to get the salary account details", e);
        } finally {
            session.close();
        }
        return salaryAccount;
    }

    @Override
    public void updateSalaryAccount(SalaryAccount salaryAccount) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(salaryAccount);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to update the account details : " + salaryAccount.getAccountNumber(), e);
        } finally {
            session.close();
        }
    }

}