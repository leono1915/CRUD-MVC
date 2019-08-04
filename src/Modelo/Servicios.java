/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author estrella
 */
public class Servicios {
    private int id;
    private String descripcion,fecha,hora;
    private double precio;
    private String tipo;

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public String getHora() {
        return hora;
    }

    public String getTipo() {
        return tipo;
    }
    
}
