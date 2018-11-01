/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textioassignment;

import java.io.*;
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
    public static void main(String[] args) {
        // TODO code application logic here
        File file = new File("info.txt");
        Scanner input = new Scanner(System.in);
        PrintWriter writer=null;
        Scanner reader=null;
        try {
           writer = new PrintWriter(new FileWriter(file, true));
            reader = new Scanner(file);
        } catch (IOException ex) {
            Logger.getLogger(TextIOAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean loop=false;
        do {
            System.out.println("Hello, are you a new user(N) or an old existing user(O)?");
            switch (input.next()) {
                case "N":
                    System.out.println("What is your first name?");
                    String firstName=input.next();
                    System.out.println("What is your last name?");
                    String lastName=input.next();
                    System.out.println("Please enter a new username");
                    String username=input.next();
                    System.out.println("Please enter a new password");
                    String password=input.next();
                    User user = new User(firstName,lastName,username,password);
                    writer.write(user.toString() + "\n");
                    break;
                case "O":
                    
                    break;
                default:
                    System.out.println("Enter valid option please");
                    loop=true;
            }
        }while(loop);
        
        writer.close();
        reader.close();
    }

    
    public static boolean goodPassword(String testPassword) throws FileNotFoundException{
        File dictionary = new File ("dictbadpass.txt");
        Scanner reader = new Scanner(dictionary);
        ArrayList badPasswords  = new ArrayList ();
        reader.useDelimiter("\n");
        while(reader.hasNext()){
            badPasswords.add(reader.next());
        }
        
    }
}
