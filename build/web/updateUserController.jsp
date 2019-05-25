<%@page import="MODEL.User"%>
<%@page import="MODEL.DAO.DatabaseManager"%>
<%

    String userID = (String) request.getParameter("ID");
    String save = (String) request.getParameter("save");
    DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
    User newUser = manager.getUserByID(userID);
    if (newUser != null) {
        session.setAttribute("user", newUser);
        response.sendRedirect("updateUser.jsp?success=User Found.");
        if (save != null) {

            String email = (String) request.getParameter("email");
            String name = (String) request.getParameter("name");
            String password = (String) request.getParameter("password");
            String phone = (String) request.getParameter("phoneNumber");
            String status = (String) request.getParameter("deactive");
            if (status != null) {
                User user = (User) session.getAttribute("user");
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                user.setPhoneNumber(phone);
                user.setStatus(status);
                manager.updateUser(userID, email, name, password, phone, status);
                session.setAttribute("user", user);
                response.sendRedirect("updateUser.jsp?success1=User Updated.");
            }
            else{
                User user = (User) session.getAttribute("user");
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                user.setPhoneNumber(phone);
                user.setStatus(status);
                manager.updateUser(userID, email, name, password, phone, "Active");
                session.setAttribute("user", user);
                response.sendRedirect("updateUser.jsp?success1=User Updated.");
            }
        }
    } else {
        response.sendRedirect("updateUser.jsp?failure= User not found.");
    }


%>