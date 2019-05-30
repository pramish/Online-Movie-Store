<%-- 
    Document   : updateMovie
    Created on : 27/05/2019, 10:03:22 AM
    Author     : luckylau
--%>

<%@page import="MODEL.Movie"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="MODEL.DAO.*"%>
<%@page import="MODEL.controller.*"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" import="java.util.*" import="MODEL.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Movie</title>
    </head>
    
    <%
       
        Movie movie = (Movie) session.getAttribute("movie");

       
    %> 
    
    
         <h1>Update Movie</h1>        
        <form action="/movie/update" method="post">
            <table>
                <tr><td>Movie ID</td><td><%= movie.getID()%></td></tr>
                <tr><td>Movie Title</td><td><input type="text" value="<%= movie.getTitle()%>" name="title"></td></tr>
                <tr><td>Movie Genre</td><td><input type="text" value="<%= movie.getGenre()%>" name="genre"></td></tr> 
                <tr><td>Movie Price</td><td><input type="text" value="<%= movie.getPrice()%>" name="title"></td></tr>
                <tr><td>Movie Stock</td><td><input type="text" value="<%= movie.getStock()%>" name="stock"></td></tr> 
                
                 
                
                 <tr>
                    <td><input class="button" type="submit" value="Update" name="updated"> 
                        &emsp; 
                        <button class="button" type="button" onclick="location.href = '/movie/list'" > Movie List </button>
                    </td>
                </tr>
            </table>
        </form>
        
       
        
        
        
        
             <jsp:include page="/ConnServlet" flush="true" />
    
</html>
