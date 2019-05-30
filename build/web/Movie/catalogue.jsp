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
        <title>OMS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Catalogue</h1>
        
       

 <button class="button" type="button" onclick="location.href = '/movie/add'" > Add movie </button>
 
<table align="center" cellpadding="5" cellspacing="5" border="1">
            <tr>
             
                <th> ID </th>
                <th> Title </th>
                <th> Genre </th>
                <th> Price </th>
                <th> Stock </th>
                <th> Delete or Update </th>
           
            </tr>
                
    <%
            for (Movie m : movielist){
                
                %>
                 <tr>
                    <td><%=m.getID()%></td>
                    <td><%=m.getTitle()%></td>
                    <td><%=m.getGenre()%></td>
                    <td><%=m.getPrice()%></td>
                    <td><%=m.getStock()%></td>
                    <td> 
                            <button class="button" type="button" onclick="location.href = '/movie/delete'" > Delete movie </button>
                            <button class="button" type="button" onclick="location.href = '/movie/update'"> Update movie </button>                 
                            <a href="/order/create?movieId=<%=m.getID()%>">Buy Movie</a>
                    </td>
                </tr>
                <%

            }
    %>

</table>




