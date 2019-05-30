<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Movie> movielist = (ArrayList) session.getAttribute("movieAlllist");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Order</title>
    </head>
    <body onload="startTime()">
        <div><span class="time" id="time" ></span></div>
        <h1>create your order:</h1> 
        <br>
        <form action="/order/create" method="post"  onsubmit="return verify()">
            <table>
                <tr><td>Movie:</td>
                    <td>
                        <select name="movieId" id="movieId">
                            <%
                                for (Movie m : movielist) {
                            %>
                            <option value="<%=m.getID()%>"><%=m.getTitle()%><option>
                                <%
                                    }
                                %>
                        </select>
                    </td>
                </tr>
                <!--<tr><td>Price:</td><td><input size="23" type="text" name="price"></td></tr>-->
                <tr><td>Amount:</td><td><input size="23" type="text" name="amount" oninput="value=value.replace(/[^\d]/g,'')"></td></tr>
                <tr><td></td>
                    <td>
                        <input class="button" type="submit" value="Add"> 
                        &nbsp; 
                        <a href="/order/history" class="button">Order List</a>
                    </td>
                </tr>
            </table>
        </form>

        <jsp:include page="/ConnServlet" flush="true" />

    </body>

    <script type="text/javascript">


        var errori = '<%=request.getParameter("error")%>';
        var stock = '<%=request.getParameter("stock")%>';
        if (errori == 'yes') {
            alert("Stock is insufficient, please select again!Stock have :" + stock);
        }

        var errori = '<%=request.getParameter("user")%>';
        if (errori == 'yes') {
            alert("You must log in!");
        }
    </script>
</html>
