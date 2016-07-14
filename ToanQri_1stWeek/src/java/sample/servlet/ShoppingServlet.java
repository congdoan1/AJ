package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.book.BookDAO;
import sample.book.BookDTO;

/**
 *
 * @author Suzy
 */
public class ShoppingServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Online Book Store</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Online Book Store</h1>");

            BookDAO dao = new BookDAO();
            dao.loadAll();
            List<BookDTO> listBooks = dao.getListBooks();
            if (listBooks != null) {
                if (listBooks.size() > 0) {
                    out.println("<table border='1'>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>No</th>");
                    out.println("<th>ISBN</th>");
                    out.println("<th>Title</th>");
                    out.println("<th>Price</th>");
                    out.println("<th>Action</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    for (int i = 0; i < listBooks.size(); i++) {
                        out.println("<form action='CenterServlet'>");
                        BookDTO dto = listBooks.get(i);
                        out.println("<tr>");
                        out.println("<td>" + (i + 1) + "</td>");
                        out.println("<td>"
                                + dto.getIsbn()
                                + "<input type='hidden' name='txtISBN' value='"
                                + dto.getIsbn()
                                + "' />"
                                + "</td>");
                        out.println("<td>"
                                + dto.getTitle()
                                + "<input type='hidden' name='txtTitle' value='"
                                + dto.getTitle()
                                + "' />"
                                + "</td>");
                        out.println("<td>"
                                + dto.getPrice()
                                + "<input type='hidden' name='txtPrice' value='"
                                + dto.getPrice()
                                + "' />"
                                + "</td>");
                        out.println("<td>"
                                + "<input type='submit' value='Add To Cart' name='btAction' />"
                                + "</td>");
                        out.println("</tr>");
                        out.println("</form>");
                    }
                    out.println("<form action='CenterServlet'");
                    out.println("<tr>");
                    out.println("<td colspan='4'></td>");
                    out.println("<td>"
                            + "<input type='submit' value='View Cart' name='btAction' />"
                            + "</td>");
                    out.println("</tr>");
                    out.println("</form");
                    out.println("</tbody>");
                    out.println("</table>");
                } else {
                    out.println("<h2>No book available this time!!</h2>");
                }
            } else {
                out.println("<h2>No book available this time!!</h2>");
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
