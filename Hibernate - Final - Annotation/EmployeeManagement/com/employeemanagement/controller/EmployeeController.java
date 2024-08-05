package com.employeemanagement.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.model.Department;
import com.employeemanagement.model.Employee;
import com.employeemanagement.model.Project;
import com.employeemanagement.model.SalaryAccount;
import com.employeemanagement.service.DepartmentService;
import com.employeemanagement.service.DepartmentServiceImpl;
import com.employeemanagement.service.EmployeeService;
import com.employeemanagement.service.EmployeeServiceImpl;
import com.employeemanagement.service.ProjectService;
import com.employeemanagement.service.ProjectServiceImpl;
import com.employeemanagement.service.SalaryAccountService;
import com.employeemanagement.service.SalaryAccountServiceImpl;
import com.employeemanagement.util.Validator;

/**
 * <p>
 * This class is used for displaying menu for employee Management and
 * have some methods for getting employee details, add and display employee, employees.
 * </p>
 *
 * @author Jeevithakesavaraj
 * @version 1.0
 * @since  2024/07/30
 */
public class EmployeeController {
    static Scanner scanner = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeServiceImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();
    ProjectService projectService = new ProjectServiceImpl();
    SalaryAccountService salaryAccountService = new SalaryAccountServiceImpl();
    
    /**
     * Display menu which has functions to get employee input and display employee details.
     */
    public void displayMenu() {
        boolean isExit = false;
        while (!isExit) {
            System.out.println("--------------------------------------------");
            System.out.println("Employee Services");
            System.out.println("1.Add Employee\n2.Display Employees"
                               + "\n3.update Employee\n4.Add Project"
                               + "\n5.Display Projects of Employee"
                               + "\n6.Delete Employee"
                               + "\n7.Exit.");
            int userChoice = getValidNumber();
            switch (userChoice) {
            case 1:
                addEmployeeDetails();
                break;
            case 2:
                displayEmployees();
                break;
            case 3:
                updateEmployeeDetails();
                break;
            case 4:
                addProjectToEmployee();
                break;
            case 5:
                displayProjectsOfEmployee();
                break;
            case 6:
                deleteEmployee();
                break;
            case 7:
                isExit = true;
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid Choice.");
            }
        }
    }

