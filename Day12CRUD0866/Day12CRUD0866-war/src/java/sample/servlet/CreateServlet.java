/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.session.RegistrationSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class CreateServlet extends HttpServlet {

    private final String loginPage = "login.html";
    private final String registerPage = "register.jsp";

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String lastname = request.getParameter("txtLastname");
            boolean bErr = false;
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                bErr = true;
                request.setAttribute("usernameErr", "Username must 6-20 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                bErr = true;
                request.setAttribute("passwordErr", "Password must 6-20 chars");
            }
            if (!confirm.equals(password)) {
                bErr = true;
                request.setAttribute("confirmErr", "Password and Confirm must be same");
            }
            if (lastname.trim().length() < 5 || lastname.trim().length() > 30) {
                bErr = true;
                request.setAttribute("lastnameErr", "Lastname must 5-30 chars");
            }
            if (!bErr) {
                try {
                    Context context = new InitialContext();
                    Object obj = context.lookup("RegJNDI");
                    RegistrationSessionBeanRemote poji = (RegistrationSessionBeanRemote) obj;
                    boolean result = poji.insertUser(username, password, lastname, false);
                    if (!result) {
                        request.setAttribute("usernameDup", username + " da ton tai!");
                    } else {
                        response.sendRedirect(loginPage);
                        return;
                    }
                } catch (NamingException ex) {
                    log(ex.getMessage());
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(registerPage);
            rd.forward(request, response);
        } finally {
            out.close();
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
