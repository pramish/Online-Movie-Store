<%-- 
    Document   : createUser
    Created on : 08/05/2019, 5:58:50 PM
    Author     : pramishluitel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 

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
            <h1>Online Movie System</h1>
            <h2>Create User</h2>
            <hr />
            <span class="text-danger"><%=request.getParameter("failure1") == null ? "":request.getParameter("failure1")%></span>
            <div class="row">
                <div class="col-sm-4">
                    <form method="post">
                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" class="form-control" placeholder="Enter the full name" name="fullName">
                </div>
                <div class="form-group">
                    <label >Email Address</label>
                    <input type="text" class="form-control" placeholder="Enter the email address" name="email" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" placeholder="Enter the password" name="password" required>
                </div>
                <div class="form-group">
                    <label>Phone Number</label>
                    <input type="text" class="form-control" placeholder="Enter the phone number" name="phoneNumber">
                </div>
                <hr />
                <a href="/users" class="btn btn-default">Cancel</a>
                <input type="submit" value="Create User" class="btn btn-success pull-right" />
            </form>
                </div>
            </div>
            
        </div>
    </body>
</html>
