<%@page import="java.util.ArrayList"%>
<%@page import="MODEL.Customer"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MODEL.controller.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Management</title>
        <%@include file="/Header.html" %>
    </head>
    <body>
        <h1>Customer Management</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <a class="navbar-brand" href="/index.jsp">HOME</a>
            <a class="navbar-brand" href="list">SEARCH CUSTOMER</a>
        </nav> 
        <% List<String> errors = (List<String>)request.getAttribute("errors");
           Customer customer = (Customer)request.getAttribute("customer");
        %>
        <div class="container">
        <caption><h2>Update Customer</h2></caption>
        <div class="row">
                <div class="col-md-6 col-md-push-3">
                    <form action="/customer/edit" method="POST">
                        <ul>
                            <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
                        </ul>
                        <div class="row">
                            <div class="col-sm-8 col-sm-push-2">
                            <div class="form-group">
                                <label for="validationDefault01">ID</label>
                                <input type="text" class="form-control" id="validationDefault01" placeholder="enter the id" name="id" value="<%=customer.getId()%>" readOnly>
                            </div>
                            <div class="form-group">
                                <label for="validationDefault02">NAME</label>
                                <input type="text" class="form-control" id="validationDefault02" placeholder="enter the name" name="name" value="<%=customer.getName()%>" required>
                            </div>
                            <div class="form-group">
                                <label for="validationDefault03">EMAIL</label>
                                <input type="text" class="form-control" id="validationDefault03" placeholder="enter the email" name="email" value="<%=customer.getEmail()%>" required>
                            </div>
                            <div class="form-group">
                                <label for="validationDefault05">TYPE</label>
                                <input type="radio" name="type" value="INDIVIDUAL" <% if(customer.getType().equals("INDIVIDUAL")||customer.getType().equals("")){ %> checked<%}%> > INDIVIDUAL
                                <input type="radio" name="type" value="COMPANY" <% if(customer.getType().equals("COMPANY")){ %> checked<%}%>> COMPANY
                            </div>
                        
                            <div class="form-group">
                                <label for="validationDefault03">ADDRESS</label>
                                <input type="text" class="form-control" id="validationDefault06" placeholder="enter the address" name="address" value="<%=customer.getAddress()%>">
                            </div>
                            <div class="form-group">
                                <label for="validationDefault05">STATUS</label>
                                <input type="radio" name="status" value="ACTIVE" <% if(customer.getStatus().equals("ACTIVE")||customer.getStatus().equals("")){ %> checked<%}%>> ACTIVE
                                <input type="radio" name="status" value="DEACTIVE" <% if(customer.getStatus().equals("DEACTIVE")){ %> checked<%}%>> DEACTIVE
                                <input type="radio" name="status" value="CANCELLED" <% if(customer.getStatus().equals("CANCELLED")){ %> checked<%}%>> CANCELLED
                            </div>
                            </div>
                        </div>
                        <div class="container-fluid">
                            <div class="col-sm-12 text-right">
                                <button class="btn btn-primary btn-lg active" type="submit" name="submit">Update User</button>
                            </div>
                        </div>
                    </form>
                </div>
        </div>
        </div>
    </body>
</html>
