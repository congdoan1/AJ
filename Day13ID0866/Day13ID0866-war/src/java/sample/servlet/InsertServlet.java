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
import sample.session.AccountSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class InsertServlet extends HttpServlet {

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
            String salaryStr = request.getParameter("txtSalary");
            boolean bErr = false;
            if (username.trim().length() < 6 || username.trim().length() > 50) {
                bErr = true;
                request.setAttribute("usernameErr", "Username 6-50 char!!!");
            }
            if (salaryStr.trim().length() <= 0) {
                bErr = true;
                request.setAttribute("salaryErr", "Please insert Salary!!!");
            } else {
                boolean slry = false;
                try {
                    float salary = Float.parseFloat(salaryStr);
                    if (salary < 0) {
                        slry = true;
                    }
                } catch (NumberFormatException ex) {
                    slry = true;
                }
                if (slry) {
                    bErr = true;
                    request.setAttribute("salaryFor", "Salary is not correct format!!!");
                }
            }
            if (bErr) {
                RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
                rd.forward(request, response);
            } else {
                try {
                    Context context = new InitialContext();
                    Object obj = context.lookup("AccJNDI");
                    AccountSessionBeanRemote poji = (AccountSessionBeanRemote) obj;
                    boolean result = poji.insertAccount(username, Float.parseFloat(salaryStr));
                    if (result) {
                        response.sendRedirect("insert.jsp");
                    } else {
                        out.println(username + " da ton tai!!!");
                    }
                } catch (NamingException ex) {
                    ex.printStackTrace();
                }
            }
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
