package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.registration.RegistrationDAO;
import sample.registration.RegistrationErrors;

/**
 *
 * @author Suzy
 */
public class AddUserServlet extends HttpServlet {
    private final String errorPage = "ProcessErrorCreateServlet";
    private final String loginpage = "login.html";
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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String lastname = request.getParameter("txtLastname");
            
            boolean bErr = false;
            RegistrationErrors errors = new RegistrationErrors();
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                bErr = true;
                errors.setUsernameErr("Username phai tu 6 den 20 ky tu");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                bErr = true;
                errors.setPasswordErr("Password phai tu 6 den 30 ky tu");
            } else {
                if (!password.trim().equals(confirm.trim())) {
                    bErr = true;
                    errors.setConfirmNotMatch("Password va Confirm phai giong nhau");
                }
            }
            if (lastname.trim().length() < 2 || lastname.trim().length() > 40) {
                bErr = true;
                errors.setLastnameErr("Lastname phai tu 2 den 40 ky tu");
            }
            
            String url = errorPage;
            if (bErr) {
                request.setAttribute("ERROR", errors);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.addNewUser(username, password, lastname, false);
                
                if (result) {
                    url = loginpage;
                } else {
                    errors.setUsernameDup(username + " da ton tai");
                    request.setAttribute("ERROR", errors);
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
