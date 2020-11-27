/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.Presentation.Solicitud.show;

import Activos.logic.AccesoADatos.DaoBien;
import Activos.logic.AccesoADatos.DaoSolicitud;
import Activos.logic.AccesoADatos.GlobalException;
import Activos.logic.AccesoADatos.NoDataException;
import Activos.logic.Bien;
import Activos.logic.Solicitud;
import Activos.logic.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pc
 */
@WebServlet(name = "Presentation/Solicitud/show", urlPatterns = {"/Presentation/Solicitud/show/insert","/Presentation/Solicitud/show","/Presentation/Solicitud/show/modificar"})
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
            throws ServletException, IOException {
            if (request.getServletPath().equals("/Presentation/Solicitud/show"))
                this.show(request, response);
             if (request.getServletPath().equals("/Presentation/Solicitud/show/insert"))
                this.insert(request, response);
               if (request.getServletPath().equals("/Presentation/Solicitud/show/modificar"))
                this.modificar(request, response);
    }
    protected void show(HttpServletRequest request, 
                                  HttpServletResponse response)
            throws ServletException, IOException {
           DaoSolicitud daoSol = new DaoSolicitud();
            DaoBien daobien = new DaoBien();
            Solicitud soli = new Solicitud();
            Bien bien = new Bien();
            try {
                soli= daoSol.buscarSolicitud(Integer.parseInt(request.getParameter("numero")));
                soli.setLista_bienes(daobien.ListarBienesSolicitud(soli.getNumero()));
             
             
            } catch (Exception ex) {      
            }
            request.setAttribute("bien",bien);
            request.getSession(true).setAttribute("soli", soli);
            request.setAttribute("solicitud", soli);
            request.getRequestDispatcher("/Presentation/Solicitud/show/view.jsp").
                    forward( request, response); 
    }      
     void updateModelId(Solicitud model, HttpServletRequest request){
        model.setNumero(Integer.parseInt(request.getParameter("numero")));
    }
     protected void insert(HttpServletRequest request, 
                                  HttpServletResponse response)
            throws ServletException, IOException{
         Bien bien = new Bien();
        
            Map<String, String> errors = this.validarBien(request);
             Solicitud solici = (Solicitud) request.getSession(true).getAttribute("soli");
            if (errors.isEmpty()) {
         updateModelBien(bien,request);
         bien.setSolicitud(solici);
         solici.getLista_bienes().add(bien);
         request.getSession(true).setAttribute("soli", solici);
        bien = new Bien();
         request.setAttribute("bien", bien);
         request.setAttribute("solicitud", solici);
         request.getRequestDispatcher("/Presentation/Solicitud/show/view.jsp").forward(request, response);
    }
         else
            {
                   updateModelBien(bien,request);
                request.setAttribute("bien", bien);
                 request.setAttribute("errorsBien", errors);
                  request.setAttribute("solicitud", solici);
                  request.getRequestDispatcher("/Presentation/Solicitud/show/view.jsp").forward(request, response);
            }
     }
     protected void modificar(HttpServletRequest request, 
                                  HttpServletResponse response)
            throws ServletException, IOException {
            Solicitud soli = (Solicitud) request.getSession(true).getAttribute("soli");
         updateModelSolicitud(soli,request);
         DaoSolicitud daoSol = new DaoSolicitud();
         DaoBien daobien = new DaoBien();
         Bien bien = new Bien();
          
             Map<String, String> errors = this.validarSolicitud(request);
            if (errors.isEmpty()) {
         try{
            if(soli.getNumero()==0){
              int numero=daoSol.insertar_Solicitud(soli);
              soli.setNumero(numero);  
            }
            else{
             daoSol.modificarSolicitud(soli);
         }
         }
           catch(Exception ex){ 
         }
         try{  
             for(Bien p: soli.getLista_bienes()){
                 p.setSolicitud(soli);
                 if(daobien.buscarBien(p.getNumero())==null){
                     daobien.insertarBien(p);
                 }         
             }
               request.setAttribute("lista", daoSol.listarSolicitudDependencia(soli.getDependencia().getCondigo()));
         request.getRequestDispatcher("/Presentation/Solicitud/list/view.jsp").forward(request, response);
         }  
         catch(Exception ex){
             
         }
     }
            else{
                request.setAttribute("bien", bien);
                 request.setAttribute("errors", errors);
             
                  request.setAttribute("solicitud", soli);
                  request.getRequestDispatcher("/Presentation/Solicitud/show/view.jsp").forward(request, response);
            }
     }
      void updateModelBien(Bien model, HttpServletRequest request){
       
        model.setDescripcion(request.getParameter("descripcion"));
        model.setMarca(request.getParameter("marca"));
        model.setModelo(request.getParameter("modelo"));
         if(isInteger(request.getParameter("precio"))==true){
        model.setPrecio(Integer.parseInt(request.getParameter("precio")));
         }
            if(isInteger(request.getParameter("cantidad"))==true){
        model.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            }
         
    } 
       Map<String,String> validarSolicitud(HttpServletRequest request){
      Map<String,String> errores= new HashMap<>();
      if(request.getParameter("comprobante").equals(" ")){
          errores.put("comprobante", "comprobante requerido");
          
      }
      if(request.getParameter("tipo").isEmpty()){
          errores.put("tipo","tipo requerida");
      }
     
      
      
      
      
      
      return errores;
  }
    void updateModelSolicitud(Solicitud model,HttpServletRequest request){
         Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        model.setComprobante(request.getParameter("comprobante"));
        model.setTipo(request.getParameter("tipo"));
        model.setEstado("Recibida");
        model.setDependencia(usuario.getFuncionario().getDependencia());
       SimpleDateFormat stringdate = new SimpleDateFormat("yyyy-MM-dd");
       try{
       Date parsed = stringdate.parse(request.getParameter("fecha"));
      java.sql.Date sql  = new java.sql.Date(parsed.getTime());
      model.setFecha(sql);
       }
       
       catch(Exception ex ){
          
       }
       
 
    }
    
       Map<String,String> validarBien(HttpServletRequest request){
      Map<String,String> errores= new HashMap<>();
      if(request.getParameter("descripcion").isEmpty()){
          errores.put("descripcion", "descripcion requerido");
          
      }
      if(request.getParameter("marca").isEmpty()){
          errores.put("marca","marca requerida");
      }
          if(request.getParameter("modelo").isEmpty()){
          errores.put("modelo", "modelo requerido");
          
      }
     
          if(isInteger(request.getParameter("cantidad"))==false){
          errores.put("cantidad","cantidad requerida");
      }
          else{
              if(Integer.parseInt(request.getParameter("cantidad"))<=0){
                  errores.put("cantidad","cantidad requerida");
              }
          }
        if(isInteger(request.getParameter("precio"))==false){
          errores.put("precio","precio requerida");
      }
        else{
           if(Integer.parseInt(request.getParameter("precio"))<=0){
                  errores.put("precio","precio requerida");
              }
        }
      
      
      
      
      return errores;
  }
 public boolean isInteger(String numero){
    try{
        Integer.parseInt(numero);
        return true;
    }catch(NumberFormatException e){
        return false;
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
