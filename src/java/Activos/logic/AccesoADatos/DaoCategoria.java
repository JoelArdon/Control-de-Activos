/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic.AccesoADatos;

import Activos.logic.Bien;
import Activos.logic.Categoria;
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
public class DaoCategoria extends Servicio{
     private static final String INSERTARCATEGORIA= "{call INSERTAR_CATEGORIA(?,?)}";
    private static final String LISTARCATEGORIA = "{?=call LISTAR_CATEGORIA()}";
    private static final String ELIMINARCATEGORIA ="{call ELIMINAR_CATEGORIA(?)}";
     private static final String BUSCACATEGORIA = "{?=call BUSCAR_CATEGORIA(?)}";
      private static final String MODIFICARCATEGORIA = "{call MODIFICAR_CATEGORIA(?,?,?)}";
     
     
       public void insertarCategoria(Categoria categoria) throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARCATEGORIA); 
            pstmt.setInt(1,categoria.getCodigo());
            pstmt.setString(2, categoria.getNombre());
           
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
        public void modificarCategoria(Categoria cat) throws GlobalException, NoDataException
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
                    pstmt = conexion.prepareStatement(MODIFICARCATEGORIA);
                    pstmt.setInt(1, cat.getCodigo());
                    pstmt.setString(2, cat.getNombre());
                    pstmt.setInt(3, cat.getConsecutivo());

			
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
         public ArrayList listarCategoria() throws GlobalException, NoDataException
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
                Categoria categoria = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARCATEGORIA);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				categoria = new Categoria(rs.getInt("codigo"),
									   rs.getInt("consecutivo"),
									   rs.getString("nombre"));
                                                                           
				
				coleccion.add(categoria);
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
          public void eliminarCategoria(int codigo) throws GlobalException, NoDataException
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
			pstmt = conexion.prepareStatement(ELIMINARCATEGORIA);
			pstmt.setInt(1,codigo );

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
           public Categoria buscarCategoria(int codigo) throws GlobalException, NoDataException
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
                Categoria categoria = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(BUSCACATEGORIA);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                        pstmt.setInt(2, codigo);
                         
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				categoria = new Categoria(rs.getInt("codigo"),
									   rs.getInt("consecutivo"),
									   rs.getString("nombre"));
                                                                           
				
				coleccion.add(categoria);
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
		if (categoria==null)
		{
			return null;
		}
		return categoria;
	}
}
