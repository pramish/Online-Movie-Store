package MODEL.DAO;

import MODEL.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author pramishluitel
 */
public class DatabaseManager {

    private Statement st;

    public DatabaseManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
    User user = new User();

    public User findUser(String email, String phoneNumber) throws SQLException {
        String fetch = "select * from users where email = '" + email + "' and phonenumber = '" + phoneNumber + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userEmail = rs.getString(1);
            String phonenumber = rs.getString(4);
            if (userEmail.equals(email) && phonenumber.equals(phoneNumber)) {
                String userName = rs.getString(2);
                String userPass = rs.getString(3);
                return new User(userEmail, userName, userPass, phonenumber);
            }
        }
        return null;
    }

    public User getUser(String email) throws SQLException {
        String fetch = "select * from users where email = '" + email + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userEmail = rs.getString(1);
            String userPass = rs.getString(3);
            if (userEmail.equals(email)) {
                String userName = rs.getString(2);
                String phoneNumber = rs.getString(4);
                return new User(userEmail, userName, userPass, phoneNumber);
            }
        }
        return null;
    }
    
    
    
    
    

    public boolean checkUser(String email, String password) throws SQLException {
//        String query = "Select * from adminlogin Where Username='" + username + "' and Password='" + password + "'";
        String fetch = "select * from USERS where email='" + email + "'and password='" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userEmail = rs.getString(1);
            String userPassword = rs.getString(3);
            if (email.equals(userEmail) && password.equals(userPassword)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmail(String email) throws SQLException {
        String fetch = "select email from USERS where email='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            if (email.equals(rs.getString(1))) {
                return true;
            }
        }
        return false;
    }

    public void addUser(String email, String name, String password, String phoneNumber) throws SQLException {

        st.executeUpdate("INSERT INTO USERS VALUES('" + email + "','" + name + "','" + password + "','" + phoneNumber + "')");
    }

    public void deleteUser(String email) throws SQLException {
        //code for delete-operation
        st.executeUpdate("delete from users where email = '" + email + "'");
    }

    public void updateUser(String email, String name, String password, String phoneNumber) throws SQLException {
        st.executeUpdate("update users set email = '" + email + "', name = '" + name + "', password = '" + password + "' where email = '" + email + "'");
    }
}