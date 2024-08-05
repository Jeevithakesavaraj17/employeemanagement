package com.employeemanagement.dao;

import java.util.ArrayList;
import java.util.List; 

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Department;

/**
 * <p>
 * This interface have method declaration for CRUD operation in department repository.
 * </p>
 *
 * @author JeevithaKesavaraj
 * @version 1.0
 * @since 2024/07/30
 */
public interface DepartmentDao {

    /**
     * Add the department to department repository.
     * @param department        department deatils
     * @return Department       If department added, return department
     * @throws EmployeeException  If exception occurs while adding the department to the database.
     */
    public Department insertDepartment(Department department) throws EmployeeException;

    /**
     * Get the list of departments
     * @return List<Department>  list of departments
     * @throws EmployeeException   If exception occurs, while getting the list of departments
     */ 
    public List<Department> retrieveDepartments() throws EmployeeException;

    /**
     * Get the department by the departmentId
     * @param departmentId     Id of the department  
     * @return department      department object
     * @throws EmployeeException   If exception occurs, while getting the department object.
     */
    public Department retrieveDepartment(int departmentId) throws EmployeeException;

    /**
     * Get the list of employees by department id
     * @param departmentId   Id of the department
     * @return List<Employee>   list of the employees
     * @throws EmployeeException   If exception occurs, while getting the list of employees in the particular department.
     */
    public List<Employee> retrieveEmployeesByDepartment(int departmentId) throws EmployeeException;

    /**
     * Update department name in the database
     * @param Department   DepartmentName
     * @return Department   If department added, returns department or else throw exception
     * @throws EmployeeException   If exception occurs, while updating the department
     */
    public Department updateDepartmentName(Department department) throws EmployeeException;

    /**
     * Delete department
     * @param department   department object(department details)
     * @return boolean     If department deleted, return true or else return false.
     * @throws EmployeeException   If exception occurs, while deleting tha department.
     */
    public boolean isDepartmentDeleted(Department department) throws EmployeeException;

}