/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleados;
import Modelo.controlBaseDeDatos;
import Vista.frmEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author estrella
 */
public class ctrlEmpleados  implements ActionListener {
 private Empleados empleado;
 private frmEmpleado frmE;
 private controlBaseDeDatos ctrlB;

 public ctrlEmpleados(Empleados empleado, frmEmpleado frmE, controlBaseDeDatos ctrlB) {
        this.empleado = empleado;
        this.frmE = frmE;
        this.ctrlB = ctrlB;
        this.frmE.btnGuardar.addActionListener(this);
        this.frmE.btnModificar.addActionListener(this);
        this.frmE.btnEliminar.addActionListener(this);
        this.frmE.btnLimpiar.addActionListener(this);
        this.frmE.btnBuscar.addActionListener(this);
        this.frmE.btnSalir.addActionListener(this);
        
    }
   public void iniciar(){
    frmE.setTitle("Control de Empleados");
    frmE.setLocationRelativeTo(null);
    frmE.txtId.setVisible(false);
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
    public void limpiar(){
     
     
     frmE.txtId.setText(null);
     frmE.txtNss.setText(null);
     frmE.txtNombre.setText(null);
     
     frmE.txtDomicilio.setText(null);
     frmE.txtFechaIngreso.setText(null);
     frmE.txtFechaNacimiento.setText(null);
     frmE.txtTelefono.setText(null);
     frmE.limpiarTabla();
    }
    private static boolean fechavalida(String fecha){
        try{
            SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
            
        }catch(ParseException e){
           return false; 
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==frmE.btnGuardar){
         boolean bandera=false;
          if(frmE.txtNss.getText().length()!=11){
             JOptionPane.showMessageDialog(null,"el NSS debe tener 11 digitos para ser válido");
             bandera=true;
            }
           if(!fechavalida(frmE.txtFechaIngreso.getText())||!fechavalida(frmE.txtFechaNacimiento.getText())){
               JOptionPane.showMessageDialog(null, "el formato de fecha no es valido");
              return; 
           }
            if(!validarEntrada(frmE.txtTelefono.getText())||(frmE.txtTelefono.getText().length()>12||frmE.txtTelefono.getText().length()<8)){
                bandera=true;
                JOptionPane.showMessageDialog(null,"el numero telefónico no puede tener letras y debe ser de longitud válida");
            }
           
           if(frmE.txtNss.getText().isEmpty()||frmE.txtDomicilio.getText().isEmpty()
                   ||frmE.txtFechaIngreso.getText().isEmpty()||frmE.txtNombre.getText().isEmpty()
                   ||frmE.txtFechaNacimiento.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                bandera=true;
           }
          if(bandera==false){
         empleado.setNss(frmE.txtNss.getText());
         empleado.setNombre(frmE.txtNombre.getText());
         empleado.setDomicilio(frmE.txtDomicilio.getText());
         empleado.setFechaIngreso(frmE.txtFechaIngreso.getText());
         empleado.setFechaNacimiento(frmE.txtFechaNacimiento.getText());
         empleado.setTelefono(frmE.txtTelefono.getText());
         if(ctrlB.registrarEmpleado(empleado)){
             JOptionPane.showMessageDialog(null,"registro guardado");
             limpiar();
         }
         else{
             JOptionPane.showMessageDialog(null,"no se pudo guardar verifique si el código no está repetido");
             limpiar();
         }
          }
       }
       
        if (e.getSource() == frmE.btnModificar) {
         if(frmE.txtId.getText().isEmpty()){
             JOptionPane.showMessageDialog(null,"Debe seleccionar un registro");
         } 
         else{
         empleado.setId(Integer.parseInt(frmE.txtId.getText()));
         empleado.setNss(frmE.txtNss.getText());
         empleado.setNombre(frmE.txtNombre.getText());
         empleado.setDomicilio(frmE.txtDomicilio.getText());
         empleado.setFechaIngreso(frmE.txtFechaIngreso.getText());
         empleado.setFechaNacimiento(frmE.txtFechaNacimiento.getText());
         empleado.setTelefono(frmE.txtTelefono.getText());
            
            if(ctrlB.modificarEmpleado(empleado))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo Modificar verifique si el código no está repetido");
                limpiar();
            }
        }
        }
        if (e.getSource() == frmE.btnEliminar) {
           
            if(frmE.txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Debe selecionar un registro");
            }else{
                 empleado.setId(Integer.parseInt(frmE.txtId.getText()));
            if(ctrlB.eliminarEmpleado(empleado))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }
        }
        
        if (e.getSource() == frmE.btnBuscar) {
           
         empleado.setNss(frmE.txtNss.getText());
        if(frmE.txtNss.getText().isEmpty()){
          frmE.iniciarTabla();
          frmE.mostrarTabla(frmE.txtNombre.getText());
          
        }else{
            if(ctrlB.buscarEmpleado(empleado))
            {
         frmE.txtId.setText(String.valueOf(empleado.getId()));
         frmE.txtNombre.setText(empleado.getNombre());
         frmE.txtDomicilio.setText(empleado.getDomicilio());
         frmE.txtFechaIngreso.setText(empleado.getFechaIngreso());
         frmE.txtFechaNacimiento.setText(empleado.getFechaNacimiento());
         frmE.txtTelefono.setText(empleado.getTelefono());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró registro");
                limpiar();
            }
        }
        }
        if (e.getSource() == frmE.btnLimpiar) {
            limpiar();
           
        }
        if(e.getSource()==frmE.btnSalir){
            frmE.setVisible(false);
        }
        
    }
 
}
