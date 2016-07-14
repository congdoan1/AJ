package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.ProductDTO;
import sample.session.CartSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class RemoveItemServlet extends HttpServlet {
    
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
            String searchValue = request.getParameter("lastSearchValue");
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartSessionBeanRemote cart = (CartSessionBeanRemote) session.getAttribute("CART");
                if (cart != null) {
                    String[] rmv = request.getParameterValues("chkRemove");
                    if (rmv != null && rmv.length > 0) {
                        for (int i = 0; i < rmv.length; i++) {
                            String productID = rmv[i];
                            cart.removeItemFromCart(productID);
                        }
                        session.setAttribute("CART", cart);
                        HashMap<ProductDTO, Integer> listErr 
                                = (HashMap<ProductDTO, Integer>) session.getAttribute("QTYERROR");
                        if (listErr != null) {
                            session.removeAttribute("QTYERROR");
                        }
                    }
                }
            }
            String urlRewritting = "CenterServlet?btAction=View Cart&lastSearchValue=" + searchValue;
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
