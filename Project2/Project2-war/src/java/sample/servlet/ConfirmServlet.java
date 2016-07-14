/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.ProductDTO;
import sample.session.CartSessionBeanRemote;
import sample.session.ProductSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class ConfirmServlet extends HttpServlet {

    private final String errorPage = "errors.html";
    private final String cartPage = "cart.jsp";
    private final String searchPage = "search.jsp";

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
                //String customer = (String) session.getAttribute("USER");
                CartSessionBeanRemote cart = (CartSessionBeanRemote) session.getAttribute("CART");
                if (cart != null) {
                    HashMap<ProductDTO, Integer> listErr
                            = (HashMap<ProductDTO, Integer>) session.getAttribute("QTYERROR");
                    if (listErr == null) {
                        listErr = new HashMap<ProductDTO, Integer>();
                        HashMap<ProductDTO, Integer> items = cart.getItems();
                        Iterator iter = items.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry item = (Map.Entry) iter.next();
                            ProductDTO dto = (ProductDTO) item.getKey();
                            int quantity = Integer.parseInt(item.getValue().toString());
                            ProductSessionBeanRemote productPOJI = null;
                            try {
                                Context context = new InitialContext();
                                Object obj = context.lookup("ProductJNDI");
                                productPOJI = (ProductSessionBeanRemote) obj;
                            } catch (NamingException ex) {
                                log(ex.getMessage());
                                response.sendRedirect(errorPage);
                                return;
                            }
                            if (productPOJI != null) {
                                int currQty = productPOJI.getQuantity(dto.getProductID());
                                if (quantity > currQty) {
                                    listErr.put(dto, currQty);
                                }
                            }
                        }
                        String url = "CenterServlet?btAction=Checkout";
                        if (!listErr.isEmpty()) {
                            url = cartPage;
                            session.setAttribute("QTYERROR", listErr);
                        }
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    } else {
                        String url = cartPage;
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                } else {
                    response.sendRedirect(cartPage);
                }
            } else {
                response.sendRedirect(searchPage);
            }
        } catch (SQLException ex) {
            log(ex.getMessage());
            response.sendRedirect(errorPage);
            return;
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
