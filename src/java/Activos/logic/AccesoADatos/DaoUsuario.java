/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic.AccesoADatos;



import Activos.logic.Funcionario;
import Activos.logic.Usuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author Pc
 */
public class DaoUsuario extends Servicio {
      private static final String INSERTARUSUARIO ="{call INSERTAR_USUARIO(?,?,?,?)}";
      private static final String BUSCARUSUARIO = "{?=call BUSCAR_USUARIO(?)}";
      private static final String LISTARUSUARIO = "{?=call LISTAR_USUARIOS()}";
      private static final String LISTAREGISTRADOR = "{?=call LISTAR_REGISTRADORES()}";
      
      
      public  void insertar_Usuario(Usuario usu)throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARUSUARIO);
            pstmt.setString(1,usu.getId());
            pstmt.setString(2,usu.getClave());
            pstmt.setString(3,usu.getRol());
            pstmt.setString(4,usu.getFuncionario().getId());
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
       public Usuario buscarUsuario(String id) throws GlobalException, NoDataException
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
                DaoFuncionario  daofun = new DaoFuncionario();
		Usuario usu = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(BUSCARUSUARIO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, id);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				usu = new Usuario(rs.getString("id"),
									   rs.getString("clave"), rs.getString("cargo"),
                                        
									  daofun.buscarFuncionario(rs.getString("id_funcionario"))); 
      
                                                                           
				coleccion.add(usu);
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
		return usu;
	}
        public ArrayList listarUsuario() throws GlobalException, NoDataException
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
                DaoFuncionario  daofun = new DaoFuncionario();
		Usuario usu = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARUSUARIO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				usu = new Usuario(rs.getString("id"),
									   rs.getString("clave"), rs.getString("cargo"),
                                        
									  daofun.buscarFuncionario(rs.getString("id_funcionario"))); 
      
                                                                           
				coleccion.add(usu);
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
         public ArrayList listarRegistradores() throws GlobalException, NoDataException
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
                DaoFuncionario  daofun = new DaoFuncionario();
		Usuario usu = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTAREGISTRADOR);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				usu = new Usuario(rs.getString("id"),
									   rs.getString("clave"), rs.getString("cargo"),
                                        
									  daofun.buscarFuncionario(rs.getString("id_funcionario"))); 
      
                                                                           
				coleccion.add(usu);
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
