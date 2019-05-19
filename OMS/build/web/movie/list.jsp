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
                <a href="/" class="">Home</a> / Movies
            <hr />
            <form class="form-inline">
                <div class="form-group">
                  <label for="search">Search:</label>
                  <input type="search" class="form-control" id="search">
                </div>
                <div class="form-group">
                  <label for="filter">Genre</label>
                  <select class="form-control" name="genre">
                      <option value="">All</option>
                      <option value="Action">Action</option>
                      <option value="Adventure">Adventure</option>
                      <option value="Comedy">Comedy</option>
                      <option value="Crime">Crime</option>
                      <option value="Drama">Drama</option>
                      <option value="Fantasy">Fantasy</option>
                      <option value="Horror">Horror</option>
                      <option value="Thriller">Thriller</option>
                      <option value="Romance">Romance</option>
                  </select>
                </div>
                <input type="submit" class="btn btn-default" value="Search" />
            </form>
            <hr />
            <table class="table table-condensed table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Movie Title</th>
                        <th>Genre</th>
                        <th>Description</th>
                        <th class="text-right">Stock</th>
                        <th class="text-right">Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="movieList">
                    
                </tbody>
            </table>
        </div>
        <script>
            function getTd(data, className="")
            {
                var x = document.createElement("td");
                x.textContent = data;
                x.className = className;
                return x;
            }
            function getTableRecord(movie)
            {
                var loggedIn = IsLoggedIn();
                var tr = document.createElement("tr");
                tr.appendChild(getTd(movie.Title));
                tr.appendChild(getTd(movie.Genre));
                tr.appendChild(getTd(movie.Description));
                tr.appendChild(getTd(movie.Stock,"text-right"));
                tr.appendChild(getTd("$"+movie.Price,"text-right"));

                var buttonTd = document.createElement("td");
                buttonTd.className = "text-right";
                if(movie.Stock > 0 && movie.ActiveStatus)
                {
                    var buyBtn = document.createElement("a");
                    buyBtn.href = "buy.jsp?movieId="+movie.Id;
                    buyBtn.className = "btn btn-primary btn-xs";
                    buyBtn.textContent = "BUY";
                    buttonTd.appendChild(buyBtn);
                }
                tr.appendChild(buttonTd);
                return tr;
            }
            
            function displayMovies(){
                var tableBody = document.getElementById("movieList");
                Movies.forEach(function(item){
                    tableBody.appendChild(getTableRecord(item));
                });
            } 
            $(document).ready(displayMovies());
        </script>
    </body>
</html>
