<%@page import="MODEL.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 

<% 
    List<String> errors = (List<String>)request.getAttribute("errors");
    Customer customer = (Customer)request.getAttribute("customer");
%>

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
            <h2>Create Customer</h2>
            <hr />
            <form method="POST">
                <div class="row">
                    <div class="col-sm-4">
                        <ul>
                            <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
                        </ul>
                        <div class="form-group">
                            <label for="validationDefault02">NAME</label>
                            <input type="text" class="form-control" id="validationDefault02" placeholder="enter the name" name="name" value="<%=customer.getName()%>">
                        </div>
                        <div class="form-group">
                            <label for="validationDefault03">EMAIL</label>
                            <input type="text" class="form-control" id="validationDefault03" placeholder="enter the email" name="email" value="<%=customer.getEmail()%>">
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
                        <hr />
                        <a href="/customer/list" class="btn btn-default">Cancel</a>
                        <input type="submit" value="Create Customer" class="btn btn-success pull-right" />
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
