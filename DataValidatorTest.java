package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataValidatorTest {

    @Test
    void testIsValidCPRTest1() {
        DataValidator dataValidator = new DataValidator();
        String cprNumber = "126310-abcR";
        Boolean actualResult = dataValidator.isValidCPR(cprNumber);
        System.out.println("Test1");
        assertEquals(false,actualResult, "CPR-number must not contain letters");
    }

    @Test
    void isValidCPRTest2() {
        DataValidator dataValidator = new DataValidator();
        String cprNumber = "1234567890";
        Boolean actualResult = dataValidator.isValidCPR(cprNumber);
        System.out.println("Test2");
        assertEquals(false,actualResult, "CPR-number must not contain less than 10 numbers");
    }

    @Test
    void isValidCPRTest3() {
        DataValidator dataValidator = new DataValidator();
        String cprNumber = "123456-7890";
        Boolean actualResult = dataValidator.isValidCPR(cprNumber);
        System.out.println("Test3");
        assertEquals(true,actualResult, "Ok..");
    }

    @Test
    void isValidFirstNameTest1() {
        DataValidator dataValidator = new DataValidator();
        String letterName = "12345677890";
        Boolean actualResult = dataValidator.isValidFirstName(letterName);
        System.out.println("Test1");
        assertEquals(false,actualResult, "Name must not contain numbers");
    }

    @Test
    void isValidForNameTest2() {
        DataValidator dataValidator = new DataValidator();
        String letterName = "1234abcd";
        Boolean actualResult = dataValidator.isValidFirstName(letterName);
        System.out.println("Test2");
        assertEquals(false,actualResult, "Name must not contain numbers and letters");
    }

    @Test
    void isValidForNameTest3() {
        DataValidator dataValidator = new DataValidator();
        String letterName = "Helin";
        Boolean actualResult = dataValidator.isValidFirstName(letterName);
        System.out.println("Test3");
        assertEquals(true,actualResult, "Ok...");
    }

    @Test
    void isValidLastNameTest1() {
        DataValidator dataValidator = new DataValidator();
        String letteName = "12345677890";
        Boolean actualResult = dataValidator.isValidLastName(letteName);
        System.out.println("Test1");
        assertEquals(false,actualResult, "Name must not contain numbers");
    }

    @Test
    void isValidLastNameTest2() {
        DataValidator dataValidator = new DataValidator();
        String letteName = "1234abcd";
        Boolean actualResult = dataValidator.isValidLastName(letteName);
        System.out.println("Test2");
        assertEquals(false,actualResult, "Name must not contain numbers and letters");
    }

    @Test
    void isValidLastNameTest3() {
        DataValidator dataValidator = new DataValidator();
        String letteName = "Helin";
        Boolean actualResult = dataValidator.isValidLastName(letteName);
        System.out.println("Test3");
        assertEquals(true,actualResult, "Ok...");
    }

    @Test
    void isValidNumberTest1() {
        DataValidator dataValidator = new DataValidator();
        String numberNumber = "12";
        Boolean actualResult = dataValidator.isValidNumber(numberNumber);
        System.out.println("Test1");
        assertEquals(false,actualResult, "Wrong format!");
    }

    @Test
    void isValidNumberTest2() {
        DataValidator dataValidator = new DataValidator();
        String numberNumber = "hejmeddig";
        Boolean actualResult = dataValidator.isValidNumber(numberNumber);
        System.out.println("Test2");
        assertEquals(false,actualResult, "Only numbers!");
    }

    @Test
    void isValidNumberTest3() {
        DataValidator dataValidator = new DataValidator();
        String numberNumber = "12345678";
        Boolean actualResult = dataValidator.isValidNumber(numberNumber);
        System.out.println("Test3");
        assertEquals(true,actualResult, "Ok...");
    }

    @Test
    void isValidEmailTest1() {
        DataValidator dataValidator = new DataValidator();
        String email = "hej.dk";
        Boolean actualResult = dataValidator.isValidEmail(email);
        System.out.println("Test1");
        assertEquals(false,actualResult, "Wrong format. It must contain snabela");
    }

    @Test
    void isValidEmailTest2() {
        DataValidator dataValidator = new DataValidator();
        String email = "hej@";
        Boolean actualResult = dataValidator.isValidEmail(email);
        System.out.println("Test2");
        assertEquals(false,actualResult, "Short length");
    }
    @Test
    void isValidEmailTest3() {
        DataValidator dataValidator = new DataValidator();
        String email = "hejmeddig@hotmail.com";
        Boolean actualResult = dataValidator.isValidEmail(email);
        System.out.println("Test3");
        assertEquals(true,actualResult, "Ok...");
    }
}