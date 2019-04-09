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
                            <h3 class="panel-title">Register Page</h3>
                        </div>
                        <div class="panel-body">
                            <form method="post">
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input type="email" class="form-control" id="email">
                                </div>
                                <div class="form-group">
                                    <label for="firstName">First Name:</label>
                                    <input type="text" class="form-control" id="firstName">
                                </div>
                                <div class="form-group">
                                    <label for="lastName">Last Name:</label>
                                    <input type="text" class="form-control" id="lastName">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <input type="password" class="form-control" id="password">
                                </div>
                                <a href="/" class="btn btn-default">Cancel</a>
                                <button type="button" onclick="LogIn()" class="btn btn-primary pull-right">Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>    
        </div>
    </body>
</html>
