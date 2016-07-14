package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import sample.error.ProductErrors;
import sample.session.CartSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class AddItemServlet extends HttpServlet {

    private final String errorPage = "errors.html";

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
            HttpSession session = request.getSession();
            CartSessionBeanRemote cart = (CartSessionBeanRemote) session.getAttribute("CART");
            if (cart == null) {
                try {
                    Context context = new InitialContext();

                    Object obj = context.lookup("CartJNDI");
                    cart = (CartSessionBeanRemote) obj;
                } catch (NamingException ex) {
                    log(ex.getMessage());
                    response.sendRedirect(errorPage);
                    return;
                }
            }
            if (cart != null) {
                String productID = request.getParameter("txtProductID");
                String productName = request.getParameter("txtProductName");
                String unit = request.getParameter("txtUnit");
                float price = Float.parseFloat(request.getParameter("txtPrice"));
                String quantityStr = request.getParameter("txtQuantity");

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
                    ProductDTO dto = new ProductDTO(productID, productName, unit, price);
                    cart.addItemToCart(dto, quantity);
                    session.setAttribute("CART", cart);
                }
            }
            HashMap<ProductDTO, Integer> listErr
                    = (HashMap<ProductDTO, Integer>) session.getAttribute("QTYERROR");
            if (listErr != null) {
                session.removeAttribute("QTYERROR");
            }
            String urlRewritting = "CenterServlet?btAction=Search&txtSearch=" + searchValue;
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
