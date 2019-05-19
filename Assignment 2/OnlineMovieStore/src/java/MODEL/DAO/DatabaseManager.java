/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.DAO;

import MODEL.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pramishluitel
 */
public class DatabaseManager {

    private Statement st;

    public DatabaseManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    public User findUser(String email, String password) throws SQLException {
        String fetch = "select * from STUDENTS where email = '" + email + "' and password = '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userEmail = rs.getString(1);
            String userPass = rs.getString(3);
            if (userEmail.equals(email) && userPass.equals(password)) {
                String userName = rs.getString(2);
                String phoneNumber = rs.getString(4);
                return new User(userEmail, userName, userPass, phoneNumber);
            }
        }
        return null;
    }

    public boolean checkUser(String email, String password) throws SQLException {
        String fetch = "select * from STUDENTS where email='" + email + "'and password='" + password;
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

    public void addUser(String email, String name, String password, String phoneNumber) throws SQLException {
        st.executeUpdate("INSERT INTO USERS VALUES('"+email+"','"+name+"','"+password+"','"+phoneNumber+"')");
    }
}
