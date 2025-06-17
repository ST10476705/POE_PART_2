/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

/**
 *
 * @author RC_Student_lab
 */
class Login {
public boolean CheckUserName(String username) {
        return username.contains("_") && username.length() == 5;
    }

    public boolean checkpasswordComplexity(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*()_+<>:{}].*");
    }

    public boolean checkCellPhoneNumber(String CellPhoneNumber) {
        return CellPhoneNumber.startsWith("+27") && CellPhoneNumber.length() == 12 && CellPhoneNumber.matches(".*[0-9].*");
    }

    public String registerUser(String username, String password) {
        if (!CheckUserName(username)) {
            return "Username is incorrectly formatted";
        } else if (!checkpasswordComplexity(password)) {
            return "Password is incorrectly formatted";
        } else {
            return "Username and password are correctly formatted";
        }
    }

    public boolean loginUser(String username, String Password, String verifyUsername, String verifypassword) {
        return verifyUsername.equals(username) && verifypassword.equals(Password);
    }

    public String returnloginstatus(String username, String Password, String verifyusername, String verifyPassword) {
        return loginUser(username, Password, verifyusername, verifyPassword) ? "successful login" : "failed login";
    }
}

