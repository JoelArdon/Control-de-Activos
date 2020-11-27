/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic;

import Activos.logic.AccesoADatos.DaoActivo;
import Activos.logic.AccesoADatos.DaoBien;
import Activos.logic.AccesoADatos.DaoCategoria;
import Activos.logic.AccesoADatos.DaoDependencia;
import Activos.logic.AccesoADatos.DaoFuncionario;
import Activos.logic.AccesoADatos.DaoSolicitud;
import Activos.logic.AccesoADatos.DaoUsuario;
import Activos.logic.AccesoADatos.GlobalException;
import Activos.logic.AccesoADatos.NoDataException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Pc
 */
public class Prueba {
    public static void main (String [ ] args) throws GlobalException, NoDataException {
      DaoActivo dao2 = new DaoActivo();
   
      DaoCategoria dao3= new DaoCategoria();
      DaoDependencia dao4 =new DaoDependencia();
      Dependencia depen = new Dependencia();
      DaoSolicitud daoSol = new DaoSolicitud();
      DaoBien daoBie = new DaoBien();
      Activo act= dao2.buscarActivo("Electrodomesticos-465");
      act.setFuncionario("ayuda");
  
      
      
      

      System.out.println(daoSol.listarSolicitudRegis(8));
    
      
      
      
      
   
      }
     
     
      
   

 

                        //Aquí las instrucciones del método

 

        } 

