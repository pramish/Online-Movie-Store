<%@page contentType="text/html" import="java.util.*" import="MODEL.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    List<Order> orderlist = (ArrayList) session.getAttribute("orderlist");
%>

<html>
    <head>
        <title>OMS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="/css/style.css" rel="stylesheet" type="text/css"/>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <style type="text/css">

            #table{
                border:1px solid #ccc;
                border-collapse:collapse;
                width:1000px;}
            #table tr{height:30px;}
            #table tr:nth-child(2n){background-color:#eee;}
            #table tr th, td{border:1px solid #ccc;text-align: center;

            }
            td a{
                color:red;
            }
            .popDiv{
                width:500px;
                border:1px solid purple;
                position:absolute;
                top:50%;left:50%;
                margin-left:-250px;
                margin-top:-100px;
                background:#fff;
                padding:10px;
                display:none;
                z-index:3;
                border:1px solid #ccc;
            }
            .popDiv p{border:1px solid #ccc;padding:5px;}
            .modifyDiv{
                width:500px;
                border:1px solid purple;
                position:absolute;
                top:50%;left:50%;
                margin-left:-250px;
                margin-top:-100px;
                background:#fff;
                padding:10px;
                display:none;
                z-index:3;
                border:1px solid #ccc;
            }
            .modifyDiv p{border:1px solid #ccc;padding:5px;}

        </style>
    </head>
    <body>
        <h1>OrderCatalogue</h1>
        <nav class="navbar navbar-light" style="background-color: #e3f2fd;">
            <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="location.href = '/order/create'">Add order</button>
            <div style="text-align: right;">
                <form class="form-inline my-2 my-lg-0" action="/Order/readOrderController.jsp" method="post">
                    <input class="form-control mr-sm-2" type="search" name="orderId" placeholder="enter orderId">
                    <input type="date"  name="orderDate" id="orderDate" placeholder="enter orderdate" />
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav> 


        <table id="table">
            <tr>
                <td>OrderID</td>
                <td>MovieID</td>
                <td>Title</td>
                <td>Price</td>
                <td>Amount</td>
                <td>TotalPrice</td>
                <td>Status</td>
                <td >Date</td>
                <td >Operation</td>
            </tr>

            <%
                for (Order m : orderlist) {

            %>
            <tr>
                <td param-id="id" class="id"><%=m.getID()%></td>
                <td param-id="movieId"><%=m.getMovieID()%></td>
                <td><%=m.getTitle()%></td>
                <td><%=m.getPrice()%></td>
                <td param-id="amount"><%=m.getAmount()%></td>
                <td><%=m.getTotalPrice()%></td>
                <td><%=m.getStatus()%></td>
                <td><%=m.getDate()%></td>
                <td><a href="###" class="view">view</a>
                    <a href="#" class="del" onClick="delOrder('<%=m.getID()%>', '<%=m.getStatus()%>')" href="javascript:void(0);">delete</a>
                    <a href="javascript:void(0)" class="modify">update</a>
                </td>
            </tr>
            <%

                }
            %>

        </table>
        <div class="popDiv">
            <p><strong>OrderID: </strong><span></span></p>
            <p><strong>MovieID: </strong><span></span></p>
            <p><strong>Title </strong><span></span></p>
            <p><strong>Price: </strong><span></span></p>
            <p><strong>Amount: </strong><span></span></p>
            <p><strong>TotalPrice: </strong><span></span></p>
            <p><strong>Status: </strong><span></span></p>
            <p><strong>Date </strong><span></span></p>
            <a href="#" class="close">Close</a>
        </div>
        <div class="modifyDiv">
            <form action="/order/update" method="POST" id="testForm">
                <p><strong>OrderID: </strong><input type="text" name="orderId" id="orderId" disabled ="disabled "><span></span></p>
                <p><strong>MovieID: </strong><input type="text" name="movieId" id="movieId" disabled ="disabled "><span></span></p>
                <p><strong>New Amount: </strong><input type="text" name="amount"><span></span></p>
                <button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="updateSubmit()">Submit</button>
                <a href="javascript:void(0)" class="close">Cancel</a>
            </form>
        </div>

        <script type="text/javascript">
            function updateSubmit() {
                $('.modifyDiv input[name = "orderId"]').attr('disabled', false);
                $('.modifyDiv input[name = "movieId"]').attr('disabled', false);

                $('.modifyDiv form').submit();
                $('.modifyDiv input[name = "orderId"]').attr('disabled', 'disabled');
                $('.modifyDiv input[name = "movieId"]').attr('disabled', 'disabled');

            }
            $(function () {
                var tds = $("td").not(":last-child");
                tds.dblclick(tdclick);
                $("td a.view").click(function () {
                    var maskHeight = $(document).height();
                    var maskWidth = $(document).width();
                    $("<div class='mask'></div>").appendTo($("body"));
                    $("div.mask").css({
                        "opacity": 0.4,
                        "background": "#000",
                        "position": "absolute",
                        "left": 0,
                        "top": 0,
                        "width": maskWidth,
                        "height": maskHeight,
                        "z-index": 2
                    })
                    var arr = [];
                    $(this).parent().siblings().each(function () {
                        arr.push($(this).text());
                    });
                    $(".popDiv").show().children().each(function (i) {
                        $(this).children("span").text(arr[i]);
                    });
                    $(".close").click(function () {
                        $(this).parent().hide();
                        $(".mask").remove();
                    });
                });

                $("td a.modify").click(function () {
                    var amount = $(this).parent('td').siblings('td[param-id="amount"]').html();
                    var id = $(this).parent('td').siblings('td[param-id="id"]').html();
                    var movieId = $(this).parent('td').siblings('td[param-id="movieId"]').html();
                    var maskHeight = $(document).height();
                    var maskWidth = $(document).width();
                    $("<div class='mask'></div>").appendTo($("body"));
                    $("div.mask").css({
                        "opacity": 0.4,
                        "background": "#000",
                        "position": "absolute",
                        "left": 0,
                        "top": 0,
                        "width": maskWidth,
                        "height": maskHeight,
                        "z-index": 2
                    })
                    var arr = [];
                    $(this).parent().siblings().each(function () {
                        arr.push($(this).text());
                    });
                    $(".modifyDiv").show();
                    $(".modifyDiv input[name='orderId']").val(id);
                    $(".modifyDiv input[name='movieId']").val(movieId);
                    $(".modifyDiv input[name='amount']").val(amount);

                    $(".close").click(function () {
                        $('.modifyDiv').hide();
                        $(".mask").remove();
                    });
                });
            })

            function tdclick() {
                var clickfunction = this;
                var td = $(this);
                var text = $(this).text();
                td.html("");
                var input = $("<input>");
                input.attr("value", text);
                input.keyup(function (event) {
                    var myEvent = event || window.event;
                    var kcode = myEvent.keyCode;
                    if (kcode == 13) {
                        var inputnode = $(this);
                        var inputext = inputnode.val();
                        var tdNode = inputnode.parent();
                        tdNode.html(inputext);
                        tdNode.click(tdclick);
                    }
                }).blur(function () {
                    var inputnode = $(this);
                    var inputext = inputnode.val();
                    var tdNode = inputnode.parent();
                    tdNode.html(inputext);
                    tdNode.click(tdclick);
                });
                td.append(input);
                var inputdom = input.get(0);
                inputdom.select();
                td.unbind("click");
            }

            function delOrder(orderId, status)
            {
                if (status == 'CANCELLED') {
                    alert('Congratulations, order has been completed!');
                } else {
                    $(this).parents("tr").remove();
                    var contex = "<%=request.getContextPath()%>";
                    window.location.href = contex + "/order/cancel?orderId=" + orderId;
                }
            }

            //取出传回来的参数error并与yes比较
            var errori = '<%=request.getParameter("error")%>';
            if (errori == 'yes') {
                alert("Search Valus Can not be Null");
            }
            var errori = '<%=request.getParameter("searchNull")%>';
            if (errori == 'yes') {
                alert("Search Result Is Null");
            }
            var updateError = '<%=request.getParameter("updateError")%>';
            var stock = '<%=request.getParameter("stock")%>';
            if (updateError == 'yes') {
                alert("Stock is insufficient, please select again!Stock have :" + stock);
            }
            $("#orderDate").change(function () {
                $("#orderDate").attr("value", $(this).val());
            });

//
//            $("#amount").change(function () {
//                $("#orderId").attr("value", $(this).val());
//                $("#movieId").attr("value", $(this).val());
//            });
        </script>
</html>
