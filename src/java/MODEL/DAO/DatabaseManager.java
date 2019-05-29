package MODEL.DAO;

import MODEL.Movie;
import MODEL.User;
import MODEL.UserAccessLogs;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
       public User getUsers(String name, String phoneNumber) throws SQLException {
        String queryString = "select * from \"USER\" where name = '" + name + "' and phonenumber = '" + phoneNumber + "'";
        ResultSet rs = st.executeQuery(queryString);
        while (rs.next()) {
            if (name.equals(rs.getString("name"))&& phoneNumber.equals(rs.getString("phonenumber"))) {
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
    
    public List<UserAccessLogs> searchLogsByDate(String search) throws SQLException {
        
        String search2 = search;
        
        if (search2 == null) search2 = "";
        String queryString = "select * from \"USERACCESSLOG\" where upper(timeStamp) like upper('%"+search2+"%')";
        ResultSet rs = st.executeQuery(queryString);
        
        List<UserAccessLogs> list = new ArrayList<>();
        while (rs.next()) {
//            if(timeStamp)
        list.add(new UserAccessLogs(rs.getString("id"),
                rs.getString("userID"),
                rs.getString("accessType"),
                rs.getString("timeSTamp")));
    }
    return list;
                
                                                            
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
    
    
        public Movie findMovie(String ID, String title) throws SQLException {
       ResultSet rs = st.executeQuery("SELECT * FROM MOVIE");
        while(rs.next())
        {
            
            if (title.equals(rs.getString("title")))
                return new Movie(rs.getString("ID"), rs.getString("title"), rs.getString("genre"), new java.math.BigDecimal(rs.getString("price")),
               Integer.parseInt(rs.getString("stock")));
        
        }
        
        return null;
    }
    
    public Movie displayMovie() throws SQLException{
        ResultSet rs = st.executeQuery("SELECT * FROM MOVIE");
            while(rs.next())
            {
                return new Movie(rs.getString("ID"), rs.getString("title"), rs.getString("genre"), new java.math.BigDecimal(rs.getString("price")),
                Integer.parseInt(rs.getString("stock")));
            
            }
        return null;
    }
    
    public List<UserAccessLogs> searchLog(String userID) throws SQLException {
    ResultSet rs = st.executeQuery("SELECT * FROM USERACCESSLOG where userID='" + userID + "'");
      List<UserAccessLogs> logList = new ArrayList<>();
        while(rs.next())
        {
            
            //if ((title != null && title.equals(rs.getString("title"))) || (genre != null && genre.equals(rs.getString("genre"))))
                logList.add(new UserAccessLogs(rs.getString("id"), rs.getString("userID"), rs.getString("accessType"), rs.getString("timeStamp")));
        
        }
        
        return logList;
    }
    
    
    public UserAccessLogs getUserDate(String date) throws SQLException{
        
        ResultSet rs = st.executeQuery("SELECT * FROM USERACCESSLOG where timestamp='" + date + "'");
        while(rs.next()){
            return new UserAccessLogs(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
        }
        return null;
    }

    public List<Movie> searchMovie(String title, String genre) throws SQLException {
      ResultSet rs = st.executeQuery("SELECT * FROM MOVIE");
      List<Movie> movielist = new ArrayList<>();
        while(rs.next())
        {
            
            //if ((title != null && title.equals(rs.getString("title"))) || (genre != null && genre.equals(rs.getString("genre"))))
                movielist.add(new Movie(rs.getString("ID"), rs.getString("title"), rs.getString("genre"), new java.math.BigDecimal(rs.getString("price")), Integer.parseInt(rs.getString("stock"))));
        
        }
        
        return movielist;
    }
    //add a movie details in the database
    public void addMovie(String ID, String title, String genre, BigDecimal price,  int stock) throws SQLException {
        st.executeUpdate("INSERT INTO MOVIE (ID, title, genre, price, stock) values ('"+ID+"','"+title+"','"+genre+"',"+price+","+stock+")");
    }
    
    //update a movie details in the database
    public void updateMovie(String ID, String title, String genre, BigDecimal price, int stock) throws SQLException {
        st.executeUpdate("UPDATE MOVIE SET ID = '"+ID+"', title = '"+title+"', genre = '"+genre+"', price = "+price+", stock = "+stock+" WHERE ID = '"+ID+"'");
    }
    
    //delete a moive from the database
    public void deleteMovie(String ID) throws SQLException{
        st.executeUpdate("DELETE FROM MOVIE WHERE ID='"+ID+"'");
    }
    
    
    
}



    //</editor-fold>