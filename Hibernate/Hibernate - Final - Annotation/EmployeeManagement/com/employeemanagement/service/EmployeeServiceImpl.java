package com.employeemanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.employeemanagement.dao.EmployeeDao;
import com.employeemanagement.dao.EmployeeDaoImpl;
import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Project;
import com.employeemanagement.service.DepartmentService;
import com.employeemanagement.service.DepartmentServiceImpl;
import com.employeemanagement.service.ProjectService;
import com.employeemanagement.service.ProjectServiceImpl;


/**
 *<p>
 * This class implements employee service and manages the employee details.
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since   2024/07/20
 */
public class EmployeeServiceImpl implements EmployeeService {
    DepartmentService departmentService = new DepartmentServiceImpl();
    ProjectService projectService = new ProjectServiceImpl();
    EmployeeDao employeeDao = new EmployeeDaoImpl();
  
    @Override
    public Employee addEmployee(String employeeName, 
                            LocalDate dateOfBirth, String phoneNumber,
                            String mailId, int experience, int departmentId) throws EmployeeException {
        Department department = departmentService.getDepartment(departmentId);
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setPhoneNumber(phoneNumber);
        employee.setMailId(mailId);
        employee.setExperience(experience);
        employee.setDepartment(department);
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees() throws EmployeeException {
        return employeeDao.getEmployees();
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public boolean isEmployeeListEmpty() throws EmployeeException {
        List<Employee> employees = employeeDao.getEmployees();
        if (employees.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Employee updateEmployeeDetails(Employee employee) throws EmployeeException {
        return employeeDao.updateEmployeeDetails(employee);
    }

    @Override
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException {
        projectService.addProjectToEmployee(project, employee);
    }

    @Override
    public boolean isEmployeeDeleted(int employeeId) throws EmployeeException {
        return employeeDao.isEmployeeDeleted(employeeId);
    }
}