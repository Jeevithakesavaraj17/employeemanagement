package com.employeemanagement.dao;

import java.util.ArrayList;
import java.util.List; 

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Project;

/**
 *<p>
 * This interface consists method declaration for project repository
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since 2024/07/20
 */
public interface ProjectDao {

    /**
     * Add project to the list
     * @param Project   project details(id, name)
     * @return project  If project added, it returns project object
     * @throws EmployeeException   If exception occurs, while adding the project details
     */
    public Project insertProject(String name) throws EmployeeException;

    /**
     * Get list of projects
     * @return List<Project>   list of projects
     * @throws EmployeeException    If exception occurs, while getting the list of projects
     */
    public List<Project> getProjects() throws EmployeeException;

    /**
     * Get project from the database
     *@param projectId   Id of the project
     *@return Project    If project present, return project object or else return null
     *@throws EmployeeException   If exception occurs, while getting the project by projectId
     */
    public Project getProject(int projectId) throws EmployeeException;
    
    /**
     * Update project name in the database
     * @param Project   project
     * @return Project  If project is updated, returns project object
     * @throws EmployeeException   If exception occurs, while updating the project
     */
    public Project updateProjectName(Project project) throws EmployeeException;

    /**
     * Add project to the employee
     *@param Employee
     *@param Project
     *@throws EmployeeException   If exception occurs, while adding the project to the employee
     */
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException;

    /**
     * Get list of employees for the particular project using projectId
     *@param projectId   Id of the project
     *@return List<Employee>    If employees present, return employee list or else return null
     *@throws EmployeeException   If exception occurs, while getting the employees for the project by projectId
     */
    public List<Employee> getEmployeesByProject(int projectId) throws EmployeeException;

    /**
     * Delete project 
     *@param Project   project id and project name
     *@return boolean     If project is deleted, returns true or else return false
     *@throws EmployeeException   If exception occurs, while deleting the project.
     */
    public boolean isProjectDeleted(Project project) throws EmployeeException;

}