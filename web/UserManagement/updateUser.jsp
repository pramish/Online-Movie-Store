<%-- 
    Document   : updateUser
    Created on : 08/05/2019, 5:59:14 PM
    Author     : pramishluitel
--%>

<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="/Header.html" %>
    </head>
    <%
        String failure = (String) request.getParameter("failure");
        String success = (String) request.getParameter("success");
        String success1 = (String) request.getParameter("success1");
    %>
    <%if (success != null) { %>
    <div class="alert alert-success">
        <%out.print(success);%> 
    </div>
    <%  }
    %>

    <%if (failure != null) { %>
    <div class="alert alert-success">
        <%out.print(failure);%> 
    </div>
    <%  }
    %>
    <%if (success1 != null) { %>
    <div class="alert alert-success">
        <%out.print(success1);%> 
    </div>
    <%  }
    %>
    <body>
        
        <%@include file="/heading.jsp" %>
        <h1>Update User details.</h1>
        <br>
        <div class="container-fluid">
            <div class="col-sm-12 text-center">
       <form class="form-inline" action="/UserManagement/updateUserController.jsp" method="post">
            <input class="form-control mr-sm-2" type="search" name="ID" placeholder="enter user ID" cc aria-label="Search">
            <button class="btn btn-warning" name="search">Search</button>
            <br><br>
        </form>
            </div>
        </div>
        <form action="/updateUser" method="post">
            <% User user = (User) session.getAttribute("user");
                if (user != null) {%>
            <div class="container">
                <h1 class="text-center">Update User</h1>
                <hr>
                <div class="row">
                    <div class="col-md-6 col-md-push-3">
                        <div class="row">
                            <div class="col-sm-6 col-sm-push-3">
                                <div class="form-group">
                                    <label>User ID</label>
                                    <input class="form-control" type="text" name="ID" value="<%=user.getID()%>" readonly>
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input class="form-control" type="email" name="email" value="<%=user.getEmail()%>" required>
                                </div>
                                <div class="form-group">
                                    <label>Full Name</label>
                                    <input class="form-control" type="text" name="name" value="<%=user.getName()%>">
                                </div>
                                <div class="form-group">
                                    <label>Phone No.</label>
                                    <input class="form-control" type="text" name="phone" value="<%=user.getPhoneNumber()%>">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input class="form-control" type="password" name="password" value="<%=user.getPassword()%>" required>
                                </div>
                                <div class="form-group">
                                    <label>Status</label>
                                    <input class="form-control" type="text" value="<%=user.getStatus()%>" readonly>
                                </div>
                                <div class="form-group">
                                    <input  type="radio" name="status" value="Active" > Active
                                    <input  type="radio" name="status" value="Deactive" checked > Deactive
                                </div>
                                <hr/>
                                <a href="/index.jsp" class="btn btn-default">Cancel</a>
                                <input type="submit" class="btn btn-success pull-right" name="save" value="Save">
                            </div>
                        </div>
                    </div>
                </div>
                <hr/>
            </div>
            <%}%>
    </body>
</html>
