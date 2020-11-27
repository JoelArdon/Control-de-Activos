/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic;
import java.util.ArrayList;

/**
 *
 * @author Pc
 */
public class Dependencia {
  private int condigo;
    private String nombre;
    private String administrador;
    private ArrayList<Solicitud> lista_solicitudes;
    public Dependencia() {
        lista_solicitudes = new ArrayList();
    }

    public Dependencia(int condigo, String nombre, String administrador) {
        this.condigo = condigo;
        this.nombre = nombre;
        this.administrador = administrador;
        lista_solicitudes = new ArrayList();
    }

    public int getCondigo() {
        return condigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setCondigo(int condigo) {
        this.condigo = condigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
  
}
