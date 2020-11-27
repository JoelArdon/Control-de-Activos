/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.logic.AccesoADatos;

/**
 *
 * @author Administrador
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Joel
 */


public class Servicio {

    protected Connection conexion= null;
    
    public Servicio() {
        
    }
    
    public  void conectar() throws SQLException,ClassNotFoundException 
    {
            Class.forName("oracle.jdbc.driver.OracleDriver");
       // try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
            //conexion = getJdbcMydbsource();
       /* } catch (NamingException ex) {
            ex.printStackTrace();
        }*/
        
    }
    
    protected void desconectar() throws SQLException{
        if(!conexion.isClosed())
        {
            conexion.close();
        }
    }

    private Connection getJdbcMydbsource() throws NamingException {
        Context c = new InitialContext();
        try {
            return ((DataSource) c.lookup("jdbc/Mydbsource")).getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
