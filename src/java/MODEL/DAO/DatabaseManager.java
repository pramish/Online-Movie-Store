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

    public User findUser(String name, String phoneNumber) throws SQLException {
        String fetch = "select * from \"USER\" where name = '" + name + "' and phonenumber = '" + phoneNumber + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userName = rs.getString(3);
            String phonenumber = rs.getString(5);
            if (userName.equals(name) && phonenumber.equals(phoneNumber)) {
                String userID = rs.getString(1);
                String userStatus = rs.getString(6);
                String userEmail = rs.getString(2);
                String userPass = rs.getString(4);
                return new User(userID, userEmail, userName, userPass, phonenumber, userStatus);
            }
        }
        return null;
    }

    public User getUser(String name, String phoneNumber) throws SQLException {
        String fetch = "select * from \"USER\" where name = '" + name + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userName = rs.getString(3);
            String userPhone = rs.getString(5);
            if (userName.equals(name) && userPhone.equals(phoneNumber)) {
                String userPass = rs.getString(4);
                String userEmail = rs.getString(2);
                String userID = rs.getString(1);
                String userStatus = rs.getString(6);
                return new User(userID, userEmail, userName, userPass, userPhone, userStatus);
            }
        }
        return null;
    }

    public User getUserByID(String ID) throws SQLException {
        String fetch = "select * from \"USER\" where ID = '" + ID + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userID = rs.getString(1);
            if(userID.equals(ID)) {
                String userPass = rs.getString(4);
                String userEmail = rs.getString(2);
                String userName = rs.getString(3);
                String userPhone = rs.getString(5);
                String userStatus = rs.getString(6);
                return new User(userID, userEmail, userName, userPass, userPhone, userStatus);
            }
        }
        return null;
    }

    public boolean checkUser(String email, String password) throws SQLException {
//        String query = "Select * from adminlogin Where Username='" + username + "' and Password='" + password + "'";
        String fetch = "select * from \"USER\" where email='" + email + "'and password='" + password + "'";
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
        String fetch = "select email from \"USER\" where email='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            if (email.equals(rs.getString(1))) {
                return true;
            }
        }
        return false;
    }

    public void addUser(String ID, String email, String name, String password, String phoneNumber, String status) throws SQLException {

        st.executeUpdate("INSERT INTO \"USER\" VALUES('" + ID + "','" + email + "','" + name + "','" + password + "','" + phoneNumber + "','" + status + "')");
    }

    public void deleteUser(String email) throws SQLException {
        //code for delete-operation
        st.executeUpdate("delete from \"USER\" where email = '" + email + "'");
    }

    public void setUserStatus() {

    }

    public void updateUser(String ID, String email, String name, String password, String phoneNumber, String status) throws SQLException {
        st.executeUpdate("update \"USER\" set email = '" + email + "', name= '" + name + "', password = '" + password + "', phoneNumber = '" + phoneNumber + "', status = '" + status + "' where ID='" + ID + "'");
    }
}
