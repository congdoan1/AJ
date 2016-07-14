package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.account.AccountDTO;

/**
 *
 * @author Suzy
 */
public class ShowResult extends HttpServlet {

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
            out.println("<title>Search Result</title>");
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
            out.println("<h1>Show Search Result of " + name + "</h1>");
            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>No.</th>");
            out.println("<th>Username</th>");
            out.println("<th>Password</th>");
            out.println("<th>Lastname</th>");
            out.println("<th>Admin</th>");
            out.println("<th>Delete</th>");
            out.println("<th>Update</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            List<AccountDTO> result = (List<AccountDTO>) request.getAttribute("RESULT");
            for (int i = 0; i < result.size(); i++) {
                out.println("<form action='CenterServlet'>");
                out.println("<tr>");
                out.println("<td>" + (i + 1) + "</td>");
                out.println("<td>"
                        + result.get(i).getUsername()
                        + "<input type='hidden' name='txtUsername' value='"
                        + result.get(i).getUsername() + "' />"
                        + "</td>");
                out.println("<td>"
                        + result.get(i).getPassword()
                        + "<input type='hidden' name='txtPassword' value='"
                        + result.get(i).getPassword()
                        + "' />"
                        + "</td>");
                out.println("<td>"
                        + result.get(i).getFullname()
                        + "<input type='hidden' name='txtFullname' value='"
                        + result.get(i).getFullname()
                        + "' />"
                        + "</td>");
                if (result.get(i).isIsAdmin()) {
                    out.println("<td>"
                            + "<input type='checkbox' name='chkAdmin' value='ADMIN' checked='checked' disabled='disabled'/>"
                            + "<input type='hidden' name='roles' value='True' />"
                            + "</td>");
                } else {
                    out.println("<td>"
                            + "<input type='checkbox' name='chkAdmin' value='ADMIN' disabled='disabled'/>"
                            + "<input type='hidden' name='roles' value='False' />"
                            + "</td>");
                }
                out.println("<td>"
                        + "<input type='submit' value='Delete' name='btAction' "
                        + "onclick=\"javascript:return confirm('Are you sure you want to delete this user?')\" />"
                        + "<input type='hidden' name='lastSearchValue' value='"
                        + name + "' />"
                        + "</td>");
                out.println("<td>"
                        + "<input type='submit' value='Update' name='btAction' />"
                        + "</td>");
//                String urlRewritting = "CenterServlet?btAction=Delete&txtUsername="+ result.get(i).getUsername() +
//                        "&lastSearchValue=" + name;
//                out.println("<td><a href='"
//                        + urlRewritting + "'>Delete</a></td>");
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
