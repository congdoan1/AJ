package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Suzy
 */
public class ShoppingServlet extends HttpServlet {
    private final String shoppingPage = "shopping.html";
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
            String[] products = request.getParameterValues("rmv");
            if (products != null) {
                for (int i = 0; i < products.length; i++) {
                    Cookie[] cookies = request.getCookies();
                    if (cookies == null) {
                        Cookie cookie = new Cookie(products[i], "1");
                        cookie.setMaxAge(60 * 10);
                        response.addCookie(cookie);
                    } else {
                        boolean bFound = false;
                        for (int j = 0; j < cookies.length; j++) {
                            if (cookies[j].getName().equals(products[i])) {
                                bFound = true;
                                String value = cookies[i].getValue();
                                int quantity = Integer.parseInt(value) + 1;
                                Cookie cookie = new Cookie(products[i], String.valueOf(quantity));
                                cookie.setMaxAge(60 * 10);
                                response.addCookie(cookie);
                                break;
                            }
                        }
                        if (!bFound) {
                            Cookie cookie = new Cookie(products[i], "1");
                            cookie.setMaxAge(60 * 10);
                            response.addCookie(cookie);
                        }
                    }
                }
            }
            response.sendRedirect(shoppingPage);
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
