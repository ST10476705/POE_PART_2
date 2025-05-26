/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_lab
 */
public class LoginTest {
    
    public LoginTest() {
    }

    
    
    
    /**
     * Test of CheckUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("CheckUserName");
        String username = "A_yay";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.CheckUserName(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkpasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckpasswordComplexity() {
        System.out.println("checkpasswordComplexity");
        String password = "Ayanda1!";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkpasswordComplexity(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String CellPhoneNumber = "+27686785643";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(CellPhoneNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String username = "A_yay";
        String password = "Ayanda1!";
        Login instance = new Login();
        String expResult = "Welcome user";
        String result = instance.registerUser(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String username = "A_yay";
        String Password = "Ayanda1!";
        String verifyUsername = "A_yay";
        String verifypassword = "Ayanda1!";
        Login instance = new Login();
        boolean expResult = false;
        boolean result = instance.loginUser(username, Password, verifyUsername, verifypassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnloginstatus method, of class Login.
     */
    @Test
    public void testReturnloginstatus() {
        System.out.println("returnloginstatus");
        String username = "A_yay";
        String Password = "Ayanda1!";
        String verifyusername = "A_ya";
        String verifyPassword = "Ayanda1!";
        Login instance = new Login();
        String expResult = "Login successful";
        String result = instance.returnloginstatus(username, Password, verifyusername, verifyPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
