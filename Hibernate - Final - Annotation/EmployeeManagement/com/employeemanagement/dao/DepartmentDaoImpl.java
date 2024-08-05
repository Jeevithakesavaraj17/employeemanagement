package com.employeemanagement.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.employeemanagement.connectionmanager.HibernateConnection;
import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Employee;

/**
 * <p>
 * This class is have methods for department CRUD operation and implements department Dao. 
 * </p>
 *
 * @author JeevithaKesavaraj
 * @version 1.0
 * @since 2024/07/30
 */
public class DepartmentDaoImpl implements DepartmentDao {
    
    @Override
    public Department insertDepartment(Department department) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to add the Department : " + department.getDepartmentName(), e);
        } finally {
            session.close();
        }
        return department;
    }

    @Override
    public List<Department> retrieveDepartments() throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        List<Department> departments = null;
        try {
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery("FROM Department WHERE isDeleted = :isDeleted", Department.class)
                                              .setParameter("isDeleted", false);
            departments = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
            throw new EmployeeException("Unable to get the list of departments.", e);
        } finally {
            session.close();
        }
        return departments;
    }
  
    @Override
    public Department retrieveDepartment(int departmentId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Department department = null;
        try {
            transaction = session.beginTransaction();
            department = session.createQuery("FROM Department WHERE isDeleted = false and departmentId = :departmentId", Department.class)
                                .setParameter("departmentId", departmentId).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get department" + departmentId, e);
        } finally {
            session.close();
        }
        return department;
    }
    
    @Override
    public List<Employee> retrieveEmployeesByDepartment(int departmentId) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        Department department = null;
        List<Employee> employees = null;
        try {
            transaction = session.beginTransaction();
            department = session.createQuery("FROM Department WHERE isDeleted = false and departmentId = :departmentId", Department.class)
                                .setParameter("departmentId", departmentId).uniqueResult();
            if (department != null) {
                employees = new ArrayList<>(department.getEmployees());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to get employees for the department" + departmentId, e);
        } 
        return employees;
    } 

    @Override 
    public Department updateDepartmentName(Department department) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to update the department " + department.getDepartmentId(), e);
        } finally {
            session.close();
        }
        return department;
    }

    @Override  
    public boolean isDepartmentDeleted(Department department) throws EmployeeException {
        Session session = HibernateConnection.getFactory().openSession();
        int departmentId = department.getDepartmentId();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE Department SET isDeleted = :isDeleted WHERE id = :departmentId");
            query.setParameter("isDeleted", true);
            query.setParameter("departmentId", departmentId);
            int row = query.executeUpdate();
            transaction.commit();
            if (row == 1) {
                return true;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Unable to delete the department" + department.getDepartmentId(), e);
        } finally { 
            session.close();   
        }
        return false;
    }

}