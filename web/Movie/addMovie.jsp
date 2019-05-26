<%-- 
    Document   : addMovie
    Created on : 19/05/2019, 4:21:04 PM
    Author     : luckylau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Movie</title>
    </head>
    <body onload="startTime()">
        <div><span class="time" id="time" ></span></div>
        <h1>Enter the movie details:</h1> 
        <br>
        <form action="/movie/add" method="post">
            <table>
                <tr><td>Title:</td><td><input size="23" type="text" name="title"></td></tr>
                <tr><td>Genre:</td><td><input size="23" type="text" name="genre"></td></tr>
                <tr><td>Price</td><td><input size="23" type="text" name="price"></td></tr>
                <tr><td>Stock:</td><td><input size="23" type="text" name="stock"></td></tr>  
                <tr><td></td>
                    <td>
                        <input class="button" type="submit" value="Add"> 
                        &nbsp; 
                        <a href="/movie/list" class="button">Movie List</a>
                        <button class="button" type="button" onclick="location.href = 'catalogue.jsp'" > Movie List </button>
                    </td>
                </tr>
            </table>
        </form>
        <jsp:include page="/ConnServlet" flush="true" />
        
    </body>
</html>
