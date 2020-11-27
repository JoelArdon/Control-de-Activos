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
public class Categoria {

    public Categoria() {
    }
        private int codigo;
    private String nombre;
    private int consecutivo;

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Categoria(int codigo, int consecutivo,String nombre ) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.consecutivo = consecutivo;
    }

    public Categoria(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        
    }

}
