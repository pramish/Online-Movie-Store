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
    <tr class"table-success">
        <th>ID</th>
        <th>UserID</th>
        <th>AccessType</th>
        <th>TimeStamp</th>
        <th></th>
    </tr>
    <%
            for (UserAccessLogs l : logList){
                
                %>
                <tr>
                    <td><%=l.getID()%></td>
                    <td><%=l.getUserID()%></td>
                    <td><%=l.getAccessType()%></td>
                    <td><%=l.getTimeStamp()%></td>
                    
                    <td>
                        <form method="post" action="/deleteLogs">
                            <input type="hidden" name="id" value="<%=l.getID()%>" />
                            <input type="submit" value="Delete" />
                        </form>
                    </td>
                    
                 
                    </tr>
                <%

            }
    %>
    <br><br>
    <button type="button" class="btn btn-link">Link</button>
</table>
