<%@page import="MODEL.User"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%

    String userID = (String) request.getParameter("ID");
    DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
    User newUser = manager.getUserByID(userID);
    if (newUser != null) {
        session.setAttribute("user", newUser);
        response.sendRedirect("/UserManagement/updateUser.jsp?success=User Found.");
    } else {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/UserManagement/updateUser.jsp?failure= User not found.");
    }


%>