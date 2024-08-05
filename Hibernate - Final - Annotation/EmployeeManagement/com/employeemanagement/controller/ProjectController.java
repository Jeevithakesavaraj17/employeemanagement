package com.employeemanagement.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Project;
import com.employeemanagement.service.ProjectService;
import com.employeemanagement.service.ProjectServiceImpl;
import com.employeemanagement.util.Validator;

/**
 *<p>
 * This class is used for displaying menu for project Management and
 * have some methods for getting project details, add and display employees.
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since  2024-07-20
 */
public class ProjectController {
    Scanner scanner = new Scanner(System.in);
    ProjectService projectService = new ProjectServiceImpl();

    /**
     * Display menu which has functions to get project details and display project details.
     */
    public void displayMenu() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("-------------------------------------------"
                               + "\n1.Add Project\n2.Display Projects"
                               + "\n3.Display Employees by Project"
                               + "\n4.Update Project name"
                               + "\n5.Delete Project\n6.Exit");
            int userChoice = getValidNumber();
            switch (userChoice) {
            case 1:
                addProject();
                break;
            case 2:
                displayProjects();
                break;
            case 3:
                displayEmployeesByProjectId();
                break;
            case 4:
                updateProjectName();
                break;
            case 5:
                deleteProject();
                break;
            case 6:
                isExit = true;
                System.out.println("Exiting..");
                break;
            default:
                System.out.println("Invalid choice.");
            }
        }    
    }

    /**
    * Get ProjectId and Project name and add that project details
    */
    public void addProject() {
        scanner.nextLine();
        System.out.print("Enter Project Name : ");
        String projectName = scanner.nextLine();
        try {
            Project project = projectService.addProject(projectName);
            System.out.println(project);
            System.out.println("Project added successfully");
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
    * Display list of projects
    */
    public void displayProjects() {
        try {
            System.out.println("List of Projects");
            List<Project> projects = projectService.getProjects();
            String format = "| %-4s | %15s \n";
            System.out.format(format, "Id", "Name");
            for(Project project : projects) {
                System.out.format(format, project.getProjectId(), 
                                  project.getProjectName());
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get project id and display employees by that project id.
     */
    public void displayEmployeesByProjectId() {
        System.out.println("Display employees by Project Id");
        displayProjects();
        System.out.print("Enter ProjectId:");
        int projectId = scanner.nextInt();
        try {
            Project project = projectService.getProject(projectId);
            List<Employee> employees = new ArrayList<>(project.getEmployees());
            String format = "| %-4s | %15s | %-4s | %-12s | %-12s | %-20s | %-10s \n";
            System.out.format(format, "Id", "Name", "Age", "DepartmentId", "PhoneNumber", "MailId", "Experience");
            for (Employee employee : employees) {
                System.out.format(format, employee.getEmployeeId(),
                                      employee.getEmployeeName(),
                                      employee.getAge(),
                                      employee.getDepartment().getDepartmentId(),
                                      employee.getPhoneNumber(),
                                      employee.getMailId(),
                                      employee.getExperience());
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /** 
     * Update project name by getting project Id and new name for the project
     */
    public void updateProjectName() {
        System.out.print("Enter Project Id : ");
        int projectId = scanner.nextInt();
        try {
            Project project = projectService.getProject(projectId);
            if (project== null) {
                System.out.println("No project found.");
            } else {
                scanner.nextLine();
                System.out.print("Enter new name for the project : ");
                String projectName = scanner.nextLine();
                project.setProjectName(projectName);
                Project projectObject = projectService.updateProjectName(project);
                System.out.println(projectObject);
                System.out.println("Project name updated successfully.");
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    } 

    /**
     * Delete the project by project Id
     */
    public void deleteProject() {
        try {
            displayProjects();
            System.out.print("Enter project Id : ");
            int projectId = scanner.nextInt();
            Project project = projectService.getProject(projectId);
            if (project == null) {
                System.out.println("No project Found.");
            } else {
                List<Employee> employees = new ArrayList<>(project.getEmployees());
                if (employees.isEmpty()) {
                    if (projectService.isProjectDeleted(project)) {
                        System.out.println("Project deleted successfully.");
                    }
                } else {
                    System.out.println("Enable to delete the project because it has employees.");
                }
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get number and validate
     * @return number   integer to be validation
     */
    public int getValidNumber() {
        boolean isValid = true;
        while (isValid) {
            try {
                System.out.print("Enter choice : ");
                int validNumber = scanner.nextInt();
                isValid = false;
                return validNumber;
            } catch (InputMismatchException e) {
                System.out.print("Invalid choice.");
                scanner.next();
            }   
        }
        return 0;
    }
}