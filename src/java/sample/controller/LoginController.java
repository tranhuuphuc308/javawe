/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.UserDAO;
import sample.dtos.UserDTO;

/**
 *
 * @author ROG STRIX
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

   private static final String ERROR="error.jsp";
   private static final String ADMIN_PAGE="admin.jsp";
   private static final String USER_PAGE="user.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //**
            HttpSession session = request.getSession();
            String userID=request.getParameter("userID");
            String password =request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO user = dao.checkLogin(userID, password);
            if(user!=null){
                //**
                session.setAttribute("LOGIN_USER", user);
                String roleID = user.getRoleID();
                if(roleID.equals("AD")){
                    url=ADMIN_PAGE;                   
                }else if(roleID.equals("US")){
                    url=USER_PAGE;
                }else{
                    session.setAttribute("ERROR_MESSAGE", "Wrong Role!!");
                }
            }else{
                session.setAttribute("ERROR_MESSAGE", "Wrong id vs pass!!");
            }
        } catch (Exception e) {
            log("Error at LoginController");
        }finally{
            response.sendRedirect(url);
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
