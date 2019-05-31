<%@page import="java.util.List"%>
<%@page import="MODEL.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<% List<Customer> customers = (List<Customer>) request.getAttribute("customers");%>
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
        
        <div class="container">
            <h1><a href="/">Online Movie Store</a></h1>
            <h2>Customer Management</h2>
            <hr />
            <div class="row">
                <div class="col-sm-4">
                    <a href="/" class="btn btn-default btn-block">Home</a>
                </div>
                <div class="col-sm-4">
                    <a class="btn btn-primary btn-block" href="/customer/add">Create Customer</a>
                </div>
            </div>
            <br />
            <form action="/customer/search">
                <div class="row">
                    <div class="col-sm-3">
                        <input class="form-control" type="search" name="name" placeholder="Search name">        
                    </div>
                    <div class="col-sm-2">
                        <select class="form-control" name="type">
                        <option value="">ALL TYPE</option>
                        <option value="INDIVIDUAL">INDIVIDUAL</option>
                        <option value="COMPANY">COMPANY</option>
                    </select>
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
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>TYPE</th>
                    <th>ADDRESS</th>
                    <th>STATUS</th>
                    <th style="min-width: 100px;"></th>
                </tr>
                </thead>
                <tbody>
                    <% for(Customer customer:customers) {%>
                    <tr>
                        <td> <%=customer.getId()%> </td>
                        <td> <%=customer.getName()%> </td>
                        <td> <%=customer.getEmail()%> </td>
                        <td><%=customer.getType()%></td>
                        <td><%=customer.getAddress()%></td>
                        <td><%=customer.getStatus()%> </td>
                        <td>
                            <a href="/customer/delete?id=<%=customer.getId()%>" class="btn btn-danger btn-xs" onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
                            <a href="view?id=<%=customer.getId()%>" class="btn btn-xs btn-primary">Edit</a> 
                        </td>
                    </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html>
