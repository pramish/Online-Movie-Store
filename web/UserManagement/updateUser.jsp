<%-- 
    Document   : updateUser
    Created on : 08/05/2019, 5:59:14 PM
    Author     : pramishluitel
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.User"%>
<%User user = (User) session.getAttribute("editUser");%>
<%List<String> errors =(ArrayList)session.getAttribute("editUserErrors");%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OMS</title>
        <%@include file="/Header.html" %>
    </head>
    <body>
        
        <!--<%@include file="/heading.jsp" %>-->
        
        <h1>Update User details.</h1>
        <hr />
            <div class="container">
                <h1 class="text-center">Update User</h1>
                <hr>
                <div class="row">
                    <div class="col-md-6 col-md-push-3">
                        <ul>
                            <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
                        </ul>
                        <div class="row">
                            <div class="col-sm-6 col-sm-push-3">
                                
                                <form method="post">
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
                                        <input class="form-control" type="tel" name="phone" value="<%=user.getPhoneNumber()%>">
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input class="form-control" type="password" name="password" value="<%=user.getPassword()%>" required>
                                    </div>

                                    <div class="form-group">
                                        <label>Status</label>
                                        <br />
                                        <input  type="radio" name="status" value="ACTIVE" <%= "ACTIVE".equals(user.getStatus()) ? "checked":""%> /> Active
                                        <br />
                                        <input  type="radio" name="status" value="CANCELLED" <%= "CANCELLED".equals(user.getStatus()) ? "checked":""%> > Cancelled
                                        <br />
                                        <input  type="radio" name="status" value="CANCELLED" <%= "DEACTIVE".equals(user.getStatus()) ? "checked":""%> > De-Active
                                    </div>
                                    <hr/>
                                    <a href="/users" class="btn btn-default">Cancel</a>
                                    <input type="submit" class="btn btn-success pull-right" name="save" value="Save">

                                </form>
                                <hr />
                                <form method='post' action='/deleteUser'>
                                    <input type='submit' class='btn btn-danger' value='DELETE' onclick='return confirm("Are you sure you want to delete this user?")'/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <hr/>
            </div>
    </body>
</html>
