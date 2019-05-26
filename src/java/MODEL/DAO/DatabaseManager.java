package MODEL.DAO;

import MODEL.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private Statement st;

    public DatabaseManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }
    
    // <editor-fold defaultstate="collapsed" desc="User functions. Click on the + sign on the left to edit the code.">
    
    public void addUser(String id, String email, String name, String password, String phoneNumber) throws SQLException {
        st.executeUpdate("insert into \"USER\" ( "
                + " id"
                + " ,email"
                + " ,name"
                + " ,password"
                + " ,phonenumber"
                + " ,status) "
                + " VALUES("
                + " '"  + id            + "'"
                + " ,'" + email         + "'"
                + " ,'" + name          + "'"
                + " ,'" + password      + "'"
                + " ,'" + phoneNumber   + "'"
                + " ,'ACTIVE')"
        );    
    }
    
    public User getUser(String id) throws SQLException {
        String queryString = "select * from \"USER\" where id = '" + id + "'";
        ResultSet rs = st.executeQuery(queryString);
        while (rs.next()) {
            if(id.equals(rs.getString("id")))
            {
                return new User(rs.getString("id"), 
                        rs.getString("email"), 
                        rs.getString("name"), 
                        rs.getString("password"), 
                        rs.getString("phonenumber"), 
                        rs.getString("status"));
            }
        }
        return null;
    }
    
    public void updateUser(String id, String email, String name, String password, String phoneNumber, String status) throws SQLException {
        st.executeUpdate("update \"USER\" set "
                + " email           = '" + email +          "'"
                + " ,name           = '" + name +           "'"
                + " ,password       = '" + password +       "'"
                + " ,phonenumber    = '" + phoneNumber +    "'"
                + " ,status         = '" + status +         "'"
                + " where id        = '" + id +             "'"
        );
    }

    public void deleteUser(String id) throws SQLException {
        st.executeUpdate("delete from \"USER\" where id = '" + id + "'");
    }
    
    public void addUserLogs(String ID, String userID, String accessType, String timeStamp) throws SQLException {
        st.executeUpdate("INSERT INTO \"USERACCESSLOG\" ( "
                + " id"
                + " ,userid"
                + " ,accesstype"
                + " ,timestamp) "
                + " VALUES ("
                + "'" + ID + "'"
                + ",'" + userID + "'"
                + ",'" + accessType + "'"
                + ",'" + timeStamp + "')");
        
            
    }
    
    public User findUserLogin(String email, String password) throws SQLException {
        String fetch = "select * from \"USER\" where email = '" + email + "' and password = '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userEmail = rs.getString("EMAIL");
            String userPassword = rs.getString("PASSWORD");
            if (userEmail.equals(email) && userPassword.equals(password)) {
                String userName = rs.getString("NAME");
                String phonenumber = rs.getString("PHONENUMBER");
                String status = rs.getString("STATUS");
                String id = rs.getString("ID");
               String name = rs.getString("NAME");
                
                return new User(id, email, name, password, phonenumber, status);
//                return new User(userEmail, userName, userPassword, phonenumber);
            }
        }
        return null;
    }
    
    public User findUser(String email, String phoneNumber) throws SQLException {
        String fetch = "select * from \"USER\" where email = '" + email + "' and phonenumber = '" + phoneNumber + "'";
        ResultSet rs = st.executeQuery(fetch);
        while (rs.next()) {
            String userEmail = rs.getString(1);
            String phonenumber = rs.getString(4);
            if (userEmail.equals(email) && phonenumber.equals(phoneNumber)) {
                String userName = rs.getString(2);
                String userPass = rs.getString(3);
                return new User(rs.getString("id"), 
                        rs.getString("email"), 
                        rs.getString("name"), 
                        rs.getString("password"), 
                        rs.getString("phonenumber"), 
                        rs.getString("status"));
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
    
}
    //</editor-fold>