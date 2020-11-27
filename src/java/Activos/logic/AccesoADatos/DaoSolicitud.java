/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic.AccesoADatos;


import Activos.logic.Solicitud;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author Pc
 */
public class DaoSolicitud extends Servicio {
    private static final String INSERTARSOLICITUD= "{call INSERTAR_SOLICITUD(?,?,?,?,?,?,?,?,?)}";
    private static final String ELIMINARSOLICITUD ="{call ELIMINAR_SOLICITUD(?)}";
    private static final String MODIFICARSOLICITUD="{call MODIFICAR_SOLICITUD(?,?,?,?,?,?,?,?,?)}";
    private static final String BUSCARSOLICITUD = "{?=call BUSCAR_SOLICITUD(?)}";
    private static final String LISTARSOLICITUDDEPENDENCIA = "{?=call LISTAR_SOLICITUD_DEPENDENCIA(?)}";
    private static final String LISTARSOLICITUDCOMPROBANTE = "{?=call LISTAR_SOLICI_COM(?)}";
    private static final String LISTARSOLICITUDESTADO = "{?=call LISTAR_SOLICITUD_ESTADO(?)}";
      private static final String LISTARSOLICITUD = "{?=call LISTAR_SOLICITUD ()}";
       private static final String LISTARSOLICITUDPENDIENTE = "{?=call LIST_SOL_EST_VERIFI  (?)}";
       private static final String LISTARSOLICITUDREGISTRADOR = "{?=call LISTAR_SOLI_REGIS  (?)}";
       
    
    public  int insertar_Solicitud(Solicitud soli)throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
         int generatedKey = 0;
        try {
               
            pstmt = conexion.prepareCall(INSERTARSOLICITUD);
            pstmt.setInt(1,soli.getNumero());
            pstmt.setString(2, soli.getComprobante());
            pstmt.setDate(3, soli.getFecha());
            pstmt.setString(4,soli.getTipo());
            pstmt.setInt(5, soli.getCantidad());
            pstmt.setInt(6, soli.getTotal());
            pstmt.setString(7,soli.getEstado());
            pstmt.setInt(8, soli.getDependencia().getCondigo());
            pstmt.setString(9, soli.getRegistrador());
            
            
           
                  boolean resultado = pstmt.execute();
                  
        PreparedStatement ps = conexion
        .prepareStatement("select solicitud_sequence.currval from dual");
                  ResultSet rs = ps.executeQuery();
          if (rs.next()) {
          generatedKey = (int) rs.getLong(1);
            }
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
        return generatedKey;
    }
     public void eliminarSolicitud(int numSolicitud) throws GlobalException, NoDataException
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
			pstmt = conexion.prepareStatement(ELIMINARSOLICITUD);
			pstmt.setInt(1,numSolicitud );

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
      public void modificarSolicitud(Solicitud solicitud) throws GlobalException, NoDataException
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
			pstmt = conexion.prepareStatement(MODIFICARSOLICITUD);
			pstmt.setInt(1,solicitud.getNumero());
                        pstmt.setString(2,solicitud.getComprobante());
                        pstmt.setDate(3,solicitud.getFecha());
                        pstmt.setString(4,solicitud.getTipo());
                        pstmt.setInt(5,solicitud.getCantidad());
                        pstmt.setFloat(6,solicitud.getTotal());
                        pstmt.setString(7, solicitud.getEstado());
                        pstmt.setInt(8,solicitud.getDependencia().getCondigo());
                        pstmt.setString(9, solicitud.getRegistrador());
                        
			
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
       public Solicitud buscarSolicitud(int numSolicitud) throws GlobalException, NoDataException
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
                DaoDependencia daodepen = new DaoDependencia();
                DaoBien daobien = new DaoBien();
		Solicitud solicitud = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(BUSCARSOLICITUD);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setInt(2, numSolicitud);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				solicitud = new Solicitud(rs.getInt("id"),
									   rs.getString("comprobante"),
									   rs.getDate("fecha"),
                                                                           rs.getString("tipo"),rs.getInt("cantidad"),rs.getInt("total"),rs.getString("estado"),daodepen.buscarDependencia(rs.getInt("dependencia")),rs.getString("registrador")); 
                                                                           
