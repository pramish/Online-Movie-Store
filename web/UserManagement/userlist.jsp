<%-- 
    Document   : listUser
    Created on : 23/05/2019, 9:47:14 PM
    Author     : pramishluitel
--%>


<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 

<%List<User> list = (ArrayList) session.getAttribute("userList");%>

<!DOCTYPE html>
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
            <h2>User Management</h2>
            <hr />
            <div class="row">
                <div class="col-sm-4">
                    <a href="/" class="btn btn-default btn-block">Home</a>
                </div>
                <div class="col-sm-4">
                    <a class="btn btn-primary btn-block" href="/createUser">Create User</a>
                </div>
            </div>
            <br />
            <form>
                <div class="row">
                    <div class="col-sm-3">
                        <input class="form-control" type="search" name="search" placeholder="Search name or phone here">        
                    </div>
                    <div class="col-sm-2">
                        <input type="submit" class="btn btn-primary btn-block" value="Search">        
                    </div>
                </div>
            </form>
            <hr />
            <table class="table table-condensed table-bordered table-striped table-hover">
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
                        <td><a href='/updateUser?id=<%=user.getID()%>' class="btn btn-primary btn-xs">Edit</a></td>
                    </tr>
                    <%}%>
                 </tbody>
                </table>
        </div>
        
        
    </body>
</html>