<%@page import="MODEL.User"%>
<%@page import="MODEL.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%@include file="/Header.html" %>
    <body>
        <%Order order = (Order) session.getAttribute("order1");
            String success = (String) request.getParameter("success");
            String failure = (String) request.getParameter("failure");
        %>
        <%if (success != null) { %>
        <div class="alert alert-success">
            <%out.print(success);%> 
        </div>
        <%  }
        %>
        <%if (failure != null) { %>
        <div class="alert alert-success">
            <%out.print(failure);%> 
        </div>
        <%  }
        %>

        <% if (order != null) {%>
        <div align="center">
            <table class="table table-borderless table-dark">
                <caption><h2>Order Record</h2></caption>
                <tr class="table-success">
                    <th>Order ID</th>
                    <th>MovieID</th>
                    <th>UserID</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>TotalPrice</th>
                    <th>Status</th>
                </tr>
                <tr class="table-success">
                    <td> <%=order.getID()%> </td>
                    <td> <%=order.getMovieID()%> </td>
                    <td><%=order.getUserID()%></td>
                    <td><%=order.getPrice()%></td>
                    <td><%=order.getAmount()%></td>
                    <td><%=order.getTotalPrice()%></td>
                    <td> <%=order.getStatus()%> </td>
                </tr>
            </table>
        </div>  
        <%}%>

        <%
            System.out.println(order.getID());
            System.out.println(order.getUserID());
            System.out.println(order.getDate());
        %>



    </body>
</html>
