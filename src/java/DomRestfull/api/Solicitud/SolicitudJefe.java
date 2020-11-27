/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DomRestfull.api.Solicitud;

import Activos.logic.AccesoADatos.DaoCategoria;
import Activos.logic.AccesoADatos.DaoSolicitud;
import Activos.logic.AccesoADatos.DaoUsuario;
import Activos.logic.Categoria;
import Activos.logic.Funcionario;
import Activos.logic.Solicitud;
import Activos.logic.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pc
 */
@Path("/solicitudesJefe")
public class SolicitudJefe {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Solicitud> list(@QueryParam("comprobante") String comprobante){
        try{
            DaoSolicitud daoSolicitud = new DaoSolicitud(); 
            return (ArrayList<Solicitud>)daoSolicitud.listarSolicitudPendiente(comprobante);
        }catch(Exception ex){ 
              throw new NotFoundException(); 
    }
    }
     @GET
    @Path("/registradores")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usuario> listas(@QueryParam("registrador") String codigo) {
            try{
            DaoUsuario daoUsuario = new DaoUsuario(); 
            return (ArrayList<Usuario>)daoUsuario.listarRegistradores();
        }catch(Exception ex){ 
              throw new NotFoundException(); 
    }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public List <Solicitud> asignar(Funcionario s) {  
        try{
      
                    
            DaoSolicitud daoSol = new DaoSolicitud();
            DaoUsuario daoUsuario = new DaoUsuario();
            Solicitud sol = daoSol.buscarSolicitud(Integer.parseInt(s.getId()));
            Usuario us= daoUsuario.buscarUsuario(s.getNombre());
            sol.setRegistrador(us.getId());
            daoSol.modificarSolicitud(sol);
           
            
            return (ArrayList<Solicitud>)daoSol.listarSolicitudPendiente("");
        }catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
 
}
