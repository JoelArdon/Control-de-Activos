/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DomRestfull.api.Solicitud;

import Activos.logic.AccesoADatos.DaoActivo;
import Activos.logic.AccesoADatos.DaoBien;
import Activos.logic.AccesoADatos.DaoSolicitud;
import Activos.logic.AccesoADatos.DaoUsuario;
import Activos.logic.Activo;
import Activos.logic.Bien;
import Activos.logic.Funcionario;
import Activos.logic.Solicitud;
import Activos.logic.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
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
@Path("/activos")
public class Activos {
     @Context
        private HttpServletRequest request;
     
     @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Activo>  modificar(Activo act) {  
        try{
      DaoActivo dao2 = new DaoActivo();
      Activo act2 = dao2.buscarActivo(act.getCodigo());
      act2.setFuncionario(act.getFuncionario());
      act2.setPuesto(act.getPuesto());
      dao2.modificarActivo(act2);
         int numero  =(int)request.getSession().getAttribute("Numero");
           DaoBien dao = new DaoBien(); 
            DaoSolicitud daoSol = new DaoSolicitud();
            DaoActivo daoAct = new DaoActivo();
          Solicitud sol = daoSol.buscarSolicitud(numero);
           ArrayList<Bien> Bienes =  (ArrayList<Bien>)dao.ListarBienesSolicitud(numero);
           ArrayList<Activo> activos = new ArrayList<>();
           ArrayList<Activo> activoBienes = new ArrayList<>();
           for (Bien Biene : Bienes) {
                activoBienes = (ArrayList<Activo>) daoAct.listarActivoBien(Biene.getNumero());
                for (Activo activoBiene : activoBienes) {
                    activos.add(activoBiene);
                }
            }
           
          
            return activos;
           
            
        }catch (Exception ex) {
            throw new NotFoundException(); 
        }
    }
 
        @Path("/get")
            @GET
    @Produces({MediaType.APPLICATION_JSON})
      public Activo obteneractivo(@QueryParam("codigo") String codigo){
                try{
              DaoActivo daoAct = new DaoActivo();
              return (Activo) daoAct.buscarActivo(codigo);
           }catch(Exception ex){
            throw new NotFoundException(); 
        }
      }
         @GET
    @Produces({MediaType.APPLICATION_JSON})
      public List<Activo> list(@QueryParam("numero") int numero) { 
       try{
           DaoBien dao = new DaoBien(); 
            DaoSolicitud daoSol = new DaoSolicitud();
            DaoActivo daoAct = new DaoActivo();
          Solicitud sol = daoSol.buscarSolicitud(numero);
          request.getSession().setAttribute("Numero", numero);
            
           ArrayList<Bien> Bienes =  (ArrayList<Bien>)dao.ListarBienesSolicitud(numero);
           ArrayList<Activo> activos = new ArrayList<>();
           ArrayList<Activo> activoBienes = new ArrayList<>();
           for (Bien Biene : Bienes) {
                activoBienes = (ArrayList<Activo>) daoAct.listarActivoBien(Biene.getNumero());
                for (Activo activoBiene : activoBienes) {
                    activos.add(activoBiene);
                }
            }
           
          
            return activos;
           
          
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }
      @Path("/listar")
        @GET
    @Produces({MediaType.APPLICATION_JSON})
      public List<Activo> listarActivos() { 
       try{
          DaoActivo da = new DaoActivo();
          return da.listarActivo();
          
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }
}
   
