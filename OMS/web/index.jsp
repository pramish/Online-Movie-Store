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
                <div class="col-md-8 col-md-push-2">
                    <div class="row" id="links">
                <div class="col-sm-4" id="btn1">
                    <a href="/movie/list.jsp" class="btn btn-lg btn-block btn-primary">Browse Movies</a>
                </div>
                <div class="col-sm-4" id="btn2"></div>
                <div class="col-sm-4" id="btn3"></div>
            </div>
                </div>
            </div>
            
	</div>
        
        <script>
            function displayBtns()
            {
                var btn2 = document.createElement("a");
                var btn3 = document.createElement("a");
                btn2.className = btn3.className = "btn btn-lg btn-block btn-primary";

                if(IsLoggedIn()){
                    btn2.textContent = "My Orders";
                    btn2.href = "/orders.jsp";

                    btn3.className = "btn btn-lg btn-block btn-default";
                    btn3.textContent = "Logout";
                    btn3.href = "#";
                    btn3.onclick = function(){
                        LogOut();
                    };
                }else{
                    btn2.textContent = "Register";
                    btn2.href = "/register.jsp";

                    btn3.textContent = "Login";
                    btn3.href = "/login.jsp";
                }

                document.getElementById("btn2").appendChild(btn2);
                document.getElementById("btn3").appendChild(btn3);
            }
            $(document).ready(displayBtns());
        </script>
    </body>
</html>
