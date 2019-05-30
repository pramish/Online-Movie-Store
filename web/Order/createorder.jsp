<%@page import="MODEL.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    //List<Movie> movielist = (ArrayList) session.getAttribute("movieAlllist");
    Movie movie = (Movie) session.getAttribute("buyMovie");
    Order order = (Order) session.getAttribute("order");
    List<String> errors =(ArrayList)session.getAttribute("orderErrors");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Order</title>
    </head>
    <body>
        <div><span class="time" id="time" ></span></div>
        <h1>Order Page</h1> 
        
        <hr />
        <table>
            <tr>
                <td>Title</td>
                <td><%=movie.getTitle()%></td>
            </tr>
            <tr>
                <td>Price</td>
                <td>$<%=movie.getPrice()%></td>
            </tr>
            <tr>
                <td>Stock</td>
                <td><%=movie.getStock()%></td>
            </tr>
        </table>
        
        <ul>
                                    <%for(String error: errors){%><li class="text-danger"><%=error%></li><%}%>
                                </ul>
        <form method="post">
            <table>
                <tr><td>Amount:</td><td><input size="23" type="text" name="amount" oninput="value=value.replace(/[^\d]/g,'')"></td></tr>
                <tr><td></td>
                    <td>
                        <%if(order == null){%>
                            <a href="/movie/list" >Back to Movie List</a>    
                        <%}else{%>
                            <input type="submit" name="action" value="Cancel" onclick="return confirm('Are you sure you want to cancel this order?')" />
                        <%}%>
                        
                        <input type="submit" name="action" value="Save" />
                        <input name="action" type="submit" value="Buy Now" /> 
                    </td>
                </tr>
            </table>
        </form>

        <jsp:include page="/ConnServlet" flush="true" />

    </body>

</html>
