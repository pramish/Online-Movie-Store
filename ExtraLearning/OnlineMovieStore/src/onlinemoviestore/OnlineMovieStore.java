package onlinemoviestore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OnlineMovieStore {

    private static final String USERNAME = "pramish"; //Username of a user
    private static final String PASSWORD = "pramish"; //Password of a user
    private static final String CONN_STRING = "jdbc:mysql://localhost:8889/explorecalifornia?serverTimezone=EST5EDT"; // The link to the MYSQL server database

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = null; // creating a instance of a Connection class. It is used to connect to specific database.
        Statement stmt = null; // creating a instance of a Statement class. It is used to execute the static SQL queries.
        ResultSet r = null; // Creating a table of result data. This will execute when statements execute.
        try{
            con = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD); // Drivermanager helps in connecting to a database and it gets the connection using url, username and password.
              
            System.out.println("Hello I am connected"); // If we successfully connected to the database, this statement will execute.
            Statement s = (Statement) con.createStatement(); // This will initialize on creating a SQL statement for the database.
//            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            r = stmt.executeQuery("select * from states"); // This will execute the query for the database.
            r.last(); // Moves the cursor at the last row of the table
            System.out.println("Number of rows: "+r.getRow()); // This will get the number of row for the particular table we executed above.
           
        }
        catch (SQLException e){
            System.err.print(e);
            System.out.println("I am not running");
        }
        finally{ 
            //As we implemented the functions for the database, we have to close it in a reverse way to avoid security risks.
            if(r!=null){
                r.close();
            }
            if(stmt!=null){
                stmt.close();
            }
            if(con!=null){
                con.close();
            }
        }
    }

}
