package com.employeemanagement.service;

import java.util.List;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Employee;

/**
 * <p>
 * This interface contains method declaration for add, get, update, delete the 
 * department details and get employees in the particular department.
 * </p>
 *
 * @author Jeevithakesavaraj
 * @version 1.0
 * @since  2024-07-20
 */
public interface DepartmentService {

    /**
     * Add Department details
     * @param DepartmentName   Name of the department
     * @return Department         If department added, return department
     * @throws EmployeeException  If exception occurs, while inserting the department details.
     */
    public Department addDepartment(String departmentName) throws EmployeeException;

    /**
     * Get list of Departments
     * @return List<Department>    list of Departments
     * @throws EmployeeException   If exception occurs, while getting the list of departments.
     */
    public List<Department> getDepartments() throws EmployeeException;

    /**
     * Get department by departmentId
     * @param departmentId      Id of the department
     * @return department      Id department is present, return true or else return false.
     * @throws EmployeeException   If exception occurs, while getting the department details.
     */
    public Department getDepartment(int departmentId) throws EmployeeException;

    /**
     * Checks if the department is present or not by department Id
     * @param departmentId   Id of the department
     * @return boolean       If department is present, it returns true, or else return false
     * @throws EmployeeException   If exception occurs, while checking for the department
     */
    public boolean isDepartmentPresent(int departmentId) throws EmployeeException;

    /**
     * Get employees for the particular department using departmentId
     *@param departmentId       Id of the department
     *@return List<Employee>    list of employees in that department
     *@throws EmployeeException  If exception occurs, while getting the list of employees.
     */
    public List<Employee> getEmployeesByDepartment(int departmentId) throws EmployeeException;

    /**
     * Update department name in the database
     * @param Department   DepartmentName
     * @return Department  If department is updated, returns department object
     * @throws EmployeeException   If exception occurs, while updating the department
     */
    public Department updateDepartmentName(Department department) throws EmployeeException;

    /**
     * Delete department 
     * @param Department   department id and department name
     * @return boolean     If department is deleted, returns true or else return false
     * @throws EmployeeException   If exception occurs, while deleting the department.
     */
    public boolean isDepartmentDeleted(Department department) throws EmployeeException;

}