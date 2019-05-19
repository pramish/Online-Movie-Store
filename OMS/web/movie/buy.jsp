<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/header.html" %>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Online Movie Store (OMS)</h1>
            <h3 class="text-center">Your One Stop Spot To Shop Top Movies!</h3>
            <hr />
                <a href="/" class="">Home</a> / <a href="/movie/list.jsp" class="">Movies</a> / Buy
            <hr />
            <div class="row">
                <div class="col-md-10 col-md-push-1">
                    <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Buy Movie</h3>
                </div>
                <div class="panel-body">
                    <form method="post" class="form-horizontal">
                        <div class="row">
                            <div class="col-md-6">                
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Title:</label>
                                    <div class="col-sm-9">
                                      <p class="form-control-static" id="title"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Description:</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static" id="description"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Stock:</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static" id="stock"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Price:</label>
                                    <div class="col-sm-9">
                                        <p class="form-control-static" id="price"></p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">                
                                <div class="form-group">
                                    <label class="control-label col-sm-3 col-md-4" for="qty">Quantity:</label>
                                    <div class="col-sm-9 col-md-8">
                                      <input type="number" class="form-control" id="qty" min="1" max="" name="qty" value="1" step="1" placeholder="Enter Qty">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3 col-md-4">Total Cost</label>
                                    <div class="col-sm-9 col-md-8">
                                        <p class="form-control-static" id="cost"></p>
                                    </div>
                                </div>
                                <a href="ordercomplete.jsp" class="btn btn-success pull-right">Buy</a>
                            </div>
                        </div>
                    </form>
                    
                </div>
            </div>
                </div>
            </div>
            
        </div>
        <script>
            const urlParams = new URLSearchParams(window.location.search);
            const myParam = urlParams.get('movieId');
            var price = 0;
            Movies.forEach(function(movie){
                if(movie.Id == myParam)
                {
                    document.getElementById("title").textContent = movie.Title;
                    document.getElementById("description").textContent = movie.Description;
                    document.getElementById("stock").textContent = movie.Stock;
                    document.getElementById("qty").max = movie.Stock;
                    document.getElementById("price").textContent = "$"+movie.Price;
                    document.getElementById("cost").textContent = "$"+movie.Price;
                    price = movie.Price;
                }
            });
            
            $("#qty").change(function(){
                var total = price * $(this).val();
                document.getElementById("cost").textContent = "$"+total;
              });
        </script>
    </body>
</html>
