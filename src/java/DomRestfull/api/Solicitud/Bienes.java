/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DomRestfull.api.Solicitud;

import Activos.logic.AccesoADatos.DaoActivo;
import Activos.logic.AccesoADatos.DaoBien;
import Activos.logic.AccesoADatos.DaoCategoria;
import Activos.logic.AccesoADatos.DaoSolicitud;
import Activos.logic.Activo;
import Activos.logic.Bien;
import Activos.logic.Categoria;
import Activos.logic.Solicitud;
import Activos.logic.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@Path("/bienes")
public class Bienes {
    @Context
    private HttpServletRequest request;
     @GET
    @Produces({MediaType.APPLICATION_JSON})
      public List<Bien> list(@QueryParam("numero") int numero) { 
        try{
            DaoBien dao = new DaoBien(); 
            DaoSolicitud daoSol = new DaoSolicitud();
          Solicitud sol = daoSol.buscarSolicitud(numero);
            request.getSession().setAttribute("soli",sol);
            return (ArrayList<Bien>)dao.ListarBienesSolicitud(numero);
          
        }catch(Exception ex){
            throw new NotFoundException(); 
        }
    }
        @POST
    @Consumes(MediaType.APPLICATION_JSON)
     public void insertarActivo(ArrayList<String> ar) {
            try{
                DaoCategoria daoCat = new DaoCategoria();
                 DaoBien dao = new DaoBien();
                 DaoActivo daoAct = new DaoActivo();
                 DaoSolicitud daoSol= new DaoSolicitud();
                 String func =" ";
                 String puesto =" ";
             Solicitud sol = (Solicitud)  request.getSession().getAttribute("soli");
             ArrayList<Bien> bienes = dao.ListarBienesSolicitud(sol.getNumero());
             Activo act = new Activo();
             for(int i =0 ;i<bienes.size();i++){
                 Categoria  cat = daoCat.buscarCategoria(Integer.parseInt(ar.get(i)));
                 int conse = cat.getConsecutivo()+1;
                 for(int j = 1 ;j<=bienes.get(i).getCantidad();j++){
                 act.setBien(bienes.get(i));
                 act.setCategoria(cat);
                 act.setDependencia(sol.getDependencia());
                 act.setCodigo(cat.getNombre()+"-"+conse);
                 act.setFuncionario(func);
                 act.setPuesto(puesto);
                 daoAct.insertarActivo(act);
                 System.out.println(act.getCodigo()+act.getBien().getDescripcion()+act.getCategoria().getNombre()+act.getDependencia().getNombre());
                 conse++;
             }
                  cat.setConsecutivo(bienes.get(i).getCantidad()+daoCat.buscarCategoria(Integer.parseInt(ar.get(i))).getConsecutivo());
                daoCat.modificarCategoria(cat);
             }
             sol.setEstado("Espera de rotulacion");
             daoSol.modificarSolicitud(sol);
          
              
              
              
            
           
        }catch(Exception ex){ 
              throw new NotFoundException(); 
    }
            
    }
     
      
}
