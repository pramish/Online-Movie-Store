<%@page import="MODEL.DAO.DatabaseManager"%>
<%
    String email = (String) request.getParameter("email");
    DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
    if (!manager.checkEmail(email)) {
        response.sendRedirect("index.jsp?failure=User Not found");
    }
    if (manager.checkEmail(email)) {
        manager.deleteUser(email);
        request.getSession().removeAttribute("user1");
        response.sendRedirect("index.jsp?success=User successfully deleted.");
    }
%>
