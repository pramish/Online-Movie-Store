<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MODEL.controller.*"%>
<%@page import="java.sql.*"%>

<jsp:include page="/ConnServlet" flush="true" /> 
<% User user = (User)session.getAttribute("user"); %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="Header.html" %>
    </head>
    <body>
        <div class="container">
            <h1>Online Movie System</h1>
            <hr />
            <ul class="list-group">
                
                
                <li class="list-group-item"><a href="/movie/list">Movie Catalogue</a></li>
                <li><hr /></li>
                    
            <%if(user != null && "ACTIVE".equals(user.getStatus())){%>
                      
                <li class="list-group-item"><a href="/myaccount">My Account</a></li>
                <li class="list-group-item"><a href="/myaccesslogs">My Access Logs</a></li>
                <li class="list-group-item"><a href="/myaccesslogs">My Orders</a></li>
                <li class="list-group-item"><form method="post" action="/logout"><input type="submit" value="Logout" /></form></li>
                <li><hr /></li>
                <li class="list-group-item"><a href="/users">Users</a></li>
                <li class="list-group-item"><a href="/customers">Customers</a></li>
                                
                            
            <%}else{%>
                <li class="list-group-item"><a href="/login">Login</a></li>
                <li class="list-group-item"><a href="/register">Register</a></li>
            <%}%>
                
                
            </ul>
        </div>
    </body>
</html>