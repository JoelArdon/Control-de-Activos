/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.Presentation.Login.login;

import Activos.logic.AccesoADatos.DaoUsuario;
import Activos.logic.AccesoADatos.GlobalException;
import Activos.logic.AccesoADatos.NoDataException;
import Activos.logic.Funcionario;
import Activos.logic.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
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
@WebServlet(name = "Presentation.Login.login", urlPatterns = {"/Presentation/Login/login","/Presentation/Login/logout","/Presentation/Login/preaprelogin"})
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
            throws ServletException, IOException, GlobalException, NoDataException {
       if (request.getServletPath().equals("/Presentation/Login/login"))
                this.login(request, response);
       if (request.getServletPath().equals("/Presentation/Login/logout"))
                this.logout(request, response);
        if (request.getServletPath().equals("/Presentation/Login/preaprelogin"))
                this.prepareLogin(request, response);
    }
    protected void login(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, GlobalException, NoDataException {
        if (this.verificar(request)) {
            Map<String, String> errors = this.validar(request);
            if (errors.isEmpty()) {
                Usuario model = new Usuario();
                DaoUsuario daousu = new DaoUsuario();
                    updateModel(model, request);
                    request.setAttribute("model", model);
                    Usuario base= null;
                try {
                    base = daousu.buscarUsuario(model.getId());
                    if (model.getClave().equals(base.getClave())) {
                        
                        HttpSession session = request.getSession(true);
                        session.setAttribute("usuario", base);
                        request.getRequestDispatcher("/Presentation/Solicitud/list").forward(request, response);
                    }
                    
                    else {
                         
                        request.getRequestDispatcher("/Presentation/Usuario/Login/view.jsp").forward(request, response);
                    }
                 
                } catch (Exception ex) {
                    request.getRequestDispatcher("/Presentation/Usuario/Login/view.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/Presentation/Usuario/Login/view.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/Presentation/Error.jsp").forward(request, response);
        }
    }
        
        
        
    
  void updateModel(Usuario model, HttpServletRequest request){
        model.setId(request.getParameter("username"));
        model.setClave(request.getParameter("password"));
    }
      boolean verificar(HttpServletRequest request){
       if (request.getParameter("username")==null) return false;
       if (request.getParameter("password")==null) return false; 
       return true;
    }
     
      
  Map<String,String> validar(HttpServletRequest request){
      Map<String,String> errores= new HashMap<>();
      if(request.getParameter("username").isEmpty()){
          errores.put("username", "username requerido");
          
      }
      if(request.getParameter("password").isEmpty()){
          errores.put("password","password requerida");
      }
      
      return errores;
  }
protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            session.removeAttribute("usuario");
            session.invalidate();
            request.getRequestDispatcher("/Presentation/Login/preaprelogin").forward( request, response); 
    }
protected void prepareLogin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Usuario model = new Usuario();
        request.setAttribute("model", model);
        request.getRequestDispatcher("/Presentation/Usuario/Login/view.jsp").forward( request, response); 
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
