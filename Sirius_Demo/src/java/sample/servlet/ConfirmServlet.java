package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Suzy
 */
public class ConfirmServlet extends HttpServlet {

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
            String fullname = request.getParameter("txtFullname");
            String admin = request.getParameter("roles");
            boolean roles = false;
            if (admin.equals("True")) {
                roles = true;
            }
            String searchValue = request.getParameter("lastSearchValue");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update</title>");            
            out.println("</head>");
            out.println("<body>");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username1 = "";
                for (int i = 0; i < cookies.length; i++) {
                    username1 = cookies[i].getName();
                }
                out.println("<font color='red'>Welcome, " + username1 + "</font>");
            }
            out.println("<h1>Update</h1>");
            out.println("<form action='CenterServlet'>");
            out.println("Username: "
                    + username
                    + "<input type='hidden' name='txtUsername' value='"
                    + username
                    + "' /><br/>");
            out.println("Password: "
                    + "<input type='text' name='txtPassword' value='"
                    + password
                    + "' /><br/>");
            out.println("Fullname: "
                    + "<input type='text' name='txtFullname' value='"
                    + fullname
                    + "' /><br/>");
            if (roles) {
                out.println("Roles: <input type='checkbox' name='chkAdmin' value='ADMIN' checked='checked' /><br/>");
            } else {
                out.println("Roles: <input type='checkbox' name='chkAdmin' value='ADMIN' /><br/>");
            }
            out.println("<input type='submit' value='OK' name='btAction' "
                    + "onclick=\"javascript:return confirm('Are you sure you want to update this user?')\" />");
            out.println("<input type='hidden' name='txtName' value='"
                    + searchValue
                    + "'/>");
            out.println("<input type='submit' value='Cancel' name='btAction' />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
