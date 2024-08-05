package com.employeemanagement.model;

import com.employeemanagement.model.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * <p>
 * This classs represents salary account details of the employee
 * id               Id of the account
 * accountNumber    employee's Account number
 * ifscCode         IFSC code of the account
 * </p>
 *
 * @author Jeevithakesavaraj
 * @version 1.0
 * @since  2024/07/30
 */

@Entity
@Table(name = "salary_account")
public class SalaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "ifsc_code")
    private String ifscCode;

    public SalaryAccount() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public int getId() {
        return id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    
    public String getIfscCode() {
        return ifscCode;
    }

    @Override
    public String toString() {
        return "Account Number : " + accountNumber
                + "\nIFSC Code : " + ifscCode;
    }
}