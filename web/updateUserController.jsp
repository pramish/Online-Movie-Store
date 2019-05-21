<%@page import="MODEL.User"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%

    String userEmail = (String) request.getParameter("email");
    String updated = (String) request.getParameter("updated");
    String email = (String) request.getParameter("email");
    String name = (String) request.getParameter("name");
    String password = (String) request.getParameter("password");
    String phone = (String) request.getParameter("phoneNumber");
    DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
    User newUser = manager.getUser(userEmail);

   if(newUser!=null){
       manager.updateUser(email, name, password, phone);
       session.setAttribute("user", newUser);
       response.sendRedirect("updateUser.jsp?success=User updated");
   }
   else{
    response.sendRedirect("updateUser.jsp?failure= User didn't update");
   }


%>