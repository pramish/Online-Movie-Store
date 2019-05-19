
/*This stand-alone application test the connection to the db once setup in DB class
 *It performs an add-function of a Student to the database
 *To use this app, you should complete the addStudent method in DBManager class
 *
 *
 *@author George
**/
package MODEL.controller;

import MODEL.DAO.DatabaseConnector;
import MODEL.DAO.DatabaseManager;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class TestDB {
    private static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            DatabaseConnector connector = new DatabaseConnector(); //Create a connection and initialize DB conn-field
            Connection conn = connector.openConnection(); //Get the protected connection instance from DB superclass to share for the application classes
            DatabaseManager db = new DatabaseManager(conn); //DBManger instance provide users with access to CRUD operations
            
            int key = (new Random()).nextInt(999999);
            String ID = "" + key; 
            System.out.print("Student email: ");
            String email = in.nextLine();
            System.out.print("Student name: ");
            String name = in.nextLine();
            System.out.print("Student password: ");
            String password = in.nextLine();
            System.out.print("Student phoneNumber: ");
            String phoneNumber = in.nextLine();
            db.addUser(email, name, password,phoneNumber); //This method must be completed in DBManager class
            System.out.println("Student is added to the database.");
            connector.closeConnection(); //Professional practice is to close connection to database once operations are finalized
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
