/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Activos.logic;


import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class Funcionario {
   private String id;
   private String nombre;
   private Dependencia dependencia;


    public Funcionario(String id, String nombre, Dependencia dependencia) {
        this.id = id;
        this.nombre = nombre;
        this.dependencia = dependencia;
   
    }

    public Funcionario() {
       
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }
  
}
 
    
