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
import sample.product.ProductDTO;
import sample.session.CartSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class AcceptServlet extends HttpServlet {
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
            HttpSession session = request.getSession();
            if (session != null) {
                CartSessionBeanRemote cart = (CartSessionBeanRemote) session.getAttribute("CART");
                if (cart != null) {
                    HashMap<ProductDTO, Integer> listErr
                            = (HashMap<ProductDTO, Integer>) session.getAttribute("QTYERROR");
                    if (listErr != null) {
                        Iterator iter = listErr.entrySet().iterator();
                        while (iter.hasNext()) {
                            Map.Entry item = (Map.Entry) iter.next();
                            ProductDTO dto = (ProductDTO) item.getKey();
                            int quantity = Integer.parseInt(item.getValue().toString());
                            cart.removeItemFromCart(dto.getProductID());
                            cart.addItemToCart(dto, quantity);
                        }
                        session.removeAttribute("QTYERROR");
                        if (cart.getItems().isEmpty()) {
                            String url = cartPage;
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            String urlRewritting = "CenterServlet?btAction=Checkout";
                            RequestDispatcher rd = request.getRequestDispatcher(urlRewritting);
                            rd.forward(request, response);
                        }
                    } else {
                        response.sendRedirect(cartPage);
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
