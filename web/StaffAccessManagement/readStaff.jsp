<%-- 
    Document   : readStaff
    Created on : 31/05/2019, 12:06:59 AM
    Author     : pramishluitel
--%>

<%@page import="MODEL.Staff"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%
    List<Staff> list = (ArrayList) session.getAttribute("staffList");
%>
<html>
    <head>
        <%@include file="/Header.html" %>
    </head>
    <body>
        <h1>Staff Menu</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <a class="navbar-brand" href="/index.jsp">Home</a>
            <a class="navbar-brand" href="/createStaff">Create Staff</a>
            <a class="navbar-brand" href="/readStaff">Read Staff</a>
            <a class="navbar-brand" href="/updateStaff">Update Staff</a>
            <a class="navbar-brand" href="/deleteStaff">Delete Staff</a>

            <br><br><br>
            <div style="text-align: right;">
                <form class="form-inline my-2 my-lg-0" >
                    <input class="form-control mr-sm-2" type="text" name="search" placeholder="enter name or position">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav> 
        <div class='container-fluid'>
            <div align="center">
                <table class="table table-borderless table-dark">
                    <caption><h2>Users Record</h2></caption>
                    <tr class="table-success">
                        <th>User ID</th>
                        <th>Email</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Address</th>
                        <th></th>
                    </tr>
                    <%
                        for (Staff user : list) {
                    %>
                    <tr class="table-success">
                        <td> <%=user.getID()%> </td>
                        <td> <%=user.getEmail()%> </td>
                        <td> <%=user.getName()%> </td>
                        <td> <%=user.getPosition()%> </td>
                        <td> <%=user.getAddress()%> </td>
                        <td><a href='/updateStaff?staffID=<%=user.getID()%>'>Edit</a></td>
                    </tr>
                    <%}%>
                </table>
            </div>  
        </div>
    </body>
</html>
