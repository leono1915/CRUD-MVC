/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Servicios;
import Modelo.controlBaseDeDatos;
import Vista.frmServicios;
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
public class ctrlServicios implements ActionListener{
  controlBaseDeDatos ctrlB;
  Servicios ser;
  frmServicios frmS; 

    public ctrlServicios(controlBaseDeDatos ctrlB, Servicios ser, frmServicios frmS) {
        this.ctrlB = ctrlB;
        this.ser = ser;
        this.frmS = frmS;
        this.frmS.btnGuardar.addActionListener(this);
        this.frmS.btnModificar.addActionListener(this);
        this.frmS.btnEliminar.addActionListener(this);
        this.frmS.btnLimpiar.addActionListener(this);
        this.frmS.btnBuscar.addActionListener(this);
        this.frmS.btnSalir.addActionListener(this);
    }
     public void iniciar(){
     frmS.setTitle("Control de Servicios");
     frmS.setVisible(true);
     frmS.txtBuscar.setVisible(false);
     frmS.setLocationRelativeTo(null);
    }
   public void limpiar(){
      frmS.txtBuscar.setText(null);
      frmS.txtDescripcion.setText(null);
      frmS.txtFecha.setText(null);
      frmS.txtHora.setText(null);
      frmS.txtPrecio.setText(null);
      frmS.txtTipo.setText(null);
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
       if (et.getSource() == frmS.btnGuardar) {
           
           if(!fechavalida(frmS.txtFecha.getText())){
               JOptionPane.showMessageDialog(null, "el formato de fecha no es valido");
              return; 
           }
             boolean bandera=false;
            
           
          
           
           if(frmS.txtPrecio.getText().isEmpty()||frmS.txtTipo.getText().isEmpty()
                   ||frmS.txtFecha.getText().isEmpty()||frmS.txtHora.getText().isEmpty()
                   ||frmS.txtDescripcion.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                bandera=true;
           }
            if(bandera==false){
            ser.setPrecio(Double.parseDouble(frmS.txtPrecio.getText()));
            ser.setTipo(frmS.txtTipo.getText());
            ser.setFecha(frmS.txtFecha.getText());
             
            ser.setHora(frmS.txtHora.getText());
            ser.setDescripcion(frmS.txtDescripcion.getText());
            if(ctrlB.registrarServicio(ser))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar verifique si el código no está repetido");
                limpiar();
            }
            }
        }
        
        if (et.getSource() == frmS.btnModificar) {
             
             ser.setId(Integer.parseInt(frmS.txtBuscar.getText()));
              if(frmS.txtBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
             
             ser.setPrecio(Double.parseDouble(frmS.txtPrecio.getText()));
            ser.setTipo(frmS.txtTipo.getText());
            ser.setFecha(frmS.txtFecha.getText());
             
            ser.setHora(frmS.txtHora.getText());
            ser.setDescripcion(frmS.txtDescripcion.getText());
            if(ctrlB.modificarServicio(ser))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar verifique si el código no está repetido");
                limpiar();
            }
             }
        }
        
        if (et.getSource() == frmS.btnEliminar) {
             if(frmS.txtBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
            ser.setId(Integer.parseInt(frmS.txtBuscar.getText()));
            
            if(ctrlB.eliminarServicio(ser))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
             }
        }
        
         if (et.getSource() == frmS.btnBuscar) {
           
            if(frmS.txtFecha.getText().isEmpty()&&frmS.txtHora.getText().isEmpty()){
                frmS.iniciarTabla();
               
                frmS.mostrarTabla(frmS.txtTipo.getText());
                
                
            }else{
                ser.setFecha(frmS.txtFecha.getText());
                ser.setHora(frmS.txtHora.getText());
                if(!ctrlB.buscarServicio(ser)){
                    JOptionPane.showMessageDialog(null,"no se encontró el registro");
                }else{
                    frmS.txtBuscar.setText(String.valueOf(ser.getId()));
                    frmS.txtDescripcion.setText(ser.getDescripcion());
                    frmS.txtFecha.setText(ser.getFecha());
                    frmS.txtHora.setText(ser.getHora());
                    frmS.txtPrecio.setText(String.valueOf(ser.getPrecio()));
                    frmS.txtTipo.setText(ser.getTipo());
                    
                }
            }
         }
        if(et.getSource()==frmS.btnLimpiar){
            limpiar();
            frmS.setVisible(false);
        }
     if(et.getSource()==frmS.btnSalir){
            frmS.setVisible(false);
        }
    }
  
}
