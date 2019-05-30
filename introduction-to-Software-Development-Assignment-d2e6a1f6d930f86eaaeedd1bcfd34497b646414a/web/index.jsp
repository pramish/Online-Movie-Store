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
        <h1>Admin Menu</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <a class="navbar-brand" href="createUser.jsp">Create Users</a>
            <a class="navbar-brand" href="updateUser.jsp">Update Users</a>
            <a class="navbar-brand" href="deleteUser.jsp">Delete Users</a>
            <a class="navbar-brand" onClick="showHistoryList()" href="javascript:void(0);">Order history</a>
            <br><br><br>
            <div style="text-align: right;">
                <form class="form-inline my-2 my-lg-0" action="readUserController.jsp" method="post">
                <input class="form-control mr-sm-2" type="search" name="name" placeholder="enter name">
                <input class="form-control mr-sm-2" type="search" name="phoneNumber" placeholder="enter phonenumber">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
            </div>
        </nav> 
        <jsp:include page="/ConnServlet" flush="true" /> 
                    
        
        
        
        <div class="container">
            <hr>
            <div class="row">
                <div class="col-sm-6 col-md-4">
                    <ul class="list-group">
                       
                        
                        
                        
                        
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
                        <li class="list-group-item"><a href="/movie/list">Movie Catalogue</a></li>
                    </ul>
                </div>
            </div>
            
        </div>
        
    </body>
     <script  type="text/javascript">
        function showHistoryList()
        {
            var contex="<%=request.getContextPath()%>";
            window.location.href = contex + "/order/history";
        }
    </script>
</html>