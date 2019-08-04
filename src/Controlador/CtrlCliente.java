/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.controlBaseDeDatos;
import Modelo.Cliente;
import Vista.frmCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author estrella
 */
public  class CtrlCliente implements ActionListener{
       
    private Cliente cli;
    private controlBaseDeDatos modC;
    private frmCliente frmc;
       
       public CtrlCliente (Cliente cli,controlBaseDeDatos modC,frmCliente frmc){
        this.cli=cli;
        this.frmc=frmc;
        this.modC=modC;
        this.frmc.btnGuardar.addActionListener(this);
        this.frmc.btnModificar.addActionListener(this);
        this.frmc.btnEliminar.addActionListener(this);
        this.frmc.btnLimpiar.addActionListener(this);
        this.frmc.btnBuscar.addActionListener(this);
        this.frmc.btnSalir.addActionListener(this);
    }
   public void iniciarCliente(){
        frmc.setTitle("Control de  Clientes");
        frmc.setLocationRelativeTo(null);
        frmc.txtId.setVisible(false);
    }
        
   public boolean validarEntrada(String nombre){
      boolean bandera=false;
      
       for(int i=0;i<nombre.length();i++){
          
           if( !Character.isDigit(nombre.charAt(i))){
             bandera=false;
             break;
       }else{
                bandera=true;
            }
           
       }
       return bandera;
   }   
    public void limpiar()
    {
        frmc.txtId.setText(null);
        frmc.txtCodigo.setText(null);
        frmc.txtNombre.setText(null);
        frmc.txtDomicilio.setText(null);
        frmc.txtTelefono.setText(null);
        frmc.txtRfc.setText(null);
      
    }
       
    @Override
        public void actionPerformed(ActionEvent et) {
     
        if (et.getSource() == frmc.btnGuardar) {
           
            cli.setCodigo(frmc.txtCodigo.getText());
            cli.setNombre(frmc.txtNombre.getText());
            cli.setDomicilio(frmc.txtDomicilio.getText());
             
            cli.setRfc(frmc.txtRfc.getText());
             boolean bandera=false;
            cli.setTelefono(frmc.txtTelefono.getText());
            if(frmc.txtRfc.getText().length()!=13){
             JOptionPane.showMessageDialog(null,"el rfc debe tener 13 caracteres para ser válido");
             
            }
            if(!validarEntrada(frmc.txtTelefono.getText())||(frmc.txtTelefono.getText().length()>12||frmc.txtTelefono.getText().length()<8)){
                bandera=true;
                JOptionPane.showMessageDialog(null,"el numero telefónico no puede tener letras y debe ser de longitud válida");
            }
           
           if(frmc.txtCodigo.getText().isEmpty()||frmc.txtRfc.getText().isEmpty()
                   ||frmc.txtDomicilio.getText().isEmpty()||frmc.txtNombre.getText().isEmpty()
                   ||frmc.txtTelefono.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                bandera=true;
           }
            if(frmc.txtRfc.getText().length()==13&&bandera==false){
            if(modC.registrarCliente(cli))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar verifique si el código no está repetido");
                limpiar();
            }
            }
        }
        
        if (et.getSource() == frmc.btnModificar) {
             
             if(!validarEntrada(frmc.txtTelefono.getText())){
                JOptionPane.showMessageDialog(null,"el numero telefónico no es valido");
                
             }
             else if(frmc.txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
                 
             cli.setCodigo(frmc.txtCodigo.getText());
             cli.setNombre(frmc.txtNombre.getText());
             cli.setDomicilio(frmc.txtDomicilio.getText());
             cli.setRfc(frmc.txtRfc.getText());
             cli.setTelefono(frmc.txtTelefono.getText());
            if(modC.modificarCliente(cli))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar verifique si el código no está repetido");
                limpiar();
            }
             }
        }
        
        if (et.getSource() == frmc.btnEliminar) {
             if(frmc.txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
            cli.setId(Integer.parseInt(frmc.txtId.getText()));
            
            if(modC.eliminarCliente(cli))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
             }
        }
        
        if (et.getSource() == frmc.btnBuscar) {
            cli.setCodigo(frmc.txtCodigo.getText());
            if(frmc.txtCodigo.getText().isEmpty()){
                frmc.iniciarTabla();
                frmc.mostrarTabla(frmc.txtNombre.getText());
                
            }
            else{
            if(modC.buscarCliente(cli))
            {
                frmc.txtId.setText(String.valueOf(cli.getId()));
                frmc.txtCodigo.setText(cli.getCodigo());
                frmc.txtNombre.setText(cli.getNombre());
                frmc.txtDomicilio.setText(cli.getDomicilio());
                frmc.txtRfc.setText(cli.getRfc());
                frmc.txtTelefono.setText(cli.getTelefono());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro registro");
                limpiar();
            }
            }
        }
        
        if (et.getSource() == frmc.btnLimpiar) {
            limpiar();
            frmc.limpiarTabla();
           
        }
        if(et.getSource()==frmc.btnSalir){
             frmc.setVisible(false);
        }

    }
       
   } 
