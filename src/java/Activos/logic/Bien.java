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
public class Bien {
     private int numero;
   private String descripcion;
   private String marca;
   private String modelo;
   private int precio;
   private int cantidad;
   Solicitud solicitud;

    public Bien(int numero, String descripcion, String marca, String modelo, int precio, int cantidad, Solicitud solicitud) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.solicitud = solicitud;
    }

    public Bien() {
     this.descripcion="";
      this.marca = "";
      this.modelo="";
      
        
    }

    public int getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
  
}
