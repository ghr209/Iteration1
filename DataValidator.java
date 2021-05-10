package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    public static boolean isValidCPR(String cpr) {
        Boolean result = false;
        //Check the length
        if (cpr.length()==11) {
            result = true;
        } else {
            return false;
        }
        //Check the format (two numbers)
        //First split the cpr number
        String birthday = cpr.substring(0,6);
        Character hyphen = cpr.charAt(6);
        String lastNumber = cpr.substring(7,11);
        try {//test if the first part is a number }
            int d = Integer.parseInt(birthday);
        } catch (NumberFormatException nfe) {
            System.out.println("First part is not a number");
            return false;
        }
        if (! hyphen.equals('-')) {
            System.out.println("Missing hyphen");
            return false; };
        try {//test if the last part is a number }
            int running = Integer.parseInt(lastNumber);
        } catch (NumberFormatException nfe){
            System.out.println("Last part is a number");
            return false;
        }
        return result;
    }
    public static boolean isValidFirstName(String ForName) {
        return ForName.length() > 1 && ForName.chars().allMatch(Character::isAlphabetic);
    }

    public static boolean isValidLastName(String LastName) {
        return LastName.length() > 1 && LastName.chars().allMatch(Character::isAlphabetic);
    }
    public static boolean isValidNumber(String Number) {
        Boolean result = false;//Check the length
        if (Number.length() == 8) {
            result = true;
        } else {
            return false;
        }
        try {//test if the first part is a number }
            int d = Integer.parseInt(Number);
        } catch (NumberFormatException nfe) {
            System.out.println(" phoneNumber is not a number");
            return false;
        }
        return result;
    }
    public static boolean isValidEmail(String Email) { // taget fra jakob
        String EMAIL_PATTERN = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$");
        Matcher matcher = pattern.matcher(Email);
        return matcher.matches();
    }
}


