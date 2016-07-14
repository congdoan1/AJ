package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.customer.CustomerDTO;
import sample.error.CustomerErrors;
import sample.session.CustomerSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class CreateUserServlet extends HttpServlet {

    private final String loginPage = "login.html";
    private final String registerPage = "register.jsp";
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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String custName = request.getParameter("txtCustName");
            String lastname = request.getParameter("txtLastname");
            String middlename = request.getParameter("txtMiddleName");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");

            boolean bErr = false;
            CustomerErrors errors = new CustomerErrors();
            if (username.trim().length() < 6 || username.trim().length() > 15) {
                bErr = true;
                errors.setUsernameErr("Username phai tu 6 den 15 ki tu!!!");
            }
            if (password.length() < 8 || password.length() > 30) {
                bErr = true;
                errors.setPasswordErr("Password phai tu 8 den 3 ki tu!!!");
            } else if (!password.equals(confirm)) {
                bErr = true;
                errors.setConfirmNotMatch("Password va Confirm khong giong nhau!!!");
            }
            if (custName.trim().length() < 2 || custName.trim().length() > 15) {
                bErr = true;
                errors.setCustNameErr("Customer Name phai tu 2 den 15 ki tu!!!");
            }
            if (lastname.trim().length() < 2 || lastname.trim().length() > 15) {
                bErr = true;
                errors.setLastnameErr("Lastname phai tu 2 den 15 ki tu!!!");
            }
            if (phone.trim().length() > 0) {
                boolean phoneValid = false;
                try {
                    int phoneC = Integer.parseInt(phone);
                    if (phoneC <= 0) {
                        phoneValid = true;
                    }
                } catch (NumberFormatException ex) {
                    phoneValid = true;
                }
                if (phoneValid) {
                    bErr = true;
                    errors.setPhoneErr("Phone number is incorrect!!!");
                }
            }

            String url = registerPage;
            if (bErr) {
                request.setAttribute("ERROR", errors);
            } else {
                try {
                    Context context = new InitialContext();

                    Object obj = context.lookup("CustomerJNDI");
                    CustomerSessionBeanRemote poji = (CustomerSessionBeanRemote) obj;

                    CustomerDTO dto = new CustomerDTO(username, password, custName, lastname, middlename, address, phone);
                    boolean result = poji.addUser(dto);
                    if (result) {
                        url = loginPage;
                    } else {
                        errors.setUsernameDup(username + " da ton tai!!!");
                        request.setAttribute("ERROR", errors);
                    }
                } catch (SQLException ex) {
                    log(ex.getMessage());
                    response.sendRedirect(errorPage);
                    return;
                } catch (NamingException ex) {
                    log(ex.getMessage());
                    response.sendRedirect(errorPage);
                    return;
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
