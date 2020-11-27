/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Activos.logic;


import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Pc
 */
public class Solicitud {
   private String comprobante;
    private Date fecha;
    private String tipo;
    private int cantidad;
    private int total;
    private String estado;
    Dependencia dependencia;
    String registrador;
    private ArrayList<Bien> lista_bienes;
    public Solicitud(int numero, String comprobante, Date fecha, String tipo, int cantidad, int total, String estado, Dependencia dependencia, String registrador, ArrayList<Bien> lista_bienes) {
        this.numero = numero;
        this.comprobante = comprobante;
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.total = total;
        this.estado = estado;
        this.dependencia = dependencia;
        this.registrador = registrador;
        this.lista_bienes = lista_bienes;
           lista_bienes = new ArrayList();
        
    }
       public Solicitud(int numero, String comprobante) {
        this.numero = numero;
        this.comprobante = comprobante;
    
       
        
    }
 private int numero;
public Solicitud(){
    this.lista_bienes = new ArrayList ();
    this.comprobante = " ";
    this.cantidad= 0;
    this.total=0;
     this.numero = 0;
     long millis=System.currentTimeMillis();  
     java.sql.Date date=new java.sql.Date(millis);  
    fecha = date;
    this.registrador = " ";

}
    public Solicitud(int numero, String comprobante, Date fecha, String tipo, int cantidad, int total, String estado, Dependencia dependencia, String registrador) {
         this.numero = numero;
        this.comprobante = comprobante;
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.total = total;
        this.estado = estado;
        this.dependencia = dependencia;
        this.registrador = registrador;
         lista_bienes = new ArrayList();
    }
 

    public ArrayList<Bien> getLista_bienes() {
        return lista_bienes;
    }

    public void setLista_bienes(ArrayList<Bien> lista_bienes) {
        this.lista_bienes = lista_bienes;
    }
   


   
    public int getNumero() {
        return numero;
    }

    public String getComprobante() {
        return comprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        int cant = 0;
        for(Bien b:lista_bienes){
            cant += b.getCantidad();
        }
        cantidad = cant;
        return cantidad;
    }

    public int getTotal() {
        int to = 0;
        for(Bien b:lista_bienes){
            to += b.getPrecio();
        }
        total = to;
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public String getRegistrador() {
        return registrador;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public void setRegistrador(String registrador) {
        this.registrador = registrador;
    
    }
}
