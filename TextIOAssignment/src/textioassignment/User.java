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
        username = newUsername;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        lastName= newLastName;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    

}