    /**
     * Get employee details and add employee data to the list.
     */
    public void addEmployeeDetails() {
        System.out.println("Add Employee:");
        scanner.nextLine();
        String employeeName = getEmployeeName();
        LocalDate birthDate = getDateOfBirth();
        long phoneNumber = getPhoneNumber();
        String mailId = getMailId();
        int experience = getExperience();
        try {
            List<Department> departments = departmentService.getDepartments();
            if (departments.isEmpty()) {
                System.out.println("No Departments found.");
            } else{
                String format = "| %-5s | %-15s \n";
                System.out.format(format, "Id", "Name");
                for(Department department : departments) {
                    System.out.format(format, department.getDepartmentId(), 
                                              department.getDepartmentName());
                }
                System.out.print("Enter Department Id : ");
                int departmentId = scanner.nextInt();
                while (!departmentService.isDepartmentPresent(departmentId)) {
                    System.out.print("No Department found. Please Enter valid departmentId : ");
                    departmentId = scanner.nextInt();
                }
                System.out.print("Enter Account Number : ");
                long accountNumber = scanner.nextLong();
                scanner.nextLine();
                System.out.print("Enter IFSC Code : ");
                String ifscCode = scanner.nextLine();
                Employee employee = employeeService.addEmployee(employeeName, birthDate,
                                                          phoneNumber, mailId, experience, 
                                                         departmentId, accountNumber, ifscCode);
                System.out.println(employee);
                System.out.println("Employee added successfully.");
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        } 
    }

    /**
     * Display list of employees
     */
    public void displayEmployees() {
        System.out.println("Employees");
        try {
            if (employeeService.isEmployeeListEmpty()) {
                System.out.println("No Employee Records...");
            } else {
                List<Employee> employees = employeeService.getEmployees();
                String format = "| %-4s | %15s | %-4s | %-15s | %-12s | %-12s | %-20s | %-10s | %-20s \n";
                System.out.format(format, "Id", "Name", "Age", "DepartmentName",
                                          "AccountNumber", "PhoneNumber", "MailId",
                                          "Experience", "Projects");
                for (Employee employee : employees) {
                    System.out.format(format, employee.getEmployeeId(),
                                      employee.getEmployeeName(),
                                      employee.getAge(),
                                      employee.getDepartment().getDepartmentName(),
                                      employee.getSalaryAccount().getAccountNumber(),
                                      employee.getPhoneNumber(),
                                      employee.getMailId(),
                                      employee.getExperience(),
                                      employee.getProjectDetails());
                }
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get Id of the employee and update their details.
     */
    public void updateEmployeeDetails() {
        int employeeId = getEmployeeId();
        try {
            if (!employeeService.isEmployeePresent(employeeId)) {
                System.out.println("No Employee Found.");
            } else {
                Employee employee = employeeService.getEmployeeById(employeeId);
                System.out.println(employee);
                System.out.println("----------------------------------------");
                System.out.println("1.Update Name\n2.Update Date of Birth"
                                   + "\n3.Update phone number\n4.Update mailId"
                                   + "\n5.Update Experience"
                                   + "\n6.Update Department"
                                   + "\n7.Update SalaryAccount");
                int choice = getValidNumber();
                scanner.nextLine();
                switch (choice) {
                case 1:
                    String employeeName = getEmployeeName();
                    employee.setEmployeeName(employeeName);
                    break;
                case 2:
                    LocalDate dateOfBirth = getDateOfBirth();
                    employee.setDateOfBirth(dateOfBirth);
                    break;
                case 3:
                    long phoneNumber = getPhoneNumber();
                    employee.setPhoneNumber(phoneNumber);
                    break;
                case 4:
                    String mailId = getMailId();
                    employee.setMailId(mailId);
                    break;
                case 5:
                    System.out.print("Enter Experience : ");
                    int experience = scanner.nextInt();
                    employee.setExperience(experience);
                    break;
                case 6:
                    List<Department> departments = departmentService
                                                       .getDepartments();
                    for (Department department : departments) {
                        System.out.println(department.getDepartmentId()
                                           + " "
                                           + department.getDepartmentName());
                    }
                    System.out.print("Enter Department Id : ");
                    int departmentId = scanner.nextInt();
                    while (!departmentService.isDepartmentPresent(departmentId)) {
                        System.out.print("Invalid departmentId. Please enter correct Id : ");
                        departmentId = scanner.nextInt();
                    }
                    Department department = departmentService.getDepartment(departmentId);
                    employee.setDepartment(department);
                    break;

                case 7:
                    System.out.print("Enter Account Number : ");
                    long accountNumber = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter IFSC Code : ");
                    String ifscCode = scanner.nextLine();
                    SalaryAccount salaryAccount = employee.getSalaryAccount();
                    salaryAccount.setAccountNumber(accountNumber);
                    salaryAccount.setIfscCode(ifscCode);
                    salaryAccountService.updateSalaryAccount(salaryAccount); 
                    employee.setSalaryAccount(salaryAccount);
                    break;
                default:
                    System.out.println("Invalid choice.");
                }
                Employee updatedEmployee = employeeService.updateEmployeeDetails(employee);
                System.out.println(updatedEmployee);
                System.out.println("Updated successfully");
            }   
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    /**
     * Get Id of the employee, project Id and
     * add project to the employee according to the employeeId.
     */
    public void addProjectToEmployee() {
        int employeeId = getEmployeeId();
        try {
            if (!employeeService.isEmployeePresent(employeeId)) {
                System.out.println("No Employee Found.");
            } else {
                Employee employee = employeeService.getEmployeeById(employeeId); 
                List<Project> projects = projectService.getProjects();
                if (projects.isEmpty()) {
                    System.out.println("No projects found.");
                } else {
                    String format = "| %-4s | %15s \n";
                    System.out.format(format, "Id", "Name");
                    for(Project project : projects) {
                        System.out.format(format, project.getProjectId(), 
                                            project.getProjectName());
                    }
                    System.out.print("Enter ProjectId : ");
                    int projectId = scanner.nextInt();
                    Project project = projectService.getProject(projectId);
                    if (project == null) {
                        System.out.println("No project found.");
                    } else {
                        employeeService.addProjectToEmployee(project, employee);
                        System.out.println("Project added successfully.");
                    }
                }
            }  
        } catch (EmployeeException e) {
            System.out.println("Employee is already assigned to the project" + e.getMessage());
        }
    }

    /**
     * Get employeeId and display all projects in that employeeId.
     */
    public void displayProjectsOfEmployee() {
        System.out.println("Display Projects by Employee");
        int employeeId = getEmployeeId();
        try {
            if (!employeeService.isEmployeePresent(employeeId)) {
                System.out.println("No employeeFound.");
            } else {
                Employee employee = employeeService.getEmployeeById(employeeId);
                List<Project> projects = new ArrayList<>(employee.getProjects());
                if (projects.isEmpty()) {
                    System.out.println("No Projects Found.");
                } else {
                    String format = "| %-4s | %15s \n";
                    System.out.format(format, "Id", "Name");
                    for(Project project : projects) {
                        System.out.format(format, project.getProjectId(), 
                                            project.getProjectName());
                    }
                }
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }      
    }

    /**
     * Get employee Id and delete that Employee.
     */
    public void deleteEmployee() {
        int employeeId = getEmployeeId();
        try {
            if (employeeService.isEmployeePresent(employeeId)) {
                if (employeeService.isEmployeeDeleted(employeeId)) {
                    System.out.println("Employee " + employeeId + " Deleted.");
                }
            } else {
                System.out.println("No employee found.");
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**  
     * Get employeeId and validate that id.
     *@return employeeId    Id of the employee
     */
    public int getEmployeeId() {
        int employeeId = 0;
        boolean isValid = true;
        while (isValid) {
            try {
                System.out.print("Enter Employee ID : ");
                employeeId = scanner.nextInt();
                isValid = false;                
            } catch (Exception e) {
                System.out.println("Invalid type.");
                scanner.next();
            }
        }
        return employeeId;
    }

    /**  
     * Get employee name and validate that name.
     *@return employeeName    Name of the employee
     */
    public String getEmployeeName() {
        System.out.print("Enter Name : ");
        String employeeName = scanner.nextLine();
        while (!Validator.isValidName(employeeName)) {
            System.out.println("Invalid format."
                               + "Enter Name :");
            employeeName = scanner.nextLine();
        }
        return employeeName;
    }

    /**
     * Get employee date of birth and validate.
     *@return daeOfBirth     date of birth of the employee
     */
    public LocalDate getDateOfBirth() {
        LocalDate dateOfBirth = null;
        String date = null;
        try {
            date = scanner.nextLine();
            System.out.print("Enter Date Of Birth (YYYY-MM-DD) : ");
            while (!Validator.isValidDate(date)) {
                System.out.print("Invalid format. Please enter correct"
                                  + "format(YYYY-MM-DD) : ");
                date = scanner.nextLine();
            }
            dateOfBirth = LocalDate.parse(date);
        } catch (InputMismatchException e) {
            System.out.print("Invalid format. Please enter correct"
                                  + "format(YYYY-MM-DD) : ");
            date = scanner.nextLine();
        }
        return dateOfBirth;
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

    /**
     * Get employee phone number and validate.
     * @return phoneNumber     employee's phone number
     */
    public long getPhoneNumber() {
        long phoneNumber = 0; 
        String mobileNumber = null;
        try {
            System.out.print("Enter PhoneNumber : ");
            phoneNumber = scanner.nextLong();
            mobileNumber = String.valueOf(phoneNumber);
            while (!Validator.isValidPhoneNumber(mobileNumber)) {
                System.out.print("Invalid format."
                                 + "Please Enter PhoneNumber :");
                phoneNumber = scanner.nextLong();
                mobileNumber = String.valueOf(phoneNumber);
            }
        } catch (InputMismatchException e) {
            System.out.print("Invalid format."
                                 + "Please Enter PhoneNumber :");
            phoneNumber = scanner.nextLong();
            mobileNumber = String.valueOf(phoneNumber);
        }
        return phoneNumber;
    }

    /**
     * Get employee mail Id and validate
     * @return mailId    employee's mailId
     */
    public String getMailId() {
        System.out.print("Enter MailId : ");
        String mailId = scanner.nextLine();
        while (!Validator.isValidMailId(mailId)) {  
            System.out.print("Invalid format."
                             + "Please enter correct format :");
            mailId = scanner.nextLine();
        }
        return mailId;
    } 

    /**
     * Get employee experience and validate
     * @return experience     employee's experience
     */ 
    public int getExperience() {
        int experience = 0;
        try {
            System.out.print("Enter Experience : ");
            experience = scanner.nextInt();
            while (!Validator.isValidExperience(experience)) {
                System.out.print("Invalid format."
                                 + "Please Enter correct Experience :");
                experience = scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.print("Invalid format."
                                 + "Please Enter correct Experience :");
            experience = scanner.nextInt();
        }
        return experience;
    }
}