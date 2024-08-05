package com.employeemanagement.dao;

import java.util.List; 

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Project;

/**
 * <p>
 * This interface consists method declaration of insert and retireve project details of the employee for project dao
 * </p>
 *
 * @author Jeevithakesavaraj
 * @version 1.0
 * @since 2024/07/30
 */
public interface ProjectDao {

    /**
     * Add project details to the project table 
     * @param project   project details
     * @return project  If project added, it returns project object
     * @throws EmployeeException   If exception occurs, while adding the project details
     */
    public Project insertProject(Project project) throws EmployeeException;

    /**
     * Get list of projects
     * @return List<Project>   list of projects
     * @throws EmployeeException    If exception occurs, while getting the list of projects
     */
    public List<Project> retrieveProjects() throws EmployeeException;

    /**
     * Get project from the database
     * @param projectId   Id of the project
     * @return Project    If project present, return project object or else return null
     * @throws EmployeeException   If exception occurs, while getting the project by projectId
     */
    public Project retrieveProject(int projectId) throws EmployeeException;
    
    /**
     * Update project name in the database
     * @param Project   project details
     * @return Project  If project is updated, returns project object
     * @throws EmployeeException   If exception occurs, while updating the project
     */
    public Project updateProjectName(Project project) throws EmployeeException;

    /**
     * Add project to the employee
     * @param Employee           employee who we have to add project
     * @param Project             project that is added to the employee
     * @throws EmployeeException  If exception occurs, while adding the project to the employee
     */
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException;

    /**
     * Get list of employees for the particular project using projectId
     * @param projectId          Id of the project
     * @return List<Employee>    If employees present, return employee list or else return null
     * @throws EmployeeException If exception occurs, while getting the employees for the project by projectId
     */
    public List<Employee> retrieveEmployeesByProject(int projectId) throws EmployeeException;

    /**
     * Delete project in project table
     * @param Project            project id and project name
     * @return boolean           If project is deleted, returns true or else return false
     * @throws EmployeeException   If exception occurs, while deleting the project.
     */
    public boolean isProjectDeleted(Project project) throws EmployeeException;

}