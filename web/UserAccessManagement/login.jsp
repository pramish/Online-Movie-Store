<%-- 
    Document   : login
    Created on : 24/05/2019, 12:30:25 AM
    Author     : jonny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
    <head>
        <title>OMS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body onload="startTime()">
        <div><span class="time" id="time" ></span></div>
        <div class="container">
            <h1><a href="/">Online Movie Store</a></h1>
            <h2>Login</h2>
            <hr />
            <h2><span class="error"><c:if test="${existErr!=null}"><span class="error"><c:out value="${existErr}"/></span></c:if></span></h2>
            
            <form method="post">
                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label>Email:</label>
                            <input type="text" class="form-control" name="userEmail" />
                        </div>
                        
                        <div class="form-group">
                            <label>Password:</label>
                            <input type="password" name="userPassword" class="form-control" /> 
                        </div>
                        <hr />
                        <a href="/" class="btn btn-default"> Home</a>
                        <input class="btn btn-success pull-right" type="submit" value="Login"> 
                    </div>
                </div>
        </form>
        </div>
    </body>
</html>
