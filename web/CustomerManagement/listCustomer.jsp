<%@page import="java.util.List"%>
<%@page import="MODEL.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>OMS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%  List<Customer> customers = (List<Customer>) request.getAttribute("customers");
        %>
        <h1>Customer Management</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
             <a class="navbar-brand" href="/">HOME</a>
            <a class="navbar-brand" href="add">CREATE CUSTOMER</a>
            <div style="text-align: right;margin-top: 10px;">
                <form class="form-inline my-2 my-lg-0" action="/customer/search" method="post">
                    <input class="form-control mr-sm-2" type="search" name="name" placeholder="enter name">
                    <select class="form-control mr-sm-2" type="search" name="type">
                        <option value="">ALL TYPE</option>
                        <option value="INDIVIDUAL">INDIVIDUAL</option>
                        <option value="COMPANY">COMPANY</option>
                    </select>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav> 
        <div align="center">
            <table class="table table-borderless table-dark">
                <caption><h2>Customer Record</h2></caption>
                <tr class="table-success">
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>TYPE</th>
                    <th>ADDRESS</th>
                    <th>STATUS</th>
                    <th> </th>
                </tr>
                <% for(Customer customer:customers) {%>
                <tr class="table-success">
                    <td> <%=customer.getId()%> </td>
                    <td> <%=customer.getName()%> </td>
                    <td> <%=customer.getEmail()%> </td>
                    <td><%=customer.getType()%></td>
                    <td><%=customer.getAddress()%></td>
                    <td><%=customer.getStatus()%> </td>
                    <td><a href="delete?id=<%=customer.getId()%>">delete</a>&nbsp;<a href="view?id=<%=customer.getId()%>">edit</a> </td>
                </tr>
                <%}%>
            </table>
        </div>  
        
        
    </body>
</html>
