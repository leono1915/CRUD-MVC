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
public class Proovedores {
  private int id;
   private String nombreEmpresa,domcilio,giro;
   private String telefono;
   private String codigo;
    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public void setDomcilio(String domcilio) {
        this.domcilio = domcilio;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getDomcilio() {
        return domcilio;
    }

    public String getGiro() {
        return giro;
    }

    public String getTelefono() {
        return telefono;
    }
   
}
