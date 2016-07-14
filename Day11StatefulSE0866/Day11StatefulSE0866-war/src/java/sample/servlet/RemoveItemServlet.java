package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.stateful.CartSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class RemoveItemServlet extends HttpServlet {

    private final String showPage = "show.jsp";

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
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartSessionBeanRemote poji = (CartSessionBeanRemote) session.getAttribute("CART");
                if (poji == null) {
                    try {
                        Context context = new InitialContext();
                        Object obj = context.lookup("CartJNDI");
                        poji = (CartSessionBeanRemote) obj;
                    } catch (NamingException ex) {
                        log(ex.getMessage());
                    }
                }
                if (poji != null) {
                    String[] rmv = request.getParameterValues("chkRemove");
                    if (rmv != null) {
                        for (int i = 0; i < rmv.length; i++) {
                            String title = rmv[i];
                            poji.removeItemFromCart(title);
                        }
                        session.setAttribute("CART", poji);
                    }
                }
            }
            String urlRewritting = "CenterServlet?btAction=View Cart";
            response.sendRedirect(urlRewritting);
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
