package com.employeemanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Project;

/**
 *<p>
 * This interface consists of method declaration for employee service.
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since   2024/07/20
 */
public interface EmployeeService {
    
    /**
     * Add employee to the list
     *@param employeeId      Id of the employee
     *@param employeeName    Name of the employee
     *@param dateOfBirth     employee's date of birth
     *@param phoneNumber     employee's Phone number
     *@param mailId          employee's MailId
     *@param experience      experience of the employee
     *@param departmentId    Id of the department
     *@return Employee        If employee is added, return employee object
     *@throws EmployeeException  If exception occurs, while inserting the employee data.
     */
    public Employee addEmployee (String employeeName, 
                            LocalDate dateOfBirth, String phoneNumber,
                            String mailId, int experience, int departmentId) throws EmployeeException;

    /**
     * Get list of employees
     *@return List<Employee>    list of employees
     *@throws EmployeeException   If exception occurs, while getting the list of employees.
     */
    public List<Employee> getEmployees() throws EmployeeException;

    /**
     * Get employee details by employeeId
     *@param employeeId     Id of the employee
     *@return Employee      If employee present, return employee details
     *                      else return null.
     *@throws EmployeeException   If exception occurs, while getting the employee details.
     */
    public Employee getEmployeeById(int employeeId) throws EmployeeException;

    /**
     * Checks if employee List is empty or not
     *@return boolean             If empty, returns true or else false 
     *@throws EmployeeException   If exception occurs, while checking for the employee list is empty or not.
     */
    public boolean isEmployeeListEmpty() throws EmployeeException;

    /** Add project to the employee
     *@param employee   employee details(employee object)
     *@param project    project details(project object)
     *@throws EmployeeException   If exception occurs, while adding project to the employee
     */
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException;

    /**
     * Update employee name by their employee id
     *@param employeeId    Id of the employee
     *@param employeeName  Name of the employee
     *@return Employee     If employee detail is updated, return employee
     *@throws EmployeeException  If exception occurs, while updating the details of employee by id.
     */
    public Employee updateEmployeeDetails(Employee employee) throws EmployeeException;

    /**
     * Delete employee by their employeeId
     *@param employeeId   Id of the employee
     *@return boolean     If employee deleted, returns true or else false
     *@throws EmployeeException  If exception occurs, while deleting the employee.
     */
    public boolean isEmployeeDeleted(int employeeId) throws EmployeeException;
    
}