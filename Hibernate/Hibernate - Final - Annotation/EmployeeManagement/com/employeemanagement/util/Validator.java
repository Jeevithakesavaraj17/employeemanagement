package com.employeemanagement.util;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
* This class contains methods for Validation.
*/
public class Validator {

    /**
    * This method is used to validate name 
    *@param inputString   string which we have to validate 
    *@return boolean      If input string contains empty space or number or empty string returns false,
    *                     else return true.
    */
    public static boolean isValidName(String inputString) {
        String str = inputString;
        String emptyString = "";
        if (str.equals(emptyString)) {
            return false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (!(ch >= 'a' && ch <= 'z'|| ch >= 'A' && ch <= 'Z')) {
                    return false;
                }
            }
            return true;
        }
    } 
    /**
    * This method is used to validate date.
    *@param  inputDate   date which we have to validate
    *@return boolean     If input date is in YYYY/MM/DD format, return true
    *                    else return false.
    */
    public static boolean isValidDate(String inputDate) { 
        try {
             LocalDate date = LocalDate.parse(inputDate);
             return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
    * This method is used to validate phone number
    *@param phoneNumber   phone number which we have to validate
    *@return  boolean     If given phone number has ten digit number, returns true
    *                     else return false; 
    */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String str = phoneNumber;
        if (str.length() == 10) {
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (!(ch >= '0' && ch <= '9')) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    } 
    /**
    * This method is used to validate mail Id.
    *@param  mailId    mail id which we have to validate 
    *@return boolean    If mail Id has lower case alphabets and number with @, returns true
    *                   else returns false.
    */
    public static boolean isValidMailId(String mailId) {
        String regex = "^[a-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mailId);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }  
    
    /**
    *This method is used to validate experience
    *@param exerience   experience which we have to validate
    *@return boolean    If experience is betweeen 0 and 30, returns true
    *                   else false.
    */
    public static boolean isValidExperience(int experience) {
        if (experience >= 0 && experience < 30) {
            return true;
        } else {
            return false;
        }
    }

    /**
    *This method is used to valid floor number
    *@param floorNumber  floor number which we have to validate
    *@return boolean    If floor number betweeen 1 and 10, returns true
    *                   else returns false.
    */
    public static boolean isValidFloor(int floorNumber) {
        if (floorNumber > 0 && floorNumber < 10) {
            return true;
        } else {
            return false;
        }
    }

}