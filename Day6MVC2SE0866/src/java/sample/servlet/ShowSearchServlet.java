package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.registration.RegistrationDTO;

/**
 *
 * @author Suzy
 */
public class ShowSearchServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Result</title>");
            out.println("</head>");
            out.println("<body>");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                String username = "";
                for (int i = 0; i < cookies.length; i++) {
                    username = cookies[i].getName();
                }
                out.println("<font color='red'>Welcome, " + username + "</font>");
            }
            String name = request.getParameter("txtName");
            out.println("<h1>Search Result of " + name + "</h1>");
            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>No.</th>");
            out.println("<th>Username</th>");
            out.println("<th>Password</th>");
            out.println("<th>Lastname</th>");
            out.println("<th>Role</th>");
            out.println("<th>Delete</th>");
            out.println("<th>Update</th>");
            out.println("<th>Delete</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("LIST");
            for (int i = 0; i < result.size(); i++) {
                out.println("<form action='CenterServlet'>");
                RegistrationDTO dto = result.get(i);
                out.println("<tr>");
                out.println("<td>" + (i + 1) + "</td>");
                out.println("<td>"
                        + dto.getUsername()
                        + "<input type='hidden' name='txtUsername' value='"
                        + dto.getUsername() + "' />"
                        + "</td>");
                out.println("<td>"
                        + "<input type='text' name='txtPassword' value='"
                        + dto.getPassword() + "' />"
                        + "</td>");
                out.println("<td>" + dto.getLastname() + "</td>");
                if (dto.isRoles()) {
                    out.println("<td>"
                            + "<input type='checkbox' name='chkAdmin' value='ADMIN' checked='checked' />"
                            + "</td>");
                } else {
                    out.println("<td>"
                            + "<input type='checkbox' name='chkAdmin' value='ADMIN' />"
                            + "</td>");
                }
                String urlRewritting = "CenterServlet?btAction=Delete&txtUsername=" + dto.getUsername() + "&lastSearchValue=" + name;
                out.println("<td><a href='"
                        + urlRewritting + "'>Delete</a></td>");
                out.println("<td>"
                        + "<input type='submit' value='Update' name='btAction' />"
                        + "<input type='hidden' name='lastSearchValue' value='"
                        + name + "' />"                        
                        + "</td>");
                out.println("<td>"
                        + "<input type='submit' value='Delete' name='btAction' />"
                        + "</td>");
                out.println("</tr>");
                out.println("</form>");
            }

            out.println("</tbody>");
            out.println("</table>");
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
