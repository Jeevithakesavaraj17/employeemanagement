package com.employeemanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Employee;
import com.employeemanagement.service.DepartmentService;
import com.employeemanagement.service.DepartmentServiceImpl;
import com.employeemanagement.service.EmployeeService;
import com.employeemanagement.service.EmployeeServiceImpl;
import com.employeemanagement.util.Validator;

/**
 *<p>
 * This class is for display menu and have methods for get department details and
 * displaying the details of department and list of departments.
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since  2024-07-20
 */
public class DepartmentController {
    Scanner scanner = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeServiceImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();

    /**
     * Display menu which has functions to get department details and display department details.
     */
    public void displayMenu() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("--------------------------------------------");
            System.out.println("1.Add Department\n2.Display Departments"
                               + "\n3.Display Employees by Department\n4.Update Department name\n"
                               + "5.Delete Department\n6.Exit");
            System.out.print("Enter Choice : ");
            int userChoice = scanner.nextInt();
            switch (userChoice) {
            case 1:
                addDepartment();
                break;
            case 2:
                displayDepartments();
                break;
            case 3:
                displayEmployeesByDepartmentId();
                break;
            case 4:
                updateDepartmentName();
                break;
            case 5:
                deleteDepartment();
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
    * Get DepartmentId and Department name and add that department details
    */
    public void addDepartment() {
        scanner.nextLine();
        System.out.print("Enter Department Name : ");
        String departmentName = scanner.nextLine();
        try {
            Department department = departmentService.addDepartment(departmentName); 
            System.out.println(department);
            System.out.println("Department added successfully.");
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
    * Display list of departments
    */
    public void displayDepartments() {
        try {
            List<Department> departments = departmentService.getDepartments();
            String format = "| %-5s | %-15s \n";
            System.out.format(format, "Id", "Name");
            for(Department department : departments) {
                System.out.format(format, department.getDepartmentId(), 
                                          department.getDepartmentName());
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Display list of employees for the particular department Id.
     */
    public void displayEmployeesByDepartmentId() {
        System.out.println("Employees");
        displayDepartments();
        System.out.print("Enter Department Id : ");
        int departmentId = scanner.nextInt();
        try {
            Department department = departmentService.getDepartment(departmentId);
            if (department == null) {
                System.out.println("No department Found.");
            } else {
                List<Employee> employees = new ArrayList<>(department.getEmployees()); 
                if (employees.isEmpty()) {
                    System.out.println("No employees Found");
                } else {
                    String format = "| %-4s | %15s | %-4s | %-12s | %-12s | %-20s | %-10s | %-20s \n";
                    System.out.format(format, "Id", "Name", "Age", "DepartmentId",
                                             "PhoneNumber", "MailId", "Experience", "Projects");
                    for (Employee employee : employees) {
                        if (!employee.getIsDeleted()) {
                            System.out.format(format, employee.getEmployeeId(),
                                                   employee.getEmployeeName(),
                                                   employee.getAge(),
                                                   employee.getDepartment().getDepartmentId(),
                                                   employee.getPhoneNumber(),
                                                   employee.getMailId(),
                                                   employee.getExperience(),
                                                   employee.getProjectDetails());
                        }
                    }
                }
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /** 
     * Update department name by getting department Id and new name for the department
     */
    public void updateDepartmentName() {
        System.out.print("Enter DepartmentId : ");
        int departmentId = scanner.nextInt();
        try {
            Department department = departmentService.getDepartment(departmentId);
            if (department == null) {
                System.out.println("No department found.");
            } else {
                scanner.nextLine();
                System.out.print("Enter new name for the department : ");
                String departmentName = scanner.nextLine();
                department.setDepartmentName(departmentName);
                Department departmentObject = departmentService.updateDepartmentName(department);
                System.out.println(departmentObject);
                System.out.println("Department name updated successfully.");
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }  

    /**
     * Delete the department by department Id
     */
    public void deleteDepartment() {
        try {
            displayDepartments();
            System.out.print("Enter department Id : ");
            int departmentId = scanner.nextInt();
            Department department = departmentService.getDepartment(departmentId);
            if (department == null) {
                System.out.println("No department Found.");
            } else {
                List<Employee> employees = new ArrayList<>(department.getEmployees());
                if (employees.isEmpty()) {
                    if (departmentService.isDepartmentDeleted(department)) {
                        System.out.println("Department deleted successfully.");
                    }
                } else {
                    System.out.println("Enable to delete the department because it has employees.");
                }
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }
}
