package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Suzy
 */
public class DigestServlet extends HttpServlet {
    
     Hashtable users = new Hashtable();
    
    @Override
    public void init() throws ServletException {
        super.init();
        users.put("toan:toan", "allowed");
        users.put("manh:manh", "");
        users.put("duy:duy", "allowed");
    }

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
            String username = null;
            boolean valid = false;
            String authHeader = request.getHeader("Authorization");
            
            if (authHeader != null) {
                StringTokenizer st = new StringTokenizer(authHeader);
                String basic = st.nextToken();
                System.out.println(basic);
                if (basic.equalsIgnoreCase("Basic")) {
                    String credential = st.nextToken();
                    System.out.println(credential);
                    BASE64Decoder decoder = new BASE64Decoder();
                    String userPass = new String(decoder.decodeBuffer(credential));
                    System.out.println(userPass);
                    int p = userPass.indexOf(":");
                    username = userPass.substring(0, p);
                    valid = users.containsKey(userPass) && users.get(userPass).equals("allowed");
                }
            }
            if (!valid) {
                String s = "Basic realm=\"Servlet Users\"";
                response.setHeader("WWW-Authenticate", s);
                response.setStatus(401);
            } else {
                out.println("<h3>Welcome " + username + "</h3>");
                out.println("You are authorized to process!!!");
                return;
            }
            out.println("<h1><font color='red'>Invalid username or password</font></h1>");
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
