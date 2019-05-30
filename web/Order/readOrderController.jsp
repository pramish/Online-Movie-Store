<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.Instant"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MODEL.Order"%>
<%@page import="MODEL.User"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%
    String orderId = request.getParameter("orderId");
    String orderDate = request.getParameter("orderDate");
    if (orderId == null || orderId == "" || orderId.equals("")) {
        response.sendRedirect("history.jsp?error=yes");
        return;
    }
    if (orderDate == null || orderDate == "" || orderDate.equals("")) {
        response.sendRedirect("history.jsp?error=yes");
        return;
    }
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate date = LocalDate.parse(orderDate, fmt);

    DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
    List<Order> orderlist = new ArrayList();
    Order order = manager.findOrder(orderId, date);
    if (order != null) {
        orderlist.add(order);
        session.setAttribute("orderlist", orderlist);
        response.sendRedirect("history.jsp?success=Order Found");
    } else {
        response.sendRedirect("history.jsp?searchNull=yes");
        return;
    }
%>