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
            <div class="row">
                <div class="col-sm-6 col-sm-push-3 col-md-4 col-md-push-4">                
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Order Complete!</h3>
                        </div>
                        <div class="panel-body text-center" id="content">
                            <h3>Your order has been completed!</h3>
                            <h4>Your Order Number is</h4>
                            <h2>2135423</h2>
                            <p>Please present this number to the store when collecting your movie.</p>
                            
                            <a href="list.jsp" class="btn btn-block btn-primary">Take me to back Movies</a>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
        <script>
        function displayOrdersBtn(){
            if(IsLoggedIn())
            {
                var orderslink = document.createElement("a");
                orderslink.href = "/orders.jsp";
                orderslink.className = "btn btn-primary btn-block";
                orderslink.textContent = "View My Orders";
                document.getElementById("content").appendChild(orderslink);
            }
        }    
        $(document).ready(displayOrdersBtn());
        </script>
    </body>
</html>
