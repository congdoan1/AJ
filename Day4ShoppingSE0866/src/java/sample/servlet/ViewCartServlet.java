package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.obj.CartObj;

/**
 *
 * @author Suzy
 */
public class ViewCartServlet extends HttpServlet {

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
            out.println("<title>Cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Your Cart includes</h1>");
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    HashMap<String, Integer> items = cart.getItems();
                    out.println("<table border='1'>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>No</th>");
                    out.println("<th>Title</th>");
                    out.println("<th>Quantity</th>");
                    out.println("<th>Action</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");

                    int count = 0;
                    Iterator iter = items.entrySet().iterator();
                    out.println("<form action='CenterServlet'");
                    while (iter.hasNext()) {
                        Map.Entry item = (Map.Entry) iter.next();                        
                        out.println("<tr>");
                        out.println("<td>" + ++count + "</td>");
                        out.println("<td>"
                                + item.getKey()
                                + "</td>");
                        out.println("<td>"
                                + "<input type='text' name='txtQuantity' value='"
                                + item.getValue()
                                + "' />"
                                + "</td>");
                        out.println("<td><input type='checkbox' name='chkItem' value='"
                                + item.getKey()
                                + "' /></td>");
                        out.println("</tr>");
                    }
                    out.println("<tr>");
                    out.println("<td colspan='3'><a href='bookStore.html'>Add More Item</a></td>");
                    out.println("<td><input type='submit' value='Remove Item' name='btAction' /></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td colspan='3' style='text-align: center'><input type='submit' value='Checkout' name='btAction' /></td>");
                    out.println("<td><input type='submit' value='Update Cart' name='btAction' /></td>");
                    out.println("</tr>");
                    out.println("</form>");
                    out.println("</tbody>");
                    out.println("</table>");
                }
            }
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
