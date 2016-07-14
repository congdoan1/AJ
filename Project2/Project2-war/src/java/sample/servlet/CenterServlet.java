package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Suzy
 */
public class CenterServlet extends HttpServlet {
    private final String searchServlet = "SearchServlet";
    private final String cartPage = "cart.jsp";
    private final String addItemServlet = "AddItemServlet";
    private final String removeItemServlet = "RemoveItemServlet";
    private final String loginServlet = "LoginServlet";
    private final String checkoutServlet = "CheckoutServlet";
    private final String confirmServlet = "ConfirmServlet";
    private final String acceptServlet = "AcceptServlet";
    private final String deniedServlet = "DeniedServlet";
    private final String errorLoginServlet = "ErrorLoginServlet";
    private final String createUserServlet = "CreateUserServlet";
    private final String registerPage = "register.jsp";
    private final String loginPage = "login.html";
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
            String button = request.getParameter("btAction");
            if (button.equals("Search")) {
                RequestDispatcher rd = request.getRequestDispatcher(searchServlet);
                rd.forward(request, response);
            } else if (button.equals("Add To Cart")) {
                RequestDispatcher rd = request.getRequestDispatcher(addItemServlet);
                rd.forward(request, response);
            } else if (button.equals("Checkout")) {
                RequestDispatcher rd = request.getRequestDispatcher(checkoutServlet);
                rd.forward(request, response);
            } else if (button.equals("Remove")) {
                RequestDispatcher rd = request.getRequestDispatcher(removeItemServlet);
                rd.forward(request, response);
            } else if (button.equals("Login")) {
                RequestDispatcher rd = request.getRequestDispatcher(loginServlet);
                rd.forward(request, response);
            } else if (button.equals("Create")) {
                RequestDispatcher rd = request.getRequestDispatcher(createUserServlet);
                rd.forward(request, response);
            } else if (button.equals("Yes")) {
                RequestDispatcher rd = request.getRequestDispatcher(acceptServlet);
                rd.forward(request, response);
            } else if (button.equals("No")) {
                RequestDispatcher rd = request.getRequestDispatcher(deniedServlet);
                rd.forward(request, response);
            } else if (button.equals("Register")) {
                RequestDispatcher rd = request.getRequestDispatcher(registerPage);
                rd.forward(request, response);
            } else if (button.equals("TryAgain")) {
                RequestDispatcher rd = request.getRequestDispatcher(errorLoginServlet);
                rd.forward(request, response);
            } else if (button.equals("View Cart")) {
                RequestDispatcher rd = request.getRequestDispatcher(cartPage);
                rd.forward(request, response);
            } else if (button.equals("LoginPage")) {
                RequestDispatcher rd = request.getRequestDispatcher(loginPage);
                rd.forward(request, response);
            } else if (button.equals("Confirm")) {
                RequestDispatcher rd = request.getRequestDispatcher(confirmServlet);
                rd.forward(request, response);
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
