<%@page contentType="text/html" import="java.util.*" import="MODEL.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    List<UserAccessLogs> logList = (ArrayList)session.getAttribute("logList");
      
    
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Access List</title>
    </head>
    <body>
        <h1>My Access List</h1>
        
        <form>
            <input class="form-control mr-sm-2" type="search" name="date" placeholder="enter date">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>     
 
<table align="center" cellpadding="5" cellspacing="5" border="1">
    <%
            for (UserAccessLogs l : logList){
                
                %>
                <tr>
                    <td><%=l.getID()%></td>
                    <td><%=l.getUserID()%></td>
                    <td><%=l.getAccessType()%></td>
                    <td><%=l.getTimeStamp()%></td>
                    <td><a <%=l.getID()%>>Delete</a></td>
                 
                    </tr>
                <%

            }
    %>

</table>
