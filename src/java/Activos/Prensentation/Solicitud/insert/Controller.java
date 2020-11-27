/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.Prensentation.Solicitud.insert;

import Activos.logic.Bien;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pc
 */
@WebServlet(name = "Presentation/Solicitud/insert", urlPatterns = {"/Presentation/Solicitud/insert"})
public class Controller extends HttpServlet {

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
        if (request.getServletPath().equals("/Presentation/Solicitud/insert"))
                this.insert(request, response);
    }
    protected void insert(HttpServletRequest request, 
                                  HttpServletResponse response)
            throws ServletException, IOException{
     
      request.getRequestDispatcher("/Presentation/Solicitud/show").forward(request, response);
    
    }
     void updateModelBien(Bien model, HttpServletRequest request){
        model.setNumero(Integer.parseInt(request.getParameter("numero")));
        model.setDescripcion(request.getParameter("descripcion"));
        model.setMarca(request.getParameter("marca"));
        model.setModelo(request.getParameter("modelo"));
        model.setPrecio(Integer.parseInt(request.getParameter("precio")));
        model.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
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
