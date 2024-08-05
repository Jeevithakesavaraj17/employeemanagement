package com.employeemanagement.dao;

import java.time.LocalDate;
import java.util.List;

import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Project;
import com.employeemanagement.exception.EmployeeException;

public interface EmployeeDao {

    /**
     * Add employee to the list
     *@param employee      Employee object
     *@return Employee     If employee is added, return employee object
     *@throws EmployeeException   If exception occurs, while inserting the employee data
     */
    public Employee insertEmployee(Employee employee) throws EmployeeException;

    /**
     * Get Employee list
     *@return List<Employee>   list of employees
     *@throws EmployeeException   If exception occurs, while getting list of employees
     */
    public List<Employee> getEmployees() throws EmployeeException;

    /**
     * Get employee by their employeeId
     *@param employeeId     Id of the employee
     *@return Employee    If Employee is present
     *@return null        employee is not present
     *@throws EmployeeException   If exception occurs, while getting employee by employeeId
     */
    public Employee getEmployeeById(int id) throws EmployeeException;

    /**
     * Update employeedetails
     *@param employee   details of the employee
     *@return Employee   If employee detail is updated, return employee
     *@throws EmployeeException    If exception occurs, while updating the employee details
     */
    public Employee updateEmployeeDetails(Employee employee) throws EmployeeException;

    /**
     * Delete employee by employeeId
     *@param employeeId    Id of the employee
     *@return boolean      If employee deleted, returns true or else return false.
     *@throws EmployeeException   If exception occurs, while checking for the employee deleted or not
     */
    public boolean isEmployeeDeleted(int employeeId) throws EmployeeException;

}
