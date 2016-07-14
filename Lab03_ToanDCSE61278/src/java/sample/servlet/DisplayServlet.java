package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.ProductDAO;
import sample.product.ProductDTO;

/**
 *
 * @author Suzy
 */
public class DisplayServlet extends HttpServlet {

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
            out.println("<title>Online Store</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Products list</h1>");
            
            out.println("<table border='1'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>No</th>");
            out.println("<th>Product ID</th>");
            out.println("<th>Product Name</th>");
            out.println("<th>Quantity Per Unit</th>");
            out.println("<th>Price</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
            ProductDAO dao = new ProductDAO();
            dao.loadAll();
            List<ProductDTO> result = dao.getListProducts();
            int i = 0;
            if (result != null) {
                if (result.size() > 0) {
                    
                    for (i = 0; i < result.size(); i++) {
                        out.println("<form action='CenterServlet'");
                        ProductDTO dto = result.get(i);
                        out.println("<tr>");
                        out.println("<td>" + (i + 1) + "</td>");                        
                        out.println("<td>"
                                + dto.getProductID()
                                + "</td>");
                        String urlRewritting = "CenterServlet?btAction=Add To Cart"
                                + "&txtProductID="
                                + dto.getProductID()
                                + "&txtProductName="
                                + dto.getProductName()
                                + "&txtQuantityPerUnit="
                                + dto.getQuantityPerUnit()
                                + "&txtPrice="
                                + dto.getPrice();
                        out.println("<td>"
                                + "<a href='"
                                + urlRewritting
                                + "'>"
                                + dto.getProductName()
                                + "</a>"
                                + "</td>");
                        out.println("<td>"
                                + dto.getQuantityPerUnit()
                                + "</td>");
                        out.println("<td>"
                                + dto.getPrice()
                                + "</td>");
                        out.println("</tr>");
                        out.println("</form>");
                    }
                    
                } else {
                    out.println("<h1>No product for displaying!!!</h1>");
                }
            } else {
                out.println("<h1>No product for displaying!!!</h1>");
            }
            out.println("</tbody>");
            out.println("</table><br/>");
            out.println("Total: "
                            + i
                            + " product in the store<br/>");
                    out.println("<a href='CenterServlet?btAction=View Cart'>View my shopping cart</a>");
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
