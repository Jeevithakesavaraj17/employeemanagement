package com.employeemanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.employeemanagement.dao.EmployeeDao;
import com.employeemanagement.dao.EmployeeDaoImpl;
import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Project;
import com.employeemanagement.model.SalaryAccount;
import com.employeemanagement.service.DepartmentService;
import com.employeemanagement.service.DepartmentServiceImpl;
import com.employeemanagement.service.ProjectService;
import com.employeemanagement.service.ProjectServiceImpl;
import com.employeemanagement.service.SalaryAccountService;
import com.employeemanagement.service.SalaryAccountServiceImpl;


/**
 * <p>
 * This class implements employee service and have methods for add, get, update, delete the employee details.
 * </p>
 *
 * @author Jeevithakesavaraj
 * @version 1.0
 * @since   2024/07/30
 */
public class EmployeeServiceImpl implements EmployeeService {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private ProjectService projectService = new ProjectServiceImpl();
    private SalaryAccountService salaryAccountService = new SalaryAccountServiceImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
  
    @Override
    public Employee addEmployee(String employeeName, 
                            LocalDate dateOfBirth, long phoneNumber,
                            String mailId, int experience, int departmentId,
                            long accountNumber, String ifscCode) throws EmployeeException {
        Department department = departmentService.getDepartment(departmentId);
        SalaryAccount salaryAccount = new SalaryAccount();
        salaryAccount.setAccountNumber(accountNumber);
        salaryAccount.setIfscCode(ifscCode);
        salaryAccountService.addSalaryAccount(salaryAccount);
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setDepartment(department);
        employee.setSalaryAccount(salaryAccount);
        employee.setPhoneNumber(phoneNumber);
        employee.setMailId(mailId);
        employee.setExperience(experience);
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees() throws EmployeeException {
        return employeeDao.retrieveEmployees();
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeException {
        return employeeDao.retrieveEmployeeById(employeeId);
    }

    @Override
    public boolean isEmployeeListEmpty() throws EmployeeException {
        List<Employee> employees = employeeDao.retrieveEmployees();
        if (employees.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmployeePresent(int employeeId) throws EmployeeException {
        Employee employee = employeeDao.retrieveEmployeeById(employeeId);
        if (null == employee) {
            return false;
        }
        return true;
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