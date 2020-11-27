/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DomRestfull.api.Solicitud;

import Activos.logic.AccesoADatos.DaoCategoria;
import Activos.logic.AccesoADatos.DaoSolicitud;
import Activos.logic.Categoria;
import Activos.logic.Solicitud;
import Activos.logic.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pc
 */
@Path("/categorias")
public class Categorias {
   @Context
    private HttpServletRequest request;
        @POST
    @Consumes(MediaType.APPLICATION_JSON)
     public void agregar(Categoria cat) {
            try{
                System.out.println(cat.getCodigo());
            DaoCategoria dao = new DaoCategoria();
            dao.insertarCategoria(cat);
            
           
        }catch(Exception ex){ 
              throw new NotFoundException(); 
    }
            
    }
      @GET
      @Path("/listar")
    @Produces({MediaType.APPLICATION_JSON})
      public List<Solicitud> listarSolRegis() { 
        try{
            Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
          System.out.println(usuario.getFuncionario());
            DaoSolicitud daoSolicitud = new DaoSolicitud(); 
            return (ArrayList<Solicitud>)daoSolicitud.listarSolicitudRegis(Integer.parseInt(usuario.getId()));
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }
      @GET
      
    @Produces({MediaType.APPLICATION_JSON})
      public List<Categoria> listarCat() { 
        try{
            DaoCategoria dao = new DaoCategoria();
            return (ArrayList<Categoria>)dao.listarCategoria();
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }
      
}
