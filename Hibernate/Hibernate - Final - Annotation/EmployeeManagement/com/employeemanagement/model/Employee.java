package com.employeemanagement.model;

import java.time.LocalDate;  
import java.time.Period; 
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.employeemanagement.model.Department;
import com.employeemanagement.model.Project;

/**
 *<p>
 * Employee is a person who is working in the company
 * This class is used for employee data
 * Id of the employee, Name, Date of Birth, mailId, Phonenumber
 * department where employee have allotted and experience
 * List of projects that employee have assigned
 *</p>
 *
 *@author Jeevithakesavaraj
 *@version 1.0
 *@since  2024-07-20
 */

@Entity
@Table(name = "employees")
public class Employee {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int employeeId;

    @Column(name = "name")
    private String employeeName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mail_id")
    private String mailId;

    @Column(name = "experience")
    private int experience;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "projects_employees",
               joinColumns = @JoinColumn(name = "employee_id"),
               inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

    public Employee() {}

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMailId() {
        return mailId;
    }

    public int getExperience() {
        return experience;
    }

    public Department getDepartment() {
        return department;
    }
    
    public boolean getIsDeleted() {
        return isDeleted;
    }
    /**
     * Get projects in th employee.
     *@return Set<Project>   list of projects
     */
    public Set<Project> getProjects() {
        return projects;
    }
    
    /**
     * Get the set of projects from employee
     * String projectNames Names of the projects where employee has assigned
     */
    public String getProjectDetails() {
        StringBuilder projectDetails = new StringBuilder();
        for (Project project : getProjects()) {
            projectDetails.append(project.getProjectName() + ", ");
        }
        return projectDetails.toString();
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void setIsDeleted() {
        this.isDeleted = true;
    }

    /**
     * To calculate age from Date of Birth 
     *@return age      age of particular employee
     */
    public int getAge() {
        LocalDate dob = getDateOfBirth();
        LocalDate curDate = LocalDate.now(); 
        return Period.between(dob, curDate).getYears();
    }

    @Override
    public String toString() {
        return "Employee Id :" + employeeId
              + "\nEmployee Name : " + employeeName 
              + "\nAge : " + getAge()
              + "\nDepartment Id : " 
              + department.getDepartmentId() 
              + "\nPhone Number : " + phoneNumber
              + "\nMail Id : "+ mailId 
              + "\nExperience : " + experience;
    }
}