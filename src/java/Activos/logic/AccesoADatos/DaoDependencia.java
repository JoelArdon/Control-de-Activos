/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic.AccesoADatos;


import Activos.logic.Bien;
import Activos.logic.Dependencia;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author Pc
 */
public class  DaoDependencia extends Servicio {
    
    private static final String INSERTARDEPENDENCIA= "{call INSERTAR_DEPENDENCIA(?,?,?)}";
      private static final String BUSCARDEPENDENCIA = "{?=call BUSCAR_DEPENDENCIA(?)}";
      private static final String LISTARDEPENDENCIA = "{?=call LISTAR_DEPENDENCIAS()}";
    
    
    public  void insertar_Dependencia(Dependencia dep)throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARDEPENDENCIA);
            pstmt.setInt(1,dep.getCondigo());
            pstmt.setString(2,dep.getNombre());
            pstmt.setString(3,dep.getAdministrador());
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
    public Dependencia buscarDependencia(int codigo) throws GlobalException, NoDataException
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
		Dependencia dep = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(BUSCARDEPENDENCIA);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setInt(2, codigo);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				dep = new Dependencia(rs.getInt("codigo"),
									   rs.getString("nombre"),
									   rs.getString("administrador")); 
      
                                                                           
				coleccion.add(dep);
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
		if (coleccion == null || coleccion.size() == 0)
		{
			throw new NoDataException("No hay datos");
		}
		return dep;
	}
      public ArrayList listarDependencia() throws GlobalException, NoDataException
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
		Dependencia dep = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARDEPENDENCIA);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				dep = new Dependencia(rs.getInt("codigo"),
									   rs.getString("nombre"),
									   rs.getString("administrador")); 
      
                                                                           
				coleccion.add(dep);
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
		if (coleccion == null || coleccion.size() == 0)
		{
			throw new NoDataException("No hay datos");
		}
		return coleccion;
	}
}
