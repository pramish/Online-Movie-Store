<%@page import="MODEL.User"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%
    String email = request.getParameter("email");
    String phoneNumber = request.getParameter("phoneNumber");
    DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
    if (manager.findUser(email, phoneNumber) != null) {
        User user1 = manager.getUser(email);
        session.setAttribute("user1", user1);
        response.sendRedirect("listUser.jsp?success=User Found");
    } else {
        response.sendRedirect("listUser.jsp?failure=User not found");
        request.getSession().removeAttribute("user1");
    }
%>