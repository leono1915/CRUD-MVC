/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Proovedores;
import Modelo.controlBaseDeDatos;
import Vista.frmProovedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author estrella
 */
public class ctrlProovedores implements ActionListener {
 controlBaseDeDatos ctrlB;
 Proovedores pro;
 frmProovedores frmP;

    public ctrlProovedores(controlBaseDeDatos ctrlB, Proovedores pro, frmProovedores frmP) {
        this.ctrlB = ctrlB;
        this.pro = pro;
        this.frmP = frmP;
        this.frmP.btnGuardar.addActionListener(this);
        this.frmP.btnModificar.addActionListener(this);
        this.frmP.btnEliminar.addActionListener(this);
        this.frmP.btnLimpiar.addActionListener(this);
        this.frmP.btnBuscar.addActionListener(this);
        this.frmP.btnSalir.addActionListener(this);
    }
   public void iniciar(){
     frmP.setTitle("Control de Proovedores");
     frmP.setVisible(true);
     frmP.txtId.setVisible(false);
     frmP.setLocationRelativeTo(null);
    }
   public void limpiar(){
      frmP.txtDomicilio.setText(null);
      frmP.txtGiro.setText(null);
      frmP.txtCodigo.setText(null);
      frmP.txtNombre.setText(null);
      frmP.txtTelefono.setText(null);
      frmP.txtId.setText(null);
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
    @Override
    public void actionPerformed(ActionEvent et) {
         if (et.getSource() == frmP.btnGuardar) {
           
            
             boolean bandera=false;
            
           
            if(!validarEntrada(frmP.txtTelefono.getText())||(frmP.txtTelefono.getText().length()>12||frmP.txtTelefono.getText().length()<8)){
                bandera=true;
                JOptionPane.showMessageDialog(null,"el numero telefónico no puede tener letras y debe ser de longitud válida");
            }
           if(frmP.txtCodigo.getText().length()!=10){
               JOptionPane.showMessageDialog(null,"El código debe tener 10 caracteres para ser válido");
               return;
           }
           if(frmP.txtNombre.getText().isEmpty()||frmP.txtDomicilio.getText().isEmpty()
                   ||frmP.txtGiro.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                bandera=true;
           }
            if(bandera==false){
             pro.setCodigo(frmP.txtCodigo.getText());
             pro.setNombreEmpresa(frmP.txtNombre.getText());
            pro.setDomcilio(frmP.txtDomicilio.getText());
            pro.setTelefono(frmP.txtTelefono.getText());
            
             
            pro.setGiro(frmP.txtGiro.getText());
            if(ctrlB.registrarProovedores(pro))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar verifique si el código no está repetido");
                limpiar();
            }
            }
        }
        
        if (et.getSource() == frmP.btnModificar) {
             
             if(!validarEntrada(frmP.txtTelefono.getText())){
                JOptionPane.showMessageDialog(null,"el numero telefónico no es valido");
                
             }
             else if(frmP.txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
            pro.setCodigo(frmP.txtCodigo.getText());     
            pro.setNombreEmpresa(frmP.txtNombre.getText());
            pro.setDomcilio(frmP.txtDomicilio.getText());
            pro.setTelefono(frmP.txtTelefono.getText());
            pro.setGiro(frmP.txtGiro.getText());
            
            if(ctrlB.modificarProovedor(pro))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar verifique si el código no está repetido");
                limpiar();
            }
             }
        }
        
        if (et.getSource() == frmP.btnEliminar) {
             if(frmP.txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
            pro.setId(Integer.parseInt(frmP.txtId.getText()));
            
            if(ctrlB.eliminarProovedor(pro))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
             }
        }
        
        if (et.getSource() == frmP.btnBuscar) {
             if(!frmP.txtCodigo.getText().isEmpty()){
                 
             }
             else{
            if(frmP.txtGiro.getText().isEmpty()){
                frmP.iniciarTabla();
                frmP.mostrarTabla(frmP.txtNombre.getText());
            }else{
                frmP.iniciarTabla();
                frmP.mostrarTabla(frmP.txtGiro.getText());
            }
             }
           /* }
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
            }*/
        }
        
        
        
        if(et.getSource()==frmP.btnLimpiar){
            limpiar();
            frmP.limpiarTabla();
        }
     if(et.getSource()==frmP.btnSalir){
            frmP.setVisible(false);
        }
    }
 
}
