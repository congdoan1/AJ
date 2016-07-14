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
import sample.session.ElectricSessionBeanRemote;

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
            String electid = request.getParameter("txtElectID");
            String distributor = request.getParameter("txtDistributor");
            String voltageStr = request.getParameter("txtVoltage");
            String powerStr = request.getParameter("txtPower");

            boolean bErr = false;
            if (distributor.trim().length() <= 0 || distributor.trim().length() > 20) {
                bErr = true;
                request.setAttribute("distributorErr", "Distributor 1-20 char!!!");
            }
            try {
                int voltage = Integer.parseInt(voltageStr);
                if (voltage <= 0) {
                    bErr = true;
                    request.setAttribute("voltageErr", "Voltage is integer > 0!!!");
                }
            } catch (NumberFormatException ex) {
                bErr = true;
                request.setAttribute("voltageFor", "Voltage not correct format!!!");
            }
            try {
                int power = Integer.parseInt(powerStr);
                if (power <= 0) {
                    bErr = true;
                    request.setAttribute("powerErr", "Power is integer > 0!!!");
                }
            } catch (NumberFormatException ex) {
                bErr = true;
                request.setAttribute("powerFor", "Power not correct format!!!");
            }
            
            if (bErr) {
                RequestDispatcher rd = request.getRequestDispatcher("CenterServlet?btAction=List");
                rd.forward(request, response);
            } else {
                Context context = new InitialContext();
                Object obj = context.lookup("ElectricJNDI");
                ElectricSessionBeanRemote poji = (ElectricSessionBeanRemote) obj;
                boolean result = poji.updateElec(electid, distributor, Integer.parseInt(voltageStr), Integer.parseInt(powerStr));
                if (result) {
                    String urlRewritting = "CenterServlet?btAction=List";
                    response.sendRedirect(urlRewritting);
                } else {
                    out.println("Khong the capnhat");
                }
            }
        } catch (NamingException ex) {
            log(ex.getMessage());
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
