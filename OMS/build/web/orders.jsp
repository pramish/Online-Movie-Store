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
                <a href="/" class="">Home</a> / My Orders
            <hr />
            <table class="table table-condensed table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Order No.</th>
                        <th>Movie Title</th>
                        <th class="text-right">Price</th>
                        <th class="text-right">Qty</th>
                        <th class="text-right">Total Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="orderList">
                    
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
            function getTableRecord(data)
            {
                var loggedIn = IsLoggedIn();
                var tr = document.createElement("tr");
                tr.appendChild(getTd(data.TimeStamp));
                tr.appendChild(getTd(data.Id));
                tr.appendChild(getTd(data.Title));
                tr.appendChild(getTd("$"+data.Price,"text-right"));
                tr.appendChild(getTd(data.Stock,"text-right"));
                tr.appendChild(getTd("$"+data.Total,"text-right"));
                
                var buttonTd = document.createElement("td");
                buttonTd.className = "text-right";
                if(data.Stock > 0 && data.ActiveStatus)
                {
                    var buyBtn = document.createElement("a");
                    buyBtn.href = "#";
                    buyBtn.className = "btn btn-danger btn-xs";
                    buyBtn.textContent = "CANCEL ORDER";
                    buyBtn.onclick = function(){
                        if(confirm("Are you sure you want to cancel this order?"))
                        {
                            $(this).hide();
                        }
                    };
                    
                    
                    buttonTd.appendChild(buyBtn);
                    
                }
                tr.appendChild(buttonTd);
                return tr;
            }
            
            function displayOrders(){
                var tableBody = document.getElementById("orderList");
                Orders.forEach(function(item){
                    tableBody.appendChild(getTableRecord(item));
                });
            } 
            $(document).ready(displayOrders());
        </script>
    </body>
</html>
