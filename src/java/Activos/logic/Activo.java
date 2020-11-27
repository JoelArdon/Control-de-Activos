/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic;

/**
 *
 * @author Pc
 */
public class Activo {

    public Activo(String codigo, Bien bien, Categoria categoria, String puesto, String funcionario) {
        this.codigo = codigo;
        this.bien = bien;
        this.categoria = categoria;
        this.puesto = puesto;
        this.funcionario = funcionario;
    }
 private String codigo;

    public Activo(String codigo, String puesto, String funcionario) {
        this.codigo = codigo;
        this.puesto = puesto;
        this.funcionario = funcionario;
    }
    private Bien bien;
    private Dependencia dependencia;
    private Categoria categoria;
    private String puesto;
    private String funcionario;
    public Activo() {
    }

    public Activo(String codigo, Bien bien, Dependencia dependencia, Categoria categoria, String puesto, String funcionario) {
        this.codigo = codigo;
        this.bien = bien;
        this.dependencia = dependencia;
        this.categoria = categoria;
        this.puesto = puesto;
        this.funcionario = funcionario;
    }

    public String getCodigo() {
        return codigo;
    }

    public Bien getBien() {
        return bien;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }
   
            
    
}
