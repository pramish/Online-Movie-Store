<%@page import="MODEL.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<!DOCTYPE html>

<%List<Order> orderlist = (ArrayList) session.getAttribute("orderlist");%>

<html>
    <head>
        <title>OMS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/css/style.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="container">
            <h1><a href="/">Online Movie Store</a></h1>
            <h2>My Order History</h2>
            <hr />
            <div class="row">
                <div class="col-sm-4">
                    <a href="/" class="btn btn-default btn-block">Home</a>
                </div>
            </div>
            <br />
            <form>
                <div class="row">
                    <div class="col-sm-3">
                        <input class="form-control" type="search" name="id" placeholder="Enter ID">        
                    </div>
                    <div class="col-sm-3">
                        <input class="form-control" type="date" name="date" placeholder="Enter a date">        
                    </div>
                    <div class="col-sm-2">
                        <input type="submit" class="btn btn-primary btn-block" value="Search">        
                    </div>
                </div>
            </form>
            <hr />
         
        <table class="table table-condensed table-bordered table-striped table-hover"> 
            <thead>
                <tr>
                    <th>OrderID</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>TotalPrice</th>
                    <th>Status</th>
                    <th>Date</th>
                    <th></th>
                </tr>
            </thead>
            
            <tbody>
                <%for (Order m : orderlist) {%>
            <tr>
                <td><%=m.getID()%></td>
                
                <td><%=m.getTitle()%></td>
                <td><%=m.getPrice()%></td>
                <td><%=m.getAmount()%></td>
                <td><%=m.getTotalPrice()%></td>
                <td><%=m.getStatus()%></td>
                <td><%=m.getDate()%></td>
                <td>
                    <%if(!m.getStatus().equals("CANCELLED")){%>
                        <a href="/order/cancel?id=<%=m.getID()%>" class="btn btn-xs btn-danger" onclick="return confirm('Are you sure you want to cancel this order?')">Cancel</a>
                    <%}%>
                    
                    <%if(m.getStatus().equals("SAVED")){%>
                        <a href="/order/update?id=<%=m.getID()%>" class="btn btn-xs btn-primary">Update</a>
                        
                    <%}%>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table> 
            
        </div>
    </body>
</html>
