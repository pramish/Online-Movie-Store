<%-- 
    Document   : listUser
    Created on : 23/05/2019, 9:47:14 PM
    Author     : pramishluitel
--%>


<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>


<%
    List<User> list = (ArrayList) session.getAttribute("userList");
    //List<User> list = new ArrayList<>();
%>
<html>
    <head>
        <%@include file="/Header.html" %>
    </head>

    <body>

        <h1>Admin Menu</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <a class="navbar-brand" href="/index.jsp">Home</a>
            <a class="navbar-brand" href="/createUser">Create Users</a>
            <a class="navbar-brand" href="/users">Read User</a>
            <a class="navbar-brand" href="/updateUser">Update Users</a>
            <a class="navbar-brand" href="/deleteUser">Delete Users</a>
            
            <br><br><br>
            <div style="text-align: right;">
                <form class="form-inline my-2 my-lg-0" >
                    <input class="form-control mr-sm-2" type="text" name="search" placeholder="enter name or phone">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav> 
        <div class='container-fluid'>
            <div class='table-responsive'>
            <caption><h2>Users Record</h2></caption>
             <table class="table table-condensed table-bordered table-striped">
                 <thead>
                     <tr class="table-success">
                        <th>User ID</th>
                        <th>Email</th>
                        <th>Name</th>
                        <th>Password</th>
                        <th>Phone Number</th>
                        <th>Status</th>
                        <th></th>

                    </tr>
                 </thead>
                 <tbody>
                     <%for (User user : list) {%>
                    <tr class="table-success">
                        <td> <%=user.getID()%> </td>
                        <td> <%=user.getEmail()%> </td>
                        <td> <%=user.getName()%> </td>
                        <td><%=user.getPassword()%></td>
                        <td><%=user.getPhoneNumber()%></td>
                        <td> <%=user.getStatus()%> </td>
                        <td><a href='/updateUser?id=<%=user.getID()%>'>Edit</a></td>
                    </tr>
                    <%}%>
                 </tbody>
                </table>
            </div>
        </div>
    </body>
</html>