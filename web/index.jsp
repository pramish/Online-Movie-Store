<%@page import="MODEL.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MODEL.controller.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Menu</title>
    </head>
    <%@include file="Header.html" %>
    <body>
        <%
            
            String success1 = (String) request.getParameter("success1");
            String failure1 = (String) request.getParameter("failure1");
        %>
        
        <%if (success1 != null) { %>
        <div class="alert alert-success">
            <%out.print(success1);%> 
        </div>
        <%  }
        %>
         <%if (failure1 != null) { %>
        <div class="alert alert-success">
            <%out.print(failure1);%> 
        </div>
        <%  }
        %>
        <%@include file="heading.jsp" %>
        <jsp:include page="/ConnServlet" flush="true" /> 
        <div class="container">
            <hr>
            <div class="row">
                <div class="col-sm-6 col-md-4">
                    <ul class="list-group">
                       
                        <li class="list-group-item"><a href="/users">User list</a></li>
                        
                        
                        
                        <%
                        User user = (User)session.getAttribute("user");
                        if(user != null && "ACTIVE".equals(user.getStatus())){
                            %>
                            <li class="list-group-item"><a href="/myaccount">My Account</a></li>
                            <li class="list-group-item"><form method="post" action="/logout"><input type="submit" value="Logout" /></form></li>
                            <li class="list-group-item"><a href="/myaccesslogs">My Access Logs</a></li>
                            
                            <%
                        }else{
%>
                            <li class="list-group-item"><a href="/login">Login</a></li>
                             <li class="list-group-item"><a href="/register">Register</a></li>
                            
                            <%
}
                        %>
                        
                    </ul>
                </div>
            </div>
            
        </div>
        
    </body>
</html>