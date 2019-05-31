<%-- 
    Document   : updateMovie
    Created on : 19/05/2019, 4:21:15 PM
    Author     : luckylau
--%>

<%@page import="MODEL.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/ConnServlet" flush="true" /> 
<%Movie movie = (Movie) session.getAttribute("movie");%> 
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
            <h1><a href="/">Online Movie System</a></h1>
            <h2>Update Movie</h2>
            <hr />
            <span class="text-danger"><%=request.getParameter("failure1") == null ? "":request.getParameter("failure1")%></span>
            <div class="row">
                <div class="col-sm-4">
                    <form method="post">
                        <div class="form-group">
                            <label>ID</label>
                            <input type="text" class="form-control" value="<%=movie.getID()%>" readOnly>
                        </div>
                        <div class="form-group">
                            <label>Title</label>
                            <input type="text" class="form-control" name="title" value="<%=movie.getTitle()%>" required />
                        </div>
                        <div class="form-group">
                            <label>Genre</label>
                            <input type="text" class="form-control" name="genre" value="<%=movie.getGenre()%>" required />
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input type="text" class="form-control" value="<%=movie.getPrice()%>" name="price" required />
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="text" class="form-control" value="<%=movie.getStock()%>" name="stock" required />
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


