/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oms.controller.UAM;

import MODEL.DAO.DatabaseManager;
import MODEL.User;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jonny
 */
@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class logout extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        DatabaseManager manager = (DatabaseManager)session.getAttribute("manager");
        User user = (User)session.getAttribute("user");
        
        if(user != null){
            if(!"".equals(user.getStatus()))
            {
                try {
                    String logID = ""+((new Random()).nextInt(900000000)+ + 100000000);
                    Date date= new Date();
                    long time = date.getTime();
                    Timestamp isTime = new Timestamp(time);
                    String timeStamp = "" + isTime;
                    String acessType = "LOGOUT";
                    manager.addUserLogs(logID, user.getID(), acessType, timeStamp);
                } catch (SQLException ex) {
                    Logger.getLogger(logout.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            session.removeAttribute("user");
        }
        response.sendRedirect("/");
    }
}
