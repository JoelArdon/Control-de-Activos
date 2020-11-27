/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic.AccesoADatos;

import Activos.logic.Activo;
import Activos.logic.Bien;
import Activos.logic.Categoria;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author Pc
 */
public class DaoActivo extends Servicio {
       private static final String INSERTARACTIVO= "{call INSERTAR_ACTIVO(?,?,?,?,?,?)}";
    private static final String ELIMINARACTIVO ="{call ELIMINAR_ACTIVO(?)}";
    private static final String BUSCARACTIVO = "{?=call BUSCAR_ACTIVO(?)}";
    private static final String LISTARACTIVO = "{?=call LISTAR_ACTIVO()}";
     private static final String LISTARACTIVO_BIEN = "{?=call BUSCAR_ACTIVO_BIEN(?)}";
      private static final String MODIFICAR_ACTIVO = "{call MODIFICAR_ACTIVO(?,?,?,?,?,?)}";
    
      public void modificarActivo(Activo activo) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException e)
		{
			throw new GlobalException("No se ha localizado el driver");
		}
		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}
		PreparedStatement pstmt = null;
		try
		{
                    pstmt = conexion.prepareStatement(MODIFICAR_ACTIVO);
                    pstmt.setString(1, activo.getCodigo());
                    pstmt.setInt(2, activo.getBien().getNumero());
                    pstmt.setInt(3, activo.getCategoria().getCodigo());
                    pstmt.setInt(4, activo.getDependencia().getCondigo());
                    pstmt.setString(5, activo.getFuncionario());
                    pstmt.setString(6, activo.getPuesto());

			
			int resultado = pstmt.executeUpdate();

			//si es diferente de 0 es porq si afecto un registro o mas
			if (resultado == 0)
			{
				throw new NoDataException("No se realizo la actualización");
			}
			else
			{
				System.out.println("\nModificación Satisfactoria!");
			}
		}
		catch (SQLException e)
		{
    			throw new GlobalException("Sentencia no valida");
		}
		finally
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e)
			{
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
	}
    public ArrayList listarActivo() throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException ex)
		{
			throw new GlobalException("No se ha localizado el Driver");
		} catch (SQLException ex) {
               Logger.getLogger(DaoActivo.class.getName()).log(Level.SEVERE, null, ex);
           }

		
                ResultSet rs = null;

		ArrayList coleccion = new ArrayList();
                DaoBien daoBien = new DaoBien();
                DaoDependencia daoDepen = new DaoDependencia();
                DaoCategoria daoCate = new DaoCategoria();
                
		Activo activo = null;
		CallableStatement pstmt = null;
		try
		{
                    
			pstmt = conexion.prepareCall(LISTARACTIVO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
			activo = new Activo(rs.getString("codigo"),daoBien.buscarBien(rs.getInt("bien")),daoDepen.buscarDependencia(rs.getInt("dependencia")),
                               daoCate.buscarCategoria(rs.getInt("categoria")),rs.getString("puesto"),rs.getString("funcionario")); 
                                                                           
				coleccion.add(activo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

			throw new GlobalException("Sentencia no valida");
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (pstmt != null)
				{
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e)
			{
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
		if (activo==null)
		{
			return null;
		}
		return coleccion;
	}
    
   public ArrayList listarActivoBien(int numero) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException ex)
		{
			throw new GlobalException("No se ha localizado el Driver");
		} catch (SQLException ex) {
               Logger.getLogger(DaoActivo.class.getName()).log(Level.SEVERE, null, ex);
           }

		
                ResultSet rs = null;

		ArrayList coleccion = new ArrayList();
                DaoBien daoBien = new DaoBien();
                DaoDependencia daoDepen = new DaoDependencia();
                DaoCategoria daoCate = new DaoCategoria();
                
		Activo activo = null;
		CallableStatement pstmt = null;
		try
		{
                    
			pstmt = conexion.prepareCall(LISTARACTIVO_BIEN);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        pstmt.setInt(2, numero);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
			activo = new Activo(rs.getString("codigo"),daoBien.buscarBien(rs.getInt("bien")),daoDepen.buscarDependencia(rs.getInt("dependencia")),
                               daoCate.buscarCategoria(rs.getInt("categoria")),rs.getString("puesto"),rs.getString("funcionario")); 
                                                                           
				coleccion.add(activo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

			throw new GlobalException("Sentencia no valida");
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (pstmt != null)
				{
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e)
			{
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
		if (activo==null)
		{
			return null;
		}
		return coleccion;
	}
    
     public void insertarActivo(Activo act) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARACTIVO); 
            pstmt.setString(1,act.getCodigo());
            pstmt.setInt(2,act.getBien().getNumero());
            pstmt.setInt(3,act.getCategoria().getCodigo());
            pstmt.setInt(4, act.getDependencia().getCondigo());
            pstmt.setString(5, act.getFuncionario());
            pstmt.setString(6, act.getPuesto());
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la inserci�n");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
}
  public void eliminarActivo(String codigo) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException e)
		{
			throw new GlobalException("No se ha localizado el driver");
		}
		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}
		PreparedStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareStatement(ELIMINARACTIVO);
			pstmt.setString(1,codigo );

			int resultado = pstmt.executeUpdate();

			if (resultado == 0)
			{
				throw new NoDataException("No se realizo el borrado");
			}
			else
			{
				System.out.println("\nEliminación Satisfactoria!");
			}
		}
		catch (SQLException e)
		{
			throw new GlobalException("Sentencia no valida");
		}
		finally
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e)
			{
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
	} 
     public Activo buscarActivo(String codigo) throws GlobalException, NoDataException
	{

		try
		{
			conectar();
		}
		catch (ClassNotFoundException e)
		{
			throw new GlobalException("No se ha localizado el driver");
		}
		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}
		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
                DaoBien daoBien = new DaoBien();
                DaoDependencia daoDepen = new DaoDependencia();
                DaoCategoria daoCate = new DaoCategoria();
                
		Activo activo = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(BUSCARACTIVO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, codigo);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				activo = new Activo(rs.getString("codigo"),daoBien.buscarBien(rs.getInt("bien")),daoDepen.buscarDependencia(rs.getInt("dependencia")),
                               daoCate.buscarCategoria(rs.getInt("categoria")),rs.getString("puesto"),rs.getString("funcionario")); 
                                                                           
				coleccion.add(activo);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();

			throw new GlobalException("Sentencia no valida");
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (pstmt != null)
				{
					pstmt.close();
				}
				desconectar();
			}
			catch (SQLException e)
			{
				throw new GlobalException("Estatutos invalidos o nulos");
			}
		}
		if (activo==null)
		{
			return null;
		}
		return activo;
	}
  
}
