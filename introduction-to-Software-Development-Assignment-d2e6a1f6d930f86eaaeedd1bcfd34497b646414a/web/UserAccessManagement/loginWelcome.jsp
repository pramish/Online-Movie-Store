<%-- 
    Document   : loginWelcome
    Created on : 24/05/2019, 11:01:29 AM
    Author     : jonny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/Header.html" %>
    </head>
    <body>
        <h1>Hello World!</h1>
          <form action="/logout" method="post">
        <tr><td align="right">
        <td><input class="button" type="submit" value="Logout"> 
            </td>
        </tr>
          </form>
    </body>
</html>
