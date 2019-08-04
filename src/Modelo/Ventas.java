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
public class Ventas {
   private int id;
   private String producto,fecha,cliente;
   private int total;
   private String facturado;

    public void setId(int id) {
        this.id = id;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setFacturado(String facturado) {
        this.facturado = facturado;
    }

    public int getId() {
        return id;
    }

    public String getProducto() {
        return producto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public int getTotal() {
        return total;
    }

    public String getFacturado() {
        return facturado;
    }
   
}
