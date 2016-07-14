package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cart.CartDAO;
import sample.cart.CartObj;
import sample.product.ProductDAO;
import sample.product.ProductDTO;

/**
 *
 * @author Suzy
 */
public class CheckoutServlet extends HttpServlet {

    private final String searchPage = "search.jsp";
    private final String cartPage = "cart.jsp";

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
            HttpSession session = request.getSession(false);
            if (session != null) {
                String customer = (String) session.getAttribute("CUSTOMER");
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    HashMap<ProductDTO, Integer> listErr = (HashMap<ProductDTO, Integer>) session.getAttribute("QTYERROR");
                    if (listErr != null) {
                        Iterator iter = listErr.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry item = (Map.Entry) iter.next();
                            ProductDTO dto = (ProductDTO) item.getKey();
                            int quantity = Integer.parseInt(item.getValue().toString());
                            cart.removeItemFromCart(dto);
                            if (quantity > 0) {
                                cart.addItemToCart(dto, quantity);
                            }
                        }
                        session.removeAttribute("QTYERROR");
                        if (cart.getItems().isEmpty()) {
                            String url = cartPage;
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            cart.setCustID(customer);
                            float total = cart.getTotal();
                            CartDAO dao = new CartDAO();
                            boolean result = dao.addOrder(cart, total);
                            if (result) {
                                session.removeAttribute("CART");
                            }
                            String url = searchPage;
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        }
                    } else {
                        listErr = new HashMap<ProductDTO, Integer>();

                        HashMap<ProductDTO, Integer> items = cart.getItems();
                        Iterator iter = items.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry item = (Map.Entry) iter.next();
                            ProductDTO dto = (ProductDTO) item.getKey();
                            int quantity = Integer.parseInt(item.getValue().toString());
                            ProductDAO dao = new ProductDAO();
                            int currQty = dao.getQuantity(dto.getProductID());
                            if (quantity > currQty) {
                                listErr.put(dto, currQty);
                                //cart.removeItemFromCart(dto);
                            }
                        }
                        session.setAttribute("QTYERROR", listErr);
                        //session.setAttribute("CART", cart);
                        String url = "CenterServlet?btAction=Checkout";
                        if (!listErr.isEmpty()) {
                            url = cartPage;
                        }
//                        RequestDispatcher rd = request.getRequestDispatcher(url);
//                        rd.forward(request, response);
                        response.sendRedirect(url);
                    }
                } else {
                    response.sendRedirect(cartPage);
                }
            } else {
                response.sendRedirect(searchPage);
            }
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
