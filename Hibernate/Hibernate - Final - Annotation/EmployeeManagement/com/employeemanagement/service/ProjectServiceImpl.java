package com.employeemanagement.service;

import java.util.List;
import java.util.ArrayList;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.dao.ProjectDao;
import com.employeemanagement.dao.ProjectDaoImpl;
import com.employeemanagement.model.Project;

/**
 *<p>
 * This class implements project service and manages the project and get the project details
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since 2024/07/20
 */
public class ProjectServiceImpl implements ProjectService {
    ProjectDao projectDao = new ProjectDaoImpl();
    
    @Override
    public Project addProject(String projectName) throws EmployeeException {
        return projectDao.insertProject(projectName);
    }

    @Override
    public List<Project> getProjects() throws EmployeeException {
        return projectDao.getProjects();
    }

    @Override
    public Project getProject(int projectId) throws EmployeeException {
        return projectDao.getProject(projectId);
    }

    @Override 
    public Project updateProjectName(Project project) throws EmployeeException {
        return projectDao.updateProjectName(project);
    }

    @Override
    public void addProjectToEmployee(Project project, Employee employee) throws EmployeeException {
        projectDao.addProjectToEmployee(project, employee);
    }

    @Override
    public List<Employee> getEmployeesByProject(int projectId) throws EmployeeException {
        return projectDao.getEmployeesByProject(projectId);
    }

    @Override
    public boolean isProjectDeleted(Project project) throws EmployeeException {
        return projectDao.isProjectDeleted(project);
    }
}