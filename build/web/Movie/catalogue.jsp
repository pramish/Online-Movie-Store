<%-- 
    Document   : catalogue
    Created on : 19/05/2019, 4:21:33 PM
    Author     : luckylau
--%>

<%@page contentType="text/html" import="java.util.*" import="MODEL.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    List<Movie> movielist = (ArrayList)session.getAttribute("movielist");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie List</title>
    </head>
    <body>
        <h1>Catalogue</h1>
        
       

 
 
<table align="center" cellpadding="5" cellspacing="5" border="1">
    <%
            for (Movie m : movielist){
                
                %>
                <tr>
                    <td><%=m.getID()%></td>
                    <td><%=m.getTitle()%></td>
                    <td><%=m.getGenre()%></td>
                    <td><%=m.getPrice()%></td>
                    <td><%=m.getStock()%></td>
                    </tr>
                <%

            }
    %>

</table>

<button class="button" type="button" onclick="location.href = '/movie/add'" > Add movie </button>