				coleccion.add(solicitud);
                                
                                
                                           
                                
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
		if (solicitud==null)
		{
			return null;
		}
		return solicitud;
	}
       public ArrayList<Solicitud> listarSolicitudDependencia(int depdendencia) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException ex)
		{
			throw new GlobalException("No se ha localizado el Driver");
		}

		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}

		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
                DaoDependencia daodepen = new DaoDependencia();
                DaoBien daobien = new DaoBien();
		Solicitud solicitud = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARSOLICITUDDEPENDENCIA);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        pstmt.setInt(2, depdendencia);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				solicitud = new Solicitud(rs.getInt("id"),
									   rs.getString("comprobante"),
									   rs.getDate("fecha"),
                                                                           rs.getString("tipo"),rs.getInt("cantidad"),rs.getInt("total"),rs.getString("estado"),daodepen.buscarDependencia(rs.getInt("dependencia")),rs.getString("registrador")); 
                                                                          
				coleccion.add(solicitud);
                                  solicitud.setLista_bienes(daobien.ListarBienesSolicitud(rs.getInt("id")));
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
		
		return coleccion;
	}
         public ArrayList<Solicitud> listarSolicitudComp(String comp) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException ex)
		{
			throw new GlobalException("No se ha localizado el Driver");
		}

		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}

		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
                DaoDependencia daodepen = new DaoDependencia();
                DaoBien daobien = new DaoBien();
		Solicitud solicitud = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARSOLICITUDCOMPROBANTE);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        pstmt.setString(2, comp);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				solicitud = new Solicitud(rs.getInt("id"),
									   rs.getString("comprobante"),
									   rs.getDate("fecha"),
                                                                           rs.getString("tipo"),rs.getInt("cantidad"),rs.getInt("total"),rs.getString("estado"),daodepen.buscarDependencia(rs.getInt("dependencia")),rs.getString("registrador")); 
                                                                          
				coleccion.add(solicitud);
                                  solicitud.setLista_bienes(daobien.ListarBienesSolicitud(rs.getInt("id")));
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
		
		return coleccion;
	}
         public ArrayList<Solicitud> listarSolicitudEstado(String estado) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException ex)
		{
			throw new GlobalException("No se ha localizado el Driver");
		}

		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}

		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
                DaoDependencia daodepen = new DaoDependencia();
                DaoBien daobien = new DaoBien();
		Solicitud solicitud = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARSOLICITUDESTADO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        pstmt.setString(2, estado);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				solicitud = new Solicitud(rs.getInt("id"),
									   rs.getString("comprobante"),
									   rs.getDate("fecha"),
                                                                           rs.getString("tipo"),rs.getInt("cantidad"),rs.getInt("total"),rs.getString("estado"),daodepen.buscarDependencia(rs.getInt("dependencia")),rs.getString("registrador")); 
                                                                          
				coleccion.add(solicitud);
                                  solicitud.setLista_bienes(daobien.ListarBienesSolicitud(rs.getInt("id")));
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
		
		return coleccion;
	}
           public ArrayList listarSolicitud() throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException ex)
		{
			throw new GlobalException("No se ha localizado el Driver");
		}

		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}

		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
               
                
		Solicitud solicitud = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARSOLICITUD);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.execute();
                         DaoBien daobien = new DaoBien();
                            DaoDependencia daodepen = new DaoDependencia();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				solicitud = new Solicitud(rs.getInt("id"),
									   rs.getString("comprobante"),
									   rs.getDate("fecha"),
                                                                           rs.getString("tipo"),rs.getInt("cantidad"),rs.getInt("total"),rs.getString("estado"),daodepen.buscarDependencia(rs.getInt("dependencia")),rs.getString("registrador")); 
                                                                          
				coleccion.add(solicitud);
                                  solicitud.setLista_bienes(daobien.ListarBienesSolicitud(rs.getInt("id")));
                                  
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
		
		return coleccion;
	}
           public ArrayList listarSolicitudPendiente(String comprobante) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException ex)
		{
			throw new GlobalException("No se ha localizado el Driver");
		}

		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}

		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
               
                
		Solicitud solicitud = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARSOLICITUDPENDIENTE);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                         pstmt.setString(2, comprobante);
			pstmt.execute();
                         DaoBien daobien = new DaoBien();
                            DaoDependencia daodepen = new DaoDependencia();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				solicitud = new Solicitud(rs.getInt("id"),
									   rs.getString("comprobante"),
									   rs.getDate("fecha"),
                                                                           rs.getString("tipo"),rs.getInt("cantidad"),rs.getInt("total"),rs.getString("estado"),daodepen.buscarDependencia(rs.getInt("dependencia")),rs.getString("registrador")); 
                                                                          
				coleccion.add(solicitud);
                                  solicitud.setLista_bienes(daobien.ListarBienesSolicitud(rs.getInt("id")));
                                  
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
		
		return coleccion;
	}
             public ArrayList<Solicitud> listarSolicitudRegis(int registrador) throws GlobalException, NoDataException
	{
		try
		{
			conectar();
		}
		catch (ClassNotFoundException ex)
		{
			throw new GlobalException("No se ha localizado el Driver");
		}

		catch (SQLException e)
		{
			throw new NoDataException("La base de datos no se encuentra disponible");
		}

		ResultSet rs = null;
		ArrayList coleccion = new ArrayList();
                DaoDependencia daodepen = new DaoDependencia();
                DaoBien daobien = new DaoBien();
		Solicitud solicitud = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARSOLICITUDREGISTRADOR);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        pstmt.setInt(2, registrador);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				solicitud = new Solicitud(rs.getInt("id"),
									   rs.getString("comprobante"),
									   rs.getDate("fecha"),
                                                                           rs.getString("tipo"),rs.getInt("cantidad"),rs.getInt("total"),rs.getString("estado"),daodepen.buscarDependencia(rs.getInt("dependencia")),rs.getString("registrador")); 
                                                                          
				coleccion.add(solicitud);
                                  solicitud.setLista_bienes(daobien.ListarBienesSolicitud(rs.getInt("id")));
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
		
		return coleccion;
	}
}

