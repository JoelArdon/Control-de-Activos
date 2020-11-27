/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic.AccesoADatos;

import Activos.logic.Dependencia;
import Activos.logic.Funcionario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.internal.OracleTypes;

/**
 *
 * @author Pc
 */
public class DaoFuncionario extends Servicio{
      private static final String INSERTARFUNCIONARIO= "{call INSERTAR_FUNCIONARIO(?,?,?)}";
      private static final String BUSCARFUNCIONARIO = "{?=call BUSCAR_FUNCIONARIO(?)}";
      private static final String LISTARFUNCIONARIO = "{?=call LISTAR_FUNCIONARIO()}";
    
      
      public  void insertar_Funcionario(Funcionario fun)throws GlobalException, NoDataException{
        try {
            conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARFUNCIONARIO);
            pstmt.setString(1,fun.getId());
            pstmt.setString(2,fun.getNombre());
            pstmt.setInt(3,fun.getDependencia().getCondigo());
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
       public Funcionario buscarFuncionario(String id) throws GlobalException, NoDataException
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
                DaoDependencia  daodepen = new DaoDependencia();
		Funcionario fun = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(BUSCARFUNCIONARIO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			pstmt.setString(2, id);
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				fun = new Funcionario(rs.getString("id"),
									   rs.getString("nombre"),
									  daodepen.buscarDependencia(rs.getInt("dependencia"))); 
      
                                                                           
				coleccion.add(fun);
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
		return fun;
	}
        public ArrayList listarFuncionario() throws GlobalException, NoDataException
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
                DaoDependencia  daodepen = new DaoDependencia();
		Funcionario fun = null;
		CallableStatement pstmt = null;
		try
		{
			pstmt = conexion.prepareCall(LISTARFUNCIONARIO);
			pstmt.registerOutParameter(1, OracleTypes.CURSOR);
			
			pstmt.execute();
			rs = (ResultSet)pstmt.getObject(1);
			while (rs.next())
			{
				fun = new Funcionario(rs.getString("id"),
									   rs.getString("nombre"),
									  daodepen.buscarDependencia(rs.getInt("dependencia"))); 
      
                                                                           
				coleccion.add(fun);
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
