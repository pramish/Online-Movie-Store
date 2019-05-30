<%-- 
    Document   : createUser
    Created on : 08/05/2019, 5:58:50 PM
    Author     : pramishluitel
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MODEL.controller.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
        <%@include file="/Header.html" %>

    </head>
    <body>

        <%@include file="/staffMenu.jsp" %>
        <h1>Creating Staff</h1>
        <br><br>

        <form action="/createStaff" method="POST">
            <div class="form-row">
                <div class="col-md-4 mb-3">


                    <label for="validationDefault01">Full Name</label>
                    <input type="text" class="form-control" id="validationDefault01" placeholder="enter the full name" name="name">
                </div>
                <div class="col-md-4 mb-3">

                    <label for="validationDefault02">Email Address</label>
                    <input type="text" class="form-control" id="validationDefault02" placeholder="enter the email address" name="email">
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-3 mb-3">

                    <label for="validationDefault05">Address</label>
                    <input type="text" class="form-control" id="validationDefault05" placeholder="enter the address" name="address">
                </div>
                 <div class="col-md-6 mb-3">

                    
                    <br><br>
                    <label for="validationDefault03">Position</label>
                    <td> 
                        <select name="position">
                            <option>Staff</option>
                            <option>Admin</option>
                        </select>
                    </td>
                </tr>
                </div>
            </div>
            <br><br><br><br><br><br><br><br>
            <div class="container-fluid">
                <div class="col-sm-12 text-center">
                    <button  class="btn btn-success" type="submit" name="submit">Create Staff</button>
                </div>
            </div>
        </form>

    </body>
</html>
