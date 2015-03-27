/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Application.User;
import Data.MySQLUser;
import Data.SoulDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kohsuke.github.GitHub;

/**
 *
 * @author weston
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {

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
           
            //get the github instance
            GitHub github = (GitHub) request.getSession().getAttribute("github");

            //create DAO 
            SoulDao db = new MySQLUser();

            //create the username
            User user = db.getUser(github.getMyself().getLogin());
            //if the user is in the database
            if (user != null) {
                //calculate the top three languages
                try {
                    user.calcTopThreeLangs(github);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                //close the connection
                db.close();
                
                //Set user attribute
                request.setAttribute("user", user);
                request.getSession().setAttribute("user", user);
                
                //forward
                request.getRequestDispatcher("profile.jsp").forward(request, response);   
                
            } else {                
                //Set github attribute
                request.setAttribute("github", github);
                
                //get the user
                user = db.getUser(github.getMyself().getLogin());
                
                //Set user attribute
                request.setAttribute("user", user);
                request.getSession().setAttribute("user", user);
                
                //forward
                request.getRequestDispatcher("edit-profile.jsp").forward(request, response);   
            }
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
