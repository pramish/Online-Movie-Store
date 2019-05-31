<%-- 
    Document   : addMovie
    Created on : 19/05/2019, 4:21:04 PM
    Author     : luckylau
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%List<String> errors =(ArrayList)session.getAttribute("movieErrors");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
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
            <h2>Add Movie</h2>
            <hr />
            <ul>
                <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
            </ul>
            <div class="row">
                <div class="col-sm-4">
                    <form method="post">
                        <div class="form-group">
                            <label>Title</label>
                            <input type="text" class="form-control" name="title" >
                        </div>
                        <div class="form-group">
                            <label>Genre</label>
                            <input type="text" class="form-control" name="genre" >
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input type="number" step="0.01" class="form-control" name="price" >
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="number" step="1" class="form-control" name="stock" >
                        </div>
                        <hr />
                        <a href="/movie/list" class="btn btn-default">Cancel</a>
                        <input type="submit" value="Add Movie" class="btn btn-success pull-right" />
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
