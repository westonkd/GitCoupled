/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Data.HttpConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.egit.github.core.client.GitHubClient;

/**
 *
 * @author weston
 */
@WebServlet(name = "CallBack", urlPatterns = {"/CallBack"})
public class CallBack extends HttpServlet {

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
        
        //if there is a oAuth token in the request
        if (request.getParameter("code") != null) {
            
            //create the user-specific instance of a Github
            String clientID = "5459711ee0c7fc3e4b02";
            String clientSecret = "68dc76a5000249b5c83ae5deca0b8108cc0c0954";
            String token = request.getParameter("code");
            
            String accessRequest = "https://github.com/login/oauth/access_token";
            String parameters = "client_id=" + clientID + "&client_secret=" + clientSecret + "&code=" + token;
            
            HttpConnection postRequest = new HttpConnection(accessRequest, parameters);
            try {
                response.getWriter().write(postRequest.sendPost());
                
                
                //get the access token
                //response.sendRedirect(accessRequest);
                
                //GitHubClient client = new GitHubClient(accessRequest);
                
                //client = client.setOAuth2Token(token);
            } catch (Exception ex) {
                response.getWriter().write("fail");
            }
           
            
            
            //add the Github as a session object
            //request.getSession().setAttribute("github", client);
            //request.getSession().setAttribute("token", token);
            

            //forward to the profile page
           // response.sendRedirect("PersonalProfile");
            return;
        } else {
            //redirect home
            response.sendRedirect("index.jsp");
            return;
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
