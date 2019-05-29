/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.usermanagement;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pramishluitel
 */
@WebServlet(name = "updateUser", urlPatterns = {"/updateUser"})
public class updateUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get user from querystring
//        String id = request.getParameter("id");
//        
//        HttpSession session = request.getSession();
//        
//        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
//        
//        
//        User user = null;
//        try {
//            user = manager.getUserByID(id);
//        } catch (SQLException ex) {
//            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        if(user == null){
//            response.sendRedirect("/users");
//        }else{
//            
//        // Set editMyAccountUser as user
//        session.setAttribute("editUser", user);
        RequestDispatcher view = request.getRequestDispatcher("/UserManagement/updateUser.jsp");
        view.forward(request, response);
//    }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String save = (String) request.getParameter("save");
        DatabaseManager manager = (DatabaseManager) session.getAttribute("manager");
        if (save != null) {

            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String status = request.getParameter("status");
            System.out.println("My  first status is askdfgksgf" + status);
            if (status.equals("Deactive")) {
                System.out.println("My  first status is " + status);
                User user = (User) session.getAttribute("user");
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                user.setPhoneNumber(phone);
                user.setStatus(status);
                try {
                    manager.updateUser(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
                    User pramish = new User(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
                    session.setAttribute("user", pramish);
                    response.sendRedirect("/index.jsp?success1=User Updated.");
                    request.getSession().removeAttribute("user");
                } catch (SQLException ex) {
                    Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (status.equals("Active")) {
                System.out.println("My second status is " + status);
                User user = (User) session.getAttribute("user");
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                user.setPhoneNumber(phone);
                user.setStatus(status);
                try {
                    manager.updateUser(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
                    User pramish = new User(user.getID(), user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber(), user.getStatus());
                    session.setAttribute("user", pramish);
                    response.sendRedirect("/index.jsp?success1=User Updated.");
                    request.getSession().removeAttribute("user");
                } catch (SQLException ex) {
                    Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
