<%-- 
    Document   : updateMovie
    Created on : 19/05/2019, 4:21:15 PM
    Author     : luckylau
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<%Movie movie = (Movie) session.getAttribute("movie");%> 
<%List<String> errors =(ArrayList)session.getAttribute("movieErrors");%>
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
            <h2>Update Movie</h2>
            <hr />
            <ul>
                <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
            </ul>
            <div class="row">
                <div class="col-sm-4">
                    <form method="post">
                        <div class="form-group">
                            <label>ID</label>
                            <input type="text" class="form-control" value="<%=movie.getID()%>" readOnly>
                        </div>
                        <div class="form-group">
                            <label>Title</label>
                            <input type="text" class="form-control" name="title" value="<%=movie.getTitle()%>"  />
                        </div>
                        <div class="form-group">
                            <label>Genre</label>
                            <input type="text" class="form-control" name="genre" value="<%=movie.getGenre()%>"  />
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input type="text" class="form-control" value="<%=movie.getPrice()%>" name="price"  />
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="text" class="form-control" value="<%=movie.getStock()%>" name="stock"  />
                        </div>
                        <hr />
                        <a href="/movie/list" class="btn btn-default">Cancel</a>
                        
                        <input type="submit" value="Save" class="btn btn-success pull-right" />
                    </form>
                    <form method="post" action="/movie/delete">
                        <input class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this movie?')" type="submit" value="Delete" />     
                    </form>    
                </div>
            </div>
        </div>
    </body>
</html>


