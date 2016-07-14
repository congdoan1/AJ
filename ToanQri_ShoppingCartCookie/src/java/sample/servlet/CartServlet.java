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
public class CartServlet extends HttpServlet {

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
            out.println("<title>Shopping Cart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Shopping Cart</h1>");
            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>No.</th>");
            out.println("<th>Book Title</th>");
            out.println("<th>Quantity</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            out.println("<form action='CenterServlet'>");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                int count = 1;
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    int tmp = Integer.parseInt(cookie.getValue());
                    if (tmp > 0) {
                        out.println("<tr>");
                        out.println("<td>" + count++ + "</td>");
                        out.println("<td>"
                                + cookie.getName()
                                + "</td>");
                        out.println("<td>"
                                + cookie.getValue()
                                + "</td>");
                        out.println("<td>"
                                + "<input type='checkbox' name='rmv' value='"
                                + cookie.getName()
                                + "' />"
                                + "</td>");
                        out.println("</tr>");
                    }
                }
            }
            out.println("<tr>");
            out.println("<td colspan='3'><a href='shopping.html'>Add More Cart</a></td>");
            out.println("<td><input type='submit' value='Remove Cart' name='btAction'/></td>");
            out.println("</tr>");
            
            out.println("</form>");

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
