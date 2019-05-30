<%-- 
    Document   : addMovie
    Created on : 19/05/2019, 4:21:04 PM
    Author     : luckylau
--%>

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
            <h1>Online Movie System</h1>
            <h2>Add Movie</h2>
            <hr />
            <span class="text-danger"><%=request.getParameter("failure1") == null ? "":request.getParameter("failure1")%></span>
            <div class="row">
                <div class="col-sm-4">
                    <form method="post">
                        <div class="form-group">
                            <label>Title</label>
                            <input type="text" class="form-control" name="title" required>
                        </div>
                        <div class="form-group">
                            <label>Genre</label>
                            <input type="text" class="form-control" name="genre" required>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input type="number" step="0.01" class="form-control" name="price" required>
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="number" step="1" class="form-control" name="stock" required>
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
