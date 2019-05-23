
<%@page import="MODEL.controller.Validator"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%@page contentType="text/html" import="java.util.*" import="MODEL.*" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
    Validator valid = new Validator();
    DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");

    String name = (String) request.getParameter("fullName");
    String password = (String) request.getParameter("password");
    String email = (String) request.getParameter("email");
    String phoneNumber = (String) request.getParameter("phoneNumber");
    int key = (new Random()).nextInt(999999);
    String ID = "" + key;
    if (manager.checkEmail(email)) {
        response.sendRedirect("index.jsp?failure1=User is already registered..");
    }
    if (!manager.checkEmail(email)) {
        manager.addUser(email, name, password, phoneNumber,ID);
        response.sendRedirect("index.jsp?success1=Registration Completed.");
    }
%>



 