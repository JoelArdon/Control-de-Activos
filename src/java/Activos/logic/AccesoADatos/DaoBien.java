/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic.AccesoADatos;

import Activos.logic.Bien;
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
public class DaoBien extends Servicio {
    private static final String INSERTARBIEN= "{call INSERTAR_BIEN(?,?,?,?,?,?,?)}";
    private static final String ELIMINARBIEN ="{call ELIMINAR_BIEN(?)}";
    private static final String BUSCARBIEN = "{?=call BUSCAR_BIEN(?)}";
    private static final String MODIFICARBIEN="{call MODIFICAR_BIEN(?,?,?,?,?,?)}";
    private static final String LISTARBIEN = "{?=call LISTAR_BIENES()}";
    private static final String LISTAR_BIEN_SOLICITUD = "{?=call LISTAR_BIENES_SOLICITUD(?)}";
    
    
    public void insertarBien(Bien bien) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARBIEN); 
            pstmt.setInt(1,bien.getNumero());
            pstmt.setString(2,bien.getDescripcion());
            pstmt.setString(3,bien.getModelo());
            pstmt.setInt(4, bien.getPrecio());
            pstmt.setInt(5, bien.getCantidad());
            pstmt.setInt(6, bien.getSolicitud().getNumero());
            pstmt.setString(7, bien.getMarca());
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
    
     public void eliminarBien(int serial) throws GlobalException, NoDataException
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
			pstmt = conexion.prepareStatement(ELIMINARBIEN);
			pstmt.setInt(1,serial );

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
     
      public void modificarBien(Bien bien) throws GlobalException, NoDataException
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
                    pstmt = conexion.prepareStatement(MODIFICARBIEN);
                    pstmt.setInt(1, bien.getNumero());
                    pstmt.setString(2, bien.getDescripcion());
                    pstmt.setString(3, bien.getModelo());
                    pstmt.setFloat(4, bien.getPrecio());
                    pstmt.setInt(5, bien.getCantidad());
                    pstmt.setInt(6, bien.getSolicitud().getNumero());

			
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
      public Bien buscarBien(int serial) throws GlobalException, NoDataException
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
                DaoSolicitud daosoli = new DaoSolicitud();
		Bien bien = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(BUSCARBIEN);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setInt(2, serial);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				bien = new Bien(rs.getInt("numero"),
									   rs.getString("descripcion"),
									   rs.getString("modelo"),rs.getString("marca"),
                                                                           rs.getInt("precio"),rs.getInt("cantidad"),daosoli.buscarSolicitud(rs.getInt("num_solicitud"))); 
                                                                           
				coleccion.add(bien);
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
		if (bien==null)
		{
			return null;
		}
		return bien;
	}
      public ArrayList listarBien() throws GlobalException, NoDataException
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
                DaoSolicitud daosoli = new DaoSolicitud();
		Bien bien= null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARBIEN);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				bien = new Bien(rs.getInt("numero"),
									   rs.getString("descripcion"),
									   rs.getString("modelo"),rs.getString("marca"),
                                                                           rs.getInt("precio"),rs.getInt("cantidad"),daosoli.buscarSolicitud(rs.getInt("num_solicitud"))); 
                                                                           
				
				coleccion.add(bien);
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
		if (coleccion == null || coleccion.size()== 0)
		{
			throw new NoDataException("No hay datos");
		}
		return coleccion;
	}
        public ArrayList<Bien> ListarBienesSolicitud(int numsolicitud)throws GlobalException, NoDataException
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
		ArrayList<Bien> coleccion = new ArrayList();
                 DaoSolicitud daosoli = new DaoSolicitud();
		Bien bien = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTAR_BIEN_SOLICITUD);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        pstmt.setInt(2, numsolicitud);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
					bien = new Bien(rs.getInt("numero"),
									   rs.getString("descripcion"),
									   rs.getString("modelo"),rs.getString("marca"),
                                                                           rs.getInt("precio"),rs.getInt("cantidad"),daosoli.buscarSolicitud(rs.getInt("num_solicitud"))); 
                                                                           
                                coleccion.add(bien);
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
