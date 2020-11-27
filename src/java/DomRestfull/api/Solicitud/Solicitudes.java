/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DomRestfull.api.Solicitud;


import Activos.logic.AccesoADatos.DaoSolicitud;
import Activos.logic.Solicitud;
import Activos.logic.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pc
 */

@Path("/solicitudes")
public class Solicitudes {
      
  
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
      public List<Solicitud> list(@QueryParam("comprobante") String comprobante) { 
        try{
           
            DaoSolicitud daoSolicitud = new DaoSolicitud(); 
            return (ArrayList<Solicitud>)daoSolicitud.listarSolicitudComp(comprobante);
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }
     
      
      
         @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public List <Solicitud> update(String p) {  
          try{
            DaoSolicitud daoSolicitud = new DaoSolicitud(); 
            Solicitud sol = daoSolicitud.buscarSolicitud(Integer.parseInt(p));
            sol.setEstado("Por Verificar");
            daoSolicitud.modificarSolicitud(sol);
            return (ArrayList<Solicitud>) daoSolicitud.listarSolicitud();
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }
    @Path("/procesada")
     @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void Procesada(String numero) {  
          try{
            DaoSolicitud daoSolicitud = new DaoSolicitud(); 
            Solicitud sol = daoSolicitud.buscarSolicitud(Integer.parseInt(numero));
            sol.setEstado("Procesada");
            daoSolicitud.modificarSolicitud(sol);
            
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }

         @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List <Solicitud> rechazar(String p) {  
          try{
            DaoSolicitud daoSolicitud = new DaoSolicitud(); 
            Solicitud sol = daoSolicitud.buscarSolicitud(Integer.parseInt(p));
            sol.setEstado("Rechazada");
            daoSolicitud.modificarSolicitud(sol);
            return (ArrayList<Solicitud>) daoSolicitud.listarSolicitud();
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }
    
}

