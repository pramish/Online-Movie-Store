<%-- 
    Document   : updateStaff
    Created on : 31/05/2019, 12:07:11 AM
    Author     : pramishluitel
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Staff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Staff staff = (Staff) session.getAttribute("editStaff");
    List<String> errors = (ArrayList) session.getAttribute("editStaffErrors");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="/Header.html" %>
    </head>
    <body>
        <h1>Update Staff details.</h1>
        <hr />
        <div class="container">
            <h1 class="text-center">Update Staff</h1>
            <hr>
            <div class="row">
                <div class="col-md-6 col-md-push-3">
                    <ul>

                    </ul>
                    <div class="row">
                        <div class="col-sm-6 col-sm-push-3">
                            <ul>
                                <%for (String error : errors) {%><li class="text-danger"><%=error%></li><%}%>
                            </ul>
                            <form method="post">
                                <div class="form-group">
                                    <label>Staff ID</label>
                                    <input class="form-control" type="text" name="ID" value="<%=staff.getID()%>" readonly>
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input class="form-control" type="email" name="email" value="<%=staff.getEmail()%>" required>
                                </div>
                                <div class="form-group">
                                    <label>Full Name</label>
                                    <input class="form-control" type="text" name="name" value="<%=staff.getName()%>">
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="form-control" type="text" name="address" value="<%=staff.getAddress()%>">
                                </div>
                                <div class="form-group">
                                    <label>Status</label>
                                    <br />
                                    <input  type="radio" name="position" value="STAFF" <%= "ACTIVE".equals(staff.getPosition()) ? "checked" : ""%> /> STAFF
                                    <br />
                                    <input  type="radio" name="position" value="ADMIN" <%= "CANCELLED".equals(staff.getPosition()) ? "checked" : ""%> > ADMIN
                                </div>
                                <hr/>
                                <a href="/readStaff" class="btn btn-default">Cancel</a>
                                <input type="submit" class="btn btn-success pull-right" name="save" value="Save">

                            </form>
                            <hr />
                            <form method='post' action='/deleteStaff'>
                                <input type='submit' class='btn btn-danger' value='DELETE' onclick='return confirm("Are you sure you want to delete this staff?")'/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
        </div>
    </body>
</html>
