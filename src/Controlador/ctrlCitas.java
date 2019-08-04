/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Citas;
import Modelo.controlBaseDeDatos;
import Vista.frmCitas;
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
public class ctrlCitas implements ActionListener{
 controlBaseDeDatos ctrlB;  
 Citas ci;
 frmCitas frmC;

    public ctrlCitas(controlBaseDeDatos ctrlB, Citas ci, frmCitas frmC) {
        this.ctrlB = ctrlB;
        this.ci = ci;
        this.frmC = frmC;
        this.frmC.btnGuardar.addActionListener(this);
        this.frmC.btnModificar.addActionListener(this);
        this.frmC.btnEliminar.addActionListener(this);
        this.frmC.btnLimpiar.addActionListener(this);
        this.frmC.btnBuscar.addActionListener(this);
        this.frmC.btnSalir.addActionListener(this);
    }
   public void iniciar(){
        frmC.setTitle("Control de Citas");
        frmC.setLocationRelativeTo(null);
        frmC.txtBuscar.setVisible(false);
   }
   public void limpiar(){
       frmC.txtBuscar.setText(null);
       frmC.txtDescripcion.setText(null);
       frmC.txtFecha.setText(null);
       frmC.txtHora.setText(null);
       frmC.txtTipo.setText(null);
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
    public void actionPerformed(ActionEvent et) {
         if (et.getSource() == frmC.btnGuardar) {
           
          
             boolean bandera=false;
            
            if(!fechavalida(frmC.txtFecha.getText())){
               JOptionPane.showMessageDialog(null, "el formato de fecha no es valido");
              return; 
           }
          
           
           if(frmC.txtDescripcion.getText().isEmpty()
                   ||frmC.txtHora.getText().isEmpty()||frmC.txtTipo.getText().isEmpty()
                  ){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                bandera=true;
           }
            if(bandera==false){
               ci.setDescripcion(frmC.txtDescripcion.getText());
            ci.setFecha(frmC.txtFecha.getText());
            ci.setHora(frmC.txtHora.getText());
            ci.setTipo(frmC.txtTipo.getText());
             
          
            if(ctrlB.registrarCitas(ci))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar verifique si el código no está repetido");
                limpiar();
            }
            }
        }
        
        if (et.getSource() == frmC.btnModificar) {
             
             
              if(frmC.txtHora.getText().isEmpty()||frmC.txtFecha.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar  fecha y hora para modificar");
            }
             else{
                 
            ci.setDescripcion(frmC.txtDescripcion.getText());
            ci.setFecha(frmC.txtFecha.getText());
            ci.setHora(frmC.txtHora.getText());
            ci.setTipo(frmC.txtTipo.getText());
            
            if(ctrlB.modificarCita(ci))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar verifique si el código no está repetido");
                limpiar();
            }
             }
        }
        
        if (et.getSource() == frmC.btnEliminar) {
             if(frmC.txtHora.getText().isEmpty()||frmC.txtFecha.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar  fecha y hora para eliminar");
            }
             else{
            ci.setFecha(frmC.txtFecha.getText());
            ci.setHora(frmC.txtHora.getText());
            if(ctrlB.eliminarCita(ci))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
             }
        }
        
        if (et.getSource() == frmC.btnBuscar) {
           
            if(frmC.txtFecha.getText().isEmpty()){
                frmC.iniciarTabla();
                frmC.mostrarTabla(frmC.txtTipo.getText());
            }else{
                frmC.iniciarTabla();
                frmC.mostrarTabla(frmC.txtFecha.getText());
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
        
        
        if(et.getSource()==frmC.btnLimpiar){
            limpiar();
            frmC.limpiarTabla();
           
        }
        if(et.getSource()==frmC.btnSalir){
            frmC.setVisible(false);
        }
        
    }
 
}
