<%-- 
    Document   : login
    Created on : 24/05/2019, 12:30:25 AM
    Author     : jonny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/Header.html" %>
    </head>
    <body onload="startTime()">
        <div><span class="time" id="time" ></span></div>
        <h1>Enter your details to login: <span class="error"><c:if test="${existErr!=null}"><span class="error"><c:out value="${existErr}"/></span></c:if></span></h1>
        
        <form action="/login" method="post">
            <table>                
                <tr><td>Email:</td><td><input type="text" name="userEmail"></td></tr>
                <tr><td>Password:</td><td><input type="password" name="userPassword"></td></tr>                
                <tr><td></td>
                    <td><input class="button" type="submit" value="Login"> 
                        &nbsp; 
                        <button class="button" type="button" onclick="location.href = 'index.jsp'" > Home Page </button>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
