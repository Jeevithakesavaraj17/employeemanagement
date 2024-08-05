package com.employeemanagement.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.employeemanagement.connectionManager.HibernateConnection;
import com.employeemanagement.dao.EmployeeDao;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Project;
import com.employeemanagement.exception.EmployeeException;

/**
 * Represents employee repository
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since 2024/07/20
 */
public class EmployeeDaoImpl implements EmployeeDao {
    
    @Override
    public Employee insertEmployee(Employee employee) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to add the employee" + employee.getEmployeeName(), e);
        } finally {
            session.close();
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployees() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("FROM Employee WHERE isDeleted = :isDeleted", Employee.class)
                                           .setParameter("isDeleted", false);
            employees = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get employees", e);
        } finally {
            session.close();
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Employee employee = null;
        try {
            transaction = session.beginTransaction();
            employee = session.createQuery("FROM Employee WHERE isDeleted = :isDeleted and employeeId = :employeeId", Employee.class)
                              .setParameter("isDeleted", false)
                              .setParameter("employeeId", id).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get employee" + id, e);
        } finally {
            session.close();
        }
        return employee;
    }


    @Override
    public Employee updateEmployeeDetails(Employee employee) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to update the employee " + employee.getEmployeeId(), e);
        } finally {
            session.close();
        }
        return employee;
    }
   
    @Override
    public boolean isEmployeeDeleted(int employeeId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("UPDATE Employee SET isDeleted = :isDeleted WHERE id = :employeeId");
            query.setParameter("isDeleted", true);
            query.setParameter("employeeId", employeeId);
            int row = query.executeUpdate();
            transaction.commit();
            if (row == 1) {
                return true;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to delete the employee" + employeeId, e);
        } finally { 
            session.close();
        }
        return false;
    }
}