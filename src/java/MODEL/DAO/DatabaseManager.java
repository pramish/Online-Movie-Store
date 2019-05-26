/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.DAO;

import MODEL.Movie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pramishluitel
 */
public class DatabaseManager {

    private Statement st;
    
    

    public DatabaseManager(Connection conn) throws SQLException {
        st = conn.createStatement();
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
