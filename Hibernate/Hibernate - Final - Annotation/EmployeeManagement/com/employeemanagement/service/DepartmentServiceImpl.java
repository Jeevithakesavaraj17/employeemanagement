package com.employeemanagement.service;

import java.util.List;
import java.util.ArrayList;

import com.employeemanagement.dao.DepartmentDao;
import com.employeemanagement.dao.DepartmentDaoImpl;
import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Employee;
import com.employeemanagement.service.DepartmentService;


/**
 *<p>
 * This class implements Department service which manages the department
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since  2024-07-20
 */
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDaoImpl();
    
    @Override
    public Department addDepartment(String departmentName) throws EmployeeException {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        return departmentDao.insertDepartment(department);
    }

    @Override
    public List<Department> getDepartments() throws EmployeeException {
        return departmentDao.getDepartments();
    }
 
    @Override
    public Department getDepartment(int departmentId) throws EmployeeException {
        return departmentDao.getDepartment(departmentId);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) throws EmployeeException {
        return departmentDao.getEmployeesByDepartment(departmentId);
    }

    @Override 
    public Department updateDepartmentName(Department department) throws EmployeeException {
        return departmentDao.updateDepartmentName(department);
    }

    @Override
    public boolean isDepartmentDeleted(Department department) throws EmployeeException {
        return departmentDao.isDepartmentDeleted(department);
    }
}
