<%@page import="MODEL.User"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%@page import="MODEL.DAO.Database"%>
<%
    String name = (String) request.getParameter("fullName");
    String password = (String) request.getParameter("password");
    String email = (String) request.getParameter("email");
    String phoneNumber = (String) request.getParameter("phoneNumber");
    DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
    User user = new User(email, name, password, phoneNumber);
    if(manager != null)
    {manager.addUser(email, name, password, phoneNumber);
    }
session.setAttribute("user", user);
    response.sendRedirect("index.jsp?success=Registration Sucessful");
%>