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

    public LoginService(String fileName) {
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
    
    public boolean userExists(String testUsername){
        for(int i=0;i<this.user.size();i++){
            if(testUsername.equals(user.get(i).getUserName())){
            return true;
            }
        }
        return false;
    }
    
    public int getIndexOfUsername(String testUsername) throws LoginServiceException{
        for(int i=0;i<user.size();i++){
            if(user.get(i).getUserName().equals(testUsername)){
                return i;
            }
        }
        
        throw new LoginServiceException("No such user");
    }
    
    public void updateFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file));
            for(int i=0;i<user.size();i++){
                writer.println(user.get(i).toString());
            }   writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TextIOAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }
    
    
     public boolean isGoodPassword(String testPassword) throws FileNotFoundException {
        File dictionary = new File("dictbadpass.txt");
        Scanner reader = new Scanner(dictionary);
        ArrayList<String> badPasswords = new ArrayList<String>();

        while (reader.hasNext()) {
            badPasswords.add(reader.next());
        }
        
        reader.close();

        boolean goodPassword = true;

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
     
     public String encrypt(String raw)  {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(raw.getBytes());
            return new sun.misc.BASE64Encoder().encode(md.digest());
        } catch (NoSuchAlgorithmException e) {
            //_log.error("Failed to encrypt password.", e);
        }
        return "";
    }
     
     
     public boolean isCorrectPassword(User testUser, String testPassword){
         if(testUser.getPassword().equals(this.encrypt(testPassword))){
             return true;
         }
         return false;
     }
     
     public User getUser(int index){
         return this.user.get(index);
     }
    

}
