package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.session.ChildrenSessionBeanRemote;

/**
 *
 * @author Suzy
 */
public class UpdateServlet extends HttpServlet {

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
            String cid = request.getParameter("txtCID");
            String DOB = request.getParameter("txtDOB");
            String POB = request.getParameter("txtPlaceOfBirth");
            String weight = request.getParameter("txtWeight");
            String height = request.getParameter("txtHeight");
            
            boolean bErr = false;
            try {
                Date t = new Date(Timestamp.valueOf(DOB).getTime());
            } catch (IllegalArgumentException ex) {
                bErr = true;
                request.setAttribute("dobErr", "Date of birth not correct!!!");
            }
            if (POB.trim().length() <= 2 || POB.trim().length() > 30) {
                bErr = true;
                request.setAttribute("pobErr", "Place of birth 3-20 char!!!");
            }
            try {
                int w = Integer.parseInt(weight);
                if (w <= 0) {
                    bErr = true;
                    request.setAttribute("weightErr", "Weight not correct!!!");
                }
            } catch (NumberFormatException ex) {
                bErr = true;
                request.setAttribute("weightErr", "Weight not correct!!!");
            }
            try {
                int h = Integer.parseInt(height);
                if (h <= 0) {
                    bErr = true;
                    request.setAttribute("heightErr", "Height not correct!!!");
                }
            } catch (NumberFormatException ex) {
                bErr = true;
                request.setAttribute("heightErr", "Height not correct!!!");
            }
            String url = "CenterServlet?btAction=List";
            if (bErr) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                try {
                    Context context = new InitialContext();
                    Object obj = context.lookup("ChildrenJNDI");
                    ChildrenSessionBeanRemote poji = (ChildrenSessionBeanRemote) obj;
                    boolean result 
                            = poji.updateChildren(cid, Timestamp.valueOf(DOB), POB, Integer.parseInt(weight), Integer.parseInt(height));
                    if (result) {
                        request.setAttribute("updateRes", "Update Successful!!!");
                    } else {
                        request.setAttribute("updateRes", "Update Failed!!!");
                    }
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
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
