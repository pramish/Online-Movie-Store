<%@page import="MODEL.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<!DOCTYPE html>
<%
    //List<Movie> movielist = (ArrayList) session.getAttribute("movieAlllist");
    Movie movie = (Movie) session.getAttribute("buyMovie");
    Order order = (Order) session.getAttribute("order");
    List<String> errors =(ArrayList)session.getAttribute("orderErrors");
%>
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
            <h2>Order Movie</h2>
            <hr />
            <div class="row">
                <div class="col-md-4">
                    <form method="post">
                        
                        <ul>
                            <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
                        </ul>
                        <div class="form-group">
                            <label>Movie Title</label>
                            <input class="form-control" type="text" name="title" value="<%=movie.getTitle()%>" readonly>
                        </div>
                       <div class="form-group">
                            <label>Price</label>
                            <input class="form-control" type="text" name="price" value="$<%=movie.getPrice()%>" readonly>
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input class="form-control" type="text" name="stock" value="<%=movie.getStock()%>" readonly>
                        </div>
                        <div class="form-group">
                            <label>Amount</label>
                            <input class="form-control" type="text" name="amount" value="<%=(order != null ? order.getAmount():"")%>" required>
                        </div>
                        <hr />
                        
                        <%if(order != null){%>
                            <a href="/order/history" class="btn btn-default">Back to Order History</a> 
                        <%}else{%>
                            <a href="/movie/list" class="btn btn-default">Back to Movie List</a> 
                        <%}%>
                        <input name="action" type="submit" value="Save" class="btn btn-warning" />
                        <input name="action" type="submit" value="Submit"  class="btn btn-success pull-right"/> 

                    </form>
                </div>
            </div>
        </div>

    </body>

</html>
