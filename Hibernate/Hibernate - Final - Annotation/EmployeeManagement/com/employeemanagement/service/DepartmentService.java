package com.employeemanagement.service;

import java.util.List;
import java.util.ArrayList;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.dao.DepartmentDao;
import com.employeemanagement.model.Department;

/**
 *<p>
 * This interface contains method declaration for the department service.
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since  2024-07-20
 */
public interface DepartmentService {

    /**
     * Add Department to the list
     *@param DepartmentName   Name of the department
     *@return Department         If department added, return department
     *throws EmployeeException  If exception occurs, while inserting the department details.
     */
    public Department addDepartment(String departmentName) throws EmployeeException;

    /**
     * Get list of Departments
     *@return List<Department>    list of Departments
     *@throws EmployeeException   If exception occurs, while getting the list of departments.
     */
    public List<Department> getDepartments() throws EmployeeException;

    /**
     * Get department by departmentId
     *@param departmentId   Id of the department
     *@return department    department object (id, name)
     *@return null          If department is not present
     *@throws EmployeeException   If exception occurs, while getting the department details.
     */
    public Department getDepartment(int departmentId) throws EmployeeException;

    /**
     * Get employees for the particular department using departmentId
     *@param departmentId       Id of the department
     *@return List<Employee>    list of employees
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
     *@param Department   department id and department name
     *@return boolean     If department is deleted, returns true or else return false
     *@throws EmployeeException   If exception occurs, while deleting the department.
     */
    public boolean isDepartmentDeleted(Department department) throws EmployeeException;

}