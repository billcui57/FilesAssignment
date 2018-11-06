/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textioassignment;

/**
 *
 * @author 348848128
 */
public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    /**
     * Constructor for user
     * @param newFirstName first name
     * @param newLastName last name
     * @param newUsername username
     * @param newPassword password
     */
    public User(String newFirstName,String newLastName,String newUsername,String newPassword){
        this.setFirstName(newFirstName);
        this.setLastName(newLastName);
        this.setPassword(newPassword);
        this.setUserName(newUsername);   
    }
    
    public User(){
        
    }
    
    @Override
    
    /**
     * returns user class in string format
     * @return returns user class in string format
     */
    public String toString(){
        //returns string format of instance
        return firstName+","+lastName+","+username+","+password;
    }
    
    /**
     * sets username
     * @param newUsername new username to set to
     */
    public void setUserName(String newUsername) {
        //sets username
        username = newUsername;
    }

    /**
     * sets password
     * @param newPassword new password to set to
     */
    public void setPassword(String newPassword) {
        //sets password
        password = newPassword;
    }

    /**
     * sets first name
     * @param newFirstName first name to set to
     */
    public void setFirstName(String newFirstName) {
        //sets first name
        firstName = newFirstName;
    }

    /**
     * sets last name
     * @param newLastName last name to set to
     */
    public void setLastName(String newLastName) {
        //sets last name
        lastName= newLastName;
    }

    /**
     * getter for username
     * @return username
     */
    public String getUserName() {
        //getter for username
        return username;
    }

    /**
     * getter for password
     * @return password
     */
    public String getPassword() {
        //getter for password
        return password;
    }

    /**
     * getter for first name
     * @return first name
     */
    public String getFirstName() {
        //getter for first name
        return firstName;
    }

    /**
     * getter for last name
     * @return last name
     */
    public String getLastName() {
        //getter for last name
        return lastName;
    }
    

}
