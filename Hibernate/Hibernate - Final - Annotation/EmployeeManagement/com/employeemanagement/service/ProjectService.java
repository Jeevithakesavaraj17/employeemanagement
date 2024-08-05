package com.employeemanagement.service;

import java.util.List;
import java.util.ArrayList;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.dao.ProjectDao;
import com.employeemanagement.model.Project;

/**
 *<p>
 * This interface consists of method declaration for project service
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since   2024/07/20
 */
public interface ProjectService {
 
    /**
     * Add project to the list
     * @param Project    project details(project id and name)
     * @return Project    If project added, return project object
     * @throws EmployeeException   If exception occurs, while inserting the project details.
     */
    public Project addProject(String projectName) throws EmployeeException;

    /**
     * Get list of projects
     * @return List<Project>    list of projects
     * @throws EmployeeException If exception occurs, while getting the list of projects.
     */
    public List<Project> getProjects() throws EmployeeException;

    /**
     * Get project from the list of projects using project Id
     * @param projectId   Id of the project
     * @return Project    If project present, return project object or else return null.
     * @throws EmployeeException   If exception occurs, while getting the project
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
     * Add project details to the employee
     * @param Project    Project details that is added to the employee
     * @param employee   Employee to whom we have add the project
     * @throws EmployeeException   If exception occurs, while adding the project to the employee
     */
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException;

    /**
     * Get list of employees by project Id
     * @param projectId   Id of the project
     * @return List<Employee>    If employees present, return employees or else return null.
     * @throws EmployeeException   If exception occurs, while getting the employees for the projectId
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