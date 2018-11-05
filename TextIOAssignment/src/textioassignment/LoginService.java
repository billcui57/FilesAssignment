/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textioassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import textioassignment.LoginServiceException;


/**
 *
 * @author billc
 */
public class LoginService {

    ArrayList<User> user = new ArrayList<User>();
    File file;

    /**
     * Constructor
     * Creates a new login service instance based off specified filename
     * @param fileName name of file
     */
    public LoginService(String fileName) {
        //reads from referred file and converts it to an arraylist of users
        this.file = new File(fileName);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            Scanner reader = new Scanner(file);
            ArrayList<String> lines = new ArrayList<String>();
            while (reader.hasNext()) {
                lines.add(reader.next());
            }
            reader.close();

            for (int i = 0; i < lines.size(); i++) {
                String[] line = lines.get(i).split(",");
                user.add(new User(line[0], line[1], line[2], line[3]));
            }

        } catch (IOException ex) {
            Logger.getLogger(TextIOAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Returns a boolean for whether if that username exists already
     * @param testUsername username to be tested
     * @return whether or not that username exists already
     */
    public boolean userExists(String testUsername){
        //compares with each username in the file
        for(int i=0;i<this.user.size();i++){
            if(testUsername.equals(user.get(i).getUserName())){
            return true;
            }
        }
        return false;
    }
    
    /**
     * Gets the array index of that username
     * @param testUsername username to get index of
     * @return index of that username
     * @throws LoginServiceException no such username
     */
    public int getIndexOfUsername(String testUsername) throws LoginServiceException{
        //if username exists in arraylist then return that index
        for(int i=0;i<user.size();i++){
            if(user.get(i).getUserName().equals(testUsername)){
                return i;
            }
        }
        
        throw new LoginServiceException("No such user");
    }
    
    /**
     * Updates the file by clearing it and replacing it with the updated arraylist
     */
    public void updateFile() {
        PrintWriter writer = null;
        try {
            //clears the file
            writer = new PrintWriter(new FileWriter(file));
            //prints the arraylist into the file and closes it
            for(int i=0;i<user.size();i++){
                writer.println(user.get(i).toString());
            }   writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TextIOAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }
    
    /**
     * Returns boolean of whether or not that password is good
     * @param testPassword password to be tested for its robustness
     * @return boolean of whether or not that password is good
     * @throws FileNotFoundException no file to refer to
     */
     public boolean isGoodPassword(String testPassword) throws FileNotFoundException {
        File dictionary = new File("dictbadpass.txt");
        Scanner reader = new Scanner(dictionary);
        
        //reads in arraylist of badpasswords
        ArrayList<String> badPasswords = new ArrayList<String>();

        while (reader.hasNext()) {
            badPasswords.add(reader.next());
        }
        
        reader.close();

        boolean goodPassword = true;
        //tests the password with all bad passwords
        for (int i = 0; i < badPasswords.size(); i++) {

            if (testPassword.equalsIgnoreCase(badPasswords.get(i).toString())) {

                goodPassword = false;

            }
        }
        return goodPassword;
    }
     
//     public String newPassword() throws FileNotFoundException, LoginServiceException{
//        Scanner type = new Scanner(System.in);
//        String password = null;
//        
//            password = type.next();
//            if (!isGoodPassword(password)) {
//                throw new LoginServiceException ("Bad Password");
//            }
//        
//        
//        password=encrypt(password);
//        return password;
//
//    }
     
     /**
      * Encrypts the password with SHA-256
      * @param raw raw unencrypted password
      * @return encrypted password
      */
     public String encrypt(String raw)  {
        try {
            //encodes the password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(raw.getBytes());
            return new sun.misc.BASE64Encoder().encode(md.digest());
        } catch (NoSuchAlgorithmException e) {
            //_log.error("Failed to encrypt password.", e);
        }
        return "";
    }
     
     /**
      * Checks if the password is correct and returns boolean
      * @param testUser username to be tested
      * @param testPassword password of that username to be tested
      * @return whether or not that password is correct
      */
     public boolean isCorrectPassword(User testUser, String testPassword){
         //if password is equal to the correct password then return true
         if(testUser.getPassword().equals(this.encrypt(testPassword))){
             return true;
         }
         return false;
     }
     
     /**
      * Returns the user from referred index
      * @param index index of user
      * @return user
      */
     public User getUser(int index){
         return this.user.get(index);
     }
    

}
