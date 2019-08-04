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
public class Facturas {
  private int id,total;
  private String folio,cliente,fecha;

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public String getFolio() {
        return folio;
    }

    public String getCliente() {
        return cliente;
    }

    public String getFecha() {
        return fecha;
    }
  
  
}
