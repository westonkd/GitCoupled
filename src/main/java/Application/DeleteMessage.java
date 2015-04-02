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
 * @author Weston
 */
@WebServlet(name = "DeleteMessage", urlPatterns = {"/DeleteMessage"})
public class DeleteMessage extends HttpServlet {

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

        try {
            //get the user and callback
            User user = (User) request.getSession().getAttribute("user");
            String callback = request.getParameter("callback");
            
            //get the id of the message to delete
            int messageID = Integer.parseInt(request.getParameter("toDelete"));

            //create a message dao
            MessageDao dao = new MySQLMessage();

            //get the message to be modified
            Message toModify = dao.getMessage(messageID);

            //modify the correct message to no longer be displayed
            toModify.setDisplay(0);

            //update the message
            dao.updateMessage(toModify);    
            
            //update message count
            int count = dao.getNumMesssages(user);
            user.setNumMessages(count);

            // Send user back to page they were privously on
            request.getRequestDispatcher(callback).forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("SignIn");
        }

//        String subject = request.getParameter("subject");
//        String body = request.getParameter("body");
//        String sentTo = request.getParameter("userTo");
//        String sentFrom = request.getParameter("userFrom");
//        String callback = request.getParameter("callback");
//        int in_reply_to = Integer.parseInt(request.getParameter("in_reply_to"));
//        User user = (User) request.getSession().getAttribute("user");
//          
//        // Get the connection object
//        SoulDao DB = new MySQLUser();
//        MessageDao mDao = new MySQLMessage();
//        
//        // get the user IDs for users
//        int sent_from = DB.getUserId(sentFrom);
//        int received_by = DB.getUserId(sentTo);
//       
//        // Create new message
//        Message newMessage = new Message(subject, body, sent_from, received_by, in_reply_to);
//        Message oldMessage = mDao.getMessage(in_reply_to);
//        oldMessage.setDisplay(0);
//        
//        // Save message
//        mDao.saveMessage(newMessage);
//        mDao.updateMessage(oldMessage);
//        

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
