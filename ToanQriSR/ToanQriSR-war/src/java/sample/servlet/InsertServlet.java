/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.session.BookSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class InsertServlet extends HttpServlet {

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
            String isbn = request.getParameter("txtISBN");
            String title = request.getParameter("txtTitle");
            String priceStr = request.getParameter("txtPrice");

            boolean bErr = false;
            if (isbn.trim().length() <= 0) {
                bErr = true;
                request.setAttribute("isbnErr", "Please enter ISBN");
            }
            if (title.trim().length() <= 0) {
                bErr = true;
                request.setAttribute("titleErr", "Please enter Title");
            }
            if (priceStr.trim().length() <= 0) {
                bErr = true;
                request.setAttribute("priceErr", "Please enter Price");
            } else {
                boolean bPrice = false;
                try {
                    float price = Float.parseFloat(priceStr);
                    if (price <= 0) {
                        bPrice = true;
                    }
                } catch (NumberFormatException ex) {
                    bPrice = true;
                }
                if (bPrice) {
                    bErr = true;
                    request.setAttribute("priceFor", "Price is a float greater than 0");
                }
            }

            if (bErr) {
                RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
                rd.forward(request, response);
            } else {
                try {
                    Context context = new InitialContext();
                    Object obj = context.lookup("BookJNDI");
                    BookSessionBeanRemote poji = (BookSessionBeanRemote) obj;
                    boolean result = poji.insertBook(isbn, title, Float.parseFloat(priceStr));
                    if (result) {
                        response.sendRedirect("CenterServlet?btAction=List");
                    } else {
                        request.setAttribute("isbnDup", isbn + " da ton tai");
                        RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
                        rd.forward(request, response);
                    }
                } catch (NamingException ex) {
                    log(ex.getMessage());
                }
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
