package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cart.CartObj;
import sample.product.ProductDTO;
import sample.product.ProductErrors;

/**
 *
 * @author Suzy
 */
public class AddItemServlet extends HttpServlet {
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
            HttpSession session = request.getSession();
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }
            String productID = request.getParameter("txtProductID");
            String productName = request.getParameter("txtProductName");
            String unit = request.getParameter("txtUnit");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            String quantityStr = request.getParameter("txtQuantity");
            
            String searchValue = request.getParameter("lastSearchValue");

            boolean bErr = false;
            ProductErrors errors = new ProductErrors();
            if (quantityStr.trim().length() == 0) {
                bErr = true;
                errors.setQtyEmpty("Please enter quantity!!!");
            } else {
                boolean qtyErr = false;
                try {
                    int qty = Integer.parseInt(quantityStr);
                    if (qty <= 0) {
                        qtyErr = true;
                    }
                } catch (NumberFormatException ex) {
                    qtyErr = true;
                }
                if (qtyErr) {
                    bErr = true;
                    errors.setQtyErr("Quantity is a number greater than 0!!!");
                }
            }
            if (bErr) {
                request.setAttribute("ERROR", errors);
            } else {
                int quantity = Integer.parseInt(quantityStr);
                ProductDTO product = new ProductDTO(productID, productName, unit, price);
                cart.addItemToCart(product, quantity);
                session.setAttribute("CART", cart);
            }
            String urlRewritting = "search.jsp?txtSearch=" + searchValue;
            RequestDispatcher rd = request.getRequestDispatcher(urlRewritting);
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
