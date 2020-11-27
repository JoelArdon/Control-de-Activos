    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Presentation.Solicitud.list;

import Activos.logic.AccesoADatos.DaoSolicitud;
import Activos.logic.AccesoADatos.GlobalException;
import Activos.logic.AccesoADatos.NoDataException;
import Activos.logic.Funcionario;
import Activos.logic.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "Presentation.Solicitud.list", urlPatterns = {"/Presentation/Solicitud/list","/Presentation/Solicitud/list/buscar"})
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
     protected void processRequest(HttpServletRequest request, 
                                  HttpServletResponse response)
            throws ServletException, IOException, GlobalException, NoDataException {
            if (request.getServletPath().equals("/Presentation/Solicitud/list"))
                this.login(request, response);
              if (request.getServletPath().equals("/Presentation/Solicitud/list/buscar"))
                this.buscar(request, response);
    }
  protected void login(HttpServletRequest request, 
                                  HttpServletResponse response)
            throws ServletException, IOException, GlobalException, NoDataException {
      DaoSolicitud daoSolicitud = new DaoSolicitud();
      

     
     
     Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
    
    if(usuario.getRol().equals("Administrador")){
      request.setAttribute("lista", daoSolicitud.listarSolicitudDependencia(usuario.getFuncionario()
              .getDependencia().getCondigo()));
      request.getRequestDispatcher("/Presentation/Solicitud/list/view.jsp").forward(request, response);
     } 
    if(usuario.getRol().equals("Secretaria")){
      
          request.getRequestDispatcher("/Presentation/Secretaria/list/viewSecre.jsp").forward(request, response);
    
     } 
    if(usuario.getRol().equals("JefeRRHH")){
      
          request.getRequestDispatcher("/Presentation/JefeRRHH/List/viewJefeRRHH.jsp").forward(request, response);
    
     } 
      if(usuario.getRol().equals("Jefe")){
        request.setAttribute("lista", daoSolicitud.listarSolicitud());
          request.getRequestDispatcher("/Presentation/Jefe/list/viewJefe.jsp").forward(request, response);
    
     } 
        if(usuario.getRol().equals("Registrador")){
        request.setAttribute("lista", daoSolicitud.listarSolicitud());
          request.getRequestDispatcher("/Presentation/Registrador/list/viewRegis.jsp").forward(request, response);
    
     } 
      
     
  }
 protected void buscar(HttpServletRequest request, 
                                  HttpServletResponse response){
        DaoSolicitud daoSolicitud = new DaoSolicitud();
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        try {
            if (usuario.getRol().equals("Administrador")) {
                request.setAttribute("lista", daoSolicitud.listarSolicitudComp(request.getParameter("compbuscar")));
                request.getRequestDispatcher("/Presentation/Solicitud/list/view.jsp").forward(request, response);
            }
        } catch (Exception ex) {

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
        try {
            processRequest(request, response);
        } catch (GlobalException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (GlobalException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoDataException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
