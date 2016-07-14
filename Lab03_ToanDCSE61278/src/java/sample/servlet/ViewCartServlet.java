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
import sample.product.ProductDTO;

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
            out.println("<title>Online Shop</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>View of your shopping cart</h1>");
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    HashMap items = cart.getItems();
                    out.println("<table border='1'>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>No</th>");
                    out.println("<th>Product ID</th>");
                    out.println("<th>Product Name</th>");
                    out.println("<th>Quantity Per Unit</th>");
                    out.println("<th>Price(USD)</th>");
                    out.println("<th>Quantity</th>");
                    out.println("<th>Total</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");

                    int count = 0;
                    float total = 0;
                    Iterator iter = items.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry item = (Map.Entry) iter.next();
                        ProductDTO dto = (ProductDTO) item.getKey();
                        out.println("<tr>");
                        out.println("<td>"
                                + ++count
                                + "</td>");
                        out.println("<td>"
                                + dto.getProductID()
                                + "</td>");
                        out.println("<td>" + dto.getProductName() + "</td>");
                        out.println("<td>" + dto.getQuantityPerUnit() + "</td>");
                        out.println("<td>" + dto.getPrice() + "</td>");
                        out.println("<td>"
                                + item.getValue()
                                + "</td>");                        
                        out.println("<td>"
                                + dto.getPrice() * Integer.parseInt(item.getValue().toString())
                                + "</td>");
                        total += dto.getPrice() * Integer.parseInt(item.getValue().toString());
                        out.println("</tr>");
                    }
                    out.println("<tr>");
                    out.println("<td colspan='4' style='border: none'>"
                            + "Total: "
                            + count
                            + " products selected</td>");
                    out.println("<td colspan='2' style='border: none'>"
                            + "Payment in USD:"
                            + "</td>");
                    out.println("<td style='border: none'>"
                            + total
                            + "</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td style='border: none'><a href='CenterServlet?btAction=Checkout&total="
                            + String.valueOf(total)
                            + "'>Checkout</a></td>");                    
                    out.println("</tr>");
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
