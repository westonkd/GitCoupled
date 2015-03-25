/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Data.MySQLUser;
import Data.SoulDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

/**
 *
 * @author weston
 */
@WebServlet(name = "CreateNewUser", urlPatterns = {"/CreateNewUser"})
public class CreateNewUser extends HttpServlet {

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
        //get the github object
        GitHub github = (GitHub) request.getSession().getAttribute("github");
        System.out.println("?????????????????????????????????????????????");
        try (PrintWriter out = response.getWriter()) {
            //get the POST variables if they exists
            if (request.getParameter("gender") != null && github != null) {
                String gender = request.getParameter("gender");
                String bio = request.getParameter("bio").replace("\"", "").replace("'", "");
                String quote = request.getParameter("quote").replace("\"", "").replace("'", "");
                int age = Integer.parseInt(request.getParameter("age"));
                String userName = github.getMyself().getLogin();

                //create the user object
                User newUser = new User(gender, age, userName, quote, bio);

                try {
                    newUser.calcTopThreeLangs(github);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //create a DAO
                SoulDao dao = new MySQLUser();

                //add the user to the database
                dao.addUser(newUser);

                //Set user attribute
                request.setAttribute("user", newUser);
                request.setAttribute("github", github);

                //forward
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else {
                response.sendRedirect("index.jsp");
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
