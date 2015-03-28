/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Data.MessageDao;
import Data.MySQLMessage;
import Data.MySQLUser;
import Data.SoulDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author richard
 */
@WebServlet(name = "CreateNewMessage", urlPatterns = {"/CreateNewMessage"})
public class CreateNewMessage extends HttpServlet {

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
        
        // Get the paramaters that are pased by the HTML form
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");
        String sentTo = request.getParameter("userTo");
        String sentFrom = request.getParameter("userFrom");
        String callback = request.getParameter("callback");
          
        // Get the connection object
        SoulDao DB = new MySQLUser();
        
        // get the user IDs for users
        int sent_from = DB.getUserId(sentFrom);
        int received_by = DB.getUserId(sentTo);       
        
        MessageDao newMySQLMessage = new MySQLMessage();
       
        // Create new message
        Message newMessage = new Message(subject, body, sent_from, received_by);
        // Save message
        
        PrintWriter out = response.getWriter();
        out.println(newMySQLMessage.printStatement(newMessage));
        
        newMySQLMessage.saveMessage(newMessage);
        // Send user back to page they were privously on
        request.getRequestDispatcher(callback).forward(request, response);
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
