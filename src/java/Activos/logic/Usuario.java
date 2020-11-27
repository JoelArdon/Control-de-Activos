/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic;

import java.util.logging.Logger;

/**
 *
 * @author Pc
 */
public class Usuario {
 private String id;
    private String clave;
    private String rol;
    Funcionario funcionario;
    public Usuario(String id, String clave, String rol, Funcionario funcionario) {
        this.id = id;
        this.clave = clave;
        this.rol = rol;
        this.funcionario = funcionario;
    }

    public Usuario() {
        this("","","",null);
    }
    

    public String getId() {
        return id;
    }

    public String getClave() {
        return clave;
    }

    public String getRol() {
        return rol;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
 

  

   
   
}
