/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import Data.MySQLDao;

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

                //store a mapping of languages to bytes
                Map<String, Integer> allLangs = new HashMap<>();

                //get all repositories for the user
                Map<String, GHRepository> repositories = github.getMyself().getRepositories();

                //loop through each repository
                for (String repo : repositories.keySet()) {

                    //get languages specific to repository mapped to bytes written in languages
                    Map<String, Integer> languages = repositories.get(repo).listLanguages();

                    //loop through languages in repository
                    for (String lang : languages.keySet()) {

                        //if the global language map contains the key
                        if (allLangs.containsKey(lang)) {
                            //add the value for this repository to the total bytes written
                            allLangs.replace(lang, languages.get(lang) + allLangs.get(lang));
                        } else {
                            //just add the number of bytes written
                            allLangs.put(lang, languages.get(lang));
                        }
                    }
                }

                //get a reverse of the map
                Map<Integer, String> langsForSort = new HashMap<>();

                for (Map.Entry<String, Integer> entry : allLangs.entrySet()) {
                    langsForSort.put(entry.getValue(), entry.getKey());
                }


                //get the top languages
                ArrayList<String> topLangs = new ArrayList<>();
                SortedSet<Integer> keys = new TreeSet<Integer>(langsForSort.keySet()).descendingSet();
                for (Integer key : keys) {
                    if (langsForSort.get(key) != "HTML" && langsForSort.get(key) != "CSS") {
                        topLangs.add(langsForSort.get(key));
                    }
                }
                
                //set the top language
                if (topLangs.size() > 0) {
                    newUser.setFirst_language(topLangs.get(0));
                }
                
                 //set the second language
                if (topLangs.size() > 1) {
                    newUser.setSecond_language(topLangs.get(1));
                }
                
                 //set the third language
                if (topLangs.size() > 2) {
                    newUser.setThird_language(topLangs.get(2));
                }
                
                //create a DAO
                MySQLDao dao = new MySQLDao();

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
