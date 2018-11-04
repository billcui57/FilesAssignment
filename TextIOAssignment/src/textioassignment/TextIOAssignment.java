/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textioassignment;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 348848128
 */
public class TextIOAssignment {

    /**
     * @param args the command line arguments
     */
    public  void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        File file = new File("info.txt");
        Scanner input = new Scanner(System.in);
        PrintWriter writer = null;
        Scanner reader = null;
        try {
            writer = new PrintWriter(new FileWriter(file, true));
            reader = new Scanner(file);
        } catch (IOException ex) {
            Logger.getLogger(TextIOAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<User> user = new ArrayList<User>();
        ArrayList<String> lines = new ArrayList<String>();
        
        while (reader.hasNext()) {
            lines.add(reader.next());
        }
        reader.close();

        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i).split(",");
            user.add(new User(line[0], line[1], line[2], line[3]));
        }

        boolean loop = false;

        int incorrectTries = 0;

        do {
            System.out.println("Hello, are you a new user(N) or an old existing user(O)?");
            switch (input.next()) {
                case "N":
                    System.out.println("What is your first name?");
                    String firstName = input.next();
                    System.out.println("What is your last name?");
                    String lastName = input.next();
                    System.out.println("Please enter a new username");
                    String username = input.next();
                    String password = null;
                    System.out.println("Please enter a new password");
                    password=newPassword();
                    
                    User newUser = new User(firstName, lastName, username, password);
                    user.add(newUser);
                   
                    updateFile(user,file);
                    loop = false;
                    break;
                case "O":
                    System.out.println("Please enter username");
                    String loginUserName = input.next();
                    int referredIndex = 0;
                    boolean userExists = false;
                    for (int i = 0; i < user.size(); i++) {
                        if (loginUserName.equals(user.get(i).getUserName())) {
                            referredIndex = i;
                            userExists = true;
                            break;
                        }
                    }

                    if (!userExists) {
                        System.out.println("No such user, try again");
                        loop = true;
                        continue;
                    }

                    System.out.println("Please enter password," + user.get(referredIndex).getFirstName());
                    String loginPassword = input.next();
                    if (encrypt(loginPassword).equals(user.get(referredIndex).getPassword())) {
                        System.out.println("Welcome, " + user.get(referredIndex).getFirstName());
                    } else {
                        System.out.println("Incorrect Password, try again");
                        incorrectTries++;
                        if (incorrectTries < 2) {
                            loop = true;
                            continue;
                        } else {
                            System.out.println("It seems that you've forgotten your password, would you like to reset it? (Y/N)");
                            switch(input.next()){
                                case "Y":
                                    System.out.println("Enter your new password!");
                                    String newPassword = newPassword();
                                    user.get(referredIndex).setPassword(newPassword);
                                    updateFile(user,file);
                                    break;
                                case "N":
                                    System.out.println("K bye then");
                                    break;
                                default:
                                    System.out.println("Bad input, try again");
                                    loop=true;
                                    continue;
                            }
                    
                    
                        }
                        
                    }
                    
                    loop = false;
                    break;
                default:
                    System.out.println("Enter valid option please");
                    loop = true;
            }
        } while (loop);

       
        
    }

    public static void updateFile(ArrayList<User> members,File file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file));
            for(int i=0;i<members.size();i++){
                writer.println(members.get(i).toString());
            }   writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TextIOAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }
    
    
    
    
    
    public static boolean isGoodPassword(String testPassword) throws FileNotFoundException {
        File dictionary = new File("dictbadpass.txt");
        Scanner reader = new Scanner(dictionary);
        ArrayList<String> badPasswords = new ArrayList<String>();

        while (reader.hasNext()) {
            badPasswords.add(reader.next());
        }

        boolean goodPassword = true;

        for (int i = 0; i < badPasswords.size(); i++) {

            if (testPassword.equalsIgnoreCase(badPasswords.get(i).toString())) {

                goodPassword = false;

            }
        }
        return goodPassword;
    }

    public static String newPassword() throws FileNotFoundException {
        Scanner type = new Scanner(System.in);
        String password = null;
        do {
            password = type.next();
            if (!isGoodPassword(password)) {
                System.out.println("Please enter a better password");
            }
        } while (!isGoodPassword(password));
        
        password=encrypt(password);
        return password;

    }
    
    
    public static String encrypt(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(raw.getBytes());
            return new sun.misc.BASE64Encoder().encode(md.digest());
        } catch (NoSuchAlgorithmException e) {
            //_log.error("Failed to encrypt password.", e);
        }
        return "";
    }

}
