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
public boolean CheckUserName (String username) {
    if (username.contains("_")&& username.length() == 5) {
        return true ; 
    }
    else
    {
        return false;    
    }
    }

      public boolean checkpasswordComplexity ( String password) {
          if (password.length() <=8 &&
                  password.matches(".*[A-Z].*") &&
                  password.matches(".*[a-z].*") &&
                  password.matches(".*[0-12].*") &&
                  password.matches(".*[!@#$%^&*()_+<>:{}].*"))
              return true;
          else {
              return false ;
          }
          
          }
      public boolean checkCellPhoneNumber ( String CellPhoneNumber) {
          
              if (CellPhoneNumber.startsWith("+27") && CellPhoneNumber.length() == 12 && CellPhoneNumber.matches(".*[0-9].*")){
                  return true;
              }else{
                  return false;
              }
              
            
     }
      public String registerUser(String username,String password){
          String message;
          if(CheckUserName(username)==true){
              message=("username is correctly formated");
              return message;
          
                  
          }else if(checkpasswordComplexity(password)==false){
              message =("password is incorrectly formatted");
              return message;
          }else{
              message=("username and password are correctly formatted");
              return message;
          }
      }
     public boolean loginUser(String username,String Password, String verifyUsername,String verifypassword){
         
         return verifyUsername.equals(username)&& verifypassword.equals(Password);
          }
     public String returnloginstatus(String username,String Password,String verifyusername,String verifyPassword){
         String message;
         if (loginUser(username,Password,verifyusername,verifyPassword)==true){
             message= "successful login";
             return message;
         }else{
             message="failed login";
             return message;
         }
      }
}
