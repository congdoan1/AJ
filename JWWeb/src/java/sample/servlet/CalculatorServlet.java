package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import sample.jwsClient.CalculatorWebService_Service;

/**
 *
 * @author Suzy
 */
public class CalculatorServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Day11JWSE0866/CalculatorWebService/CalculatorWebService.wsdl")
    private CalculatorWebService_Service service;

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
            String button = request.getParameter("btAction");
            if (button.equals("Add")) {
                String n1 = request.getParameter("txtNum1");
                String n2 = request.getParameter("txtNum2");
                double num1 = Double.parseDouble(n1);
                double num2 = Double.parseDouble(n2);
                
                double result = add(num1, num2);
                out.println("Add Result " + result);
                
            } else if (button.equals("Subtract")) {
                String n1 = request.getParameter("txtNum1");
                String n2 = request.getParameter("txtNum2");
                double num1 = Double.parseDouble(n1);
                double num2 = Double.parseDouble(n2);
                
                 double result = sub(num1, num2);
                 out.println("Subtract Result " + result);
                 
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

    private double add(double num1, double num2) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        sample.jwsClient.CalculatorWebService port = service.getCalculatorWebServicePort();
        return port.add(num1, num2);
    }

    private double sub(double num1, double num2) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        sample.jwsClient.CalculatorWebService port = service.getCalculatorWebServicePort();
        return port.sub(num1, num2);
    }

}
