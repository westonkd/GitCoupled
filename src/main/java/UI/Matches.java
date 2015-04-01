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
import java.util.Map;
import java.util.Set;
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
@WebServlet(name = "Matches", urlPatterns = {"/Matches"})
public class Matches extends HttpServlet {

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
        User user = (User) request.getSession().getAttribute("user");
        SoulDao dao = new MySQLUser();
        int page = 1;
        String pageParam = request.getParameter("page");
        
        //get the page number
        if (pageParam != null)
        {
            page = Integer.parseInt(pageParam);
        }

        if (github != null && user != null) {
           //get the matches
            Map<Integer, Set<User>> matches = dao.getMatchesWithScores(user, page);
            int numPages = dao.getNumPages(user);
            
            //set the attribute and pass to jsp
            request.setAttribute("matches", matches);
            request.setAttribute("numPages", numPages);
            request.setAttribute("page", page);
            
            
            request.getRequestDispatcher("matches.jsp").forward(request, response);
        } else {
            response.sendRedirect("SignIn");
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
