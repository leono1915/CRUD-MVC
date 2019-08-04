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
public class Cliente {
    
    private int id;
    private String codigo;
    private String nombre;
    private String domicilio;
    private String rfc;
    private String telefono;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getDomicilio(){
        return domicilio;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setDomicilio(String domicilio){
        this.domicilio=domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc=rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono=telefono;
    }
    
}
