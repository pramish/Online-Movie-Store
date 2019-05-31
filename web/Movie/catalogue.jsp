<%-- 
    Document   : catalogue
    Created on : 19/05/2019, 4:21:33 PM
    Author     : luckylau
--%>

<%@page import="MODEL.User"%>
<%@page import="MODEL.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<!DOCTYPE html>

<%
    User user = (User)session.getAttribute("user");
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
        <div class="container">
            <h1><a href="/">Online Movie Store</a></h1>
            <h2>Movie Catalogue</h2>
            <hr />
            <div class="row">
                <div class="col-sm-4">
                    <a href="/" class="btn btn-default btn-block">Home</a>
                </div>
                <%if(user.isRegistered()){%>
                    <div class="col-sm-4">
                        <a class="btn btn-primary btn-block" href="/movie/add">Add Movie</a>
                    </div>
                <%}%>
            </div>
            <br />
            <form>
                <div class="row">
                    <div class="col-sm-3">
                        <input class="form-control" type="search" name="search" placeholder="Search title or genre" />        
                    </div>
                    <div class="col-sm-2">
                        <input type="submit" class="btn btn-primary btn-block" value="Search" />
                    </div>
                </div>
            </form>
            <hr />
            <table class="table table-condensed table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th> ID </th>
                        <th> Title </th>
                        <th> Genre </th>
                        <th> Price </th>
                        <th> Stock </th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>            
                <%for (Movie m : movielist){%>
                    <tr>
                       <td><%=m.getID()%></td>
                       <td><%=m.getTitle()%></td>
                       <td><%=m.getGenre()%></td>
                       <td><%=m.getPrice()%></td>
                       <td><%=m.getStock()%></td>
                       <td> 
                            <%if(user.isRegistered()){%>
                                <a href="/movie/update?id=<%=m.getID()%>" class="btn btn-xs btn-primary">Update Movie</a>
                            <%}%>
                           
                            <%if(m.getStock()>0){%>
                                <a href="/order/create?id=<%=m.getID()%>" class="btn btn-xs btn-primary">Order Movie</a>
                            <%}%>
                       </td>
                   </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    

  





