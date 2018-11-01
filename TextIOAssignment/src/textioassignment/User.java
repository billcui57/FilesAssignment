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

    public User(String newFirstName,String newLastName,String newUsername,String newPassword){
        this.setFirstName(newFirstName);
        this.setLastName(newLastName);
        this.setPassword(newPassword);
        this.setUserName(newUsername);   
    }
    
    public User(){
        
    }
    
    @Override
    public String toString(){
        return firstName+","+lastName+","+username+","+password;
    }
    
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
