/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Ventas;
import Modelo.controlBaseDeDatos;
import Vista.frmVentas;
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
public class ctrlVentas implements ActionListener{
controlBaseDeDatos ctrlB;
frmVentas frmV;
Ventas ve;

    public ctrlVentas(controlBaseDeDatos ctrlB, frmVentas frmV, Ventas ve) {
        this.ctrlB = ctrlB;
        this.frmV = frmV;
        this.ve = ve;
        this.frmV.btnGuardar.addActionListener(this);
        this.frmV.btnModificar.addActionListener(this);
        this.frmV.btnEliminar.addActionListener(this);
        this.frmV.btnLimpiar.addActionListener(this);
        this.frmV.btnBuscar.addActionListener(this);
        this.frmV.btnSalir.addActionListener(this);
    }
     public void iniciar(){
     frmV.setTitle("Control de Ventas");
     frmV.setVisible(true);
     frmV.txtBuscar.setVisible(false);
     frmV.setLocationRelativeTo(null);
    }
   public void limpiar(){
      frmV.txtTotal.setText(null);
      frmV.txtFechaVenta.setText(null);
      frmV.txtFacturado.setText(null);
      frmV.txtCliente.setText(null);
      frmV.txtProducto.setText(null);
      frmV.TotalVentas.setText(null);
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
        if (et.getSource() == frmV.btnGuardar) {
           
           if(!fechavalida(frmV.txtFechaVenta.getText())){
               JOptionPane.showMessageDialog(null, "el formato de fecha no es valido");
              return; 
           }
             boolean bandera=false;
            
           
          
           
           if(frmV.txtProducto.getText().isEmpty()||frmV.txtFechaVenta.getText().isEmpty()
                   ||frmV.txtCliente.getText().isEmpty()||frmV.txtTotal.getText().isEmpty()
                   ||frmV.txtFacturado.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                bandera=true;
           }
            if(bandera==false){
               ve.setProducto(frmV.txtProducto.getText());
            ve.setFecha(frmV.txtFechaVenta.getText());
            ve.setCliente(frmV.txtCliente.getText());
             
            ve.setTotal(Integer.parseInt(frmV.txtTotal.getText()));
            ve.setFacturado(frmV.txtFacturado.getText());
            if(ctrlB.registrarVentas(ve))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar verifique si el código no está repetido");
                limpiar();
            }
            }
        }
        
        if (et.getSource() == frmV.btnModificar) {
             
             
              if(frmV.txtBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
                 
            ve.setProducto(frmV.txtProducto.getText());
            ve.setFecha(frmV.txtFechaVenta.getText());
            ve.setCliente(frmV.txtCliente.getText());
             
            ve.setTotal(Integer.parseInt(frmV.txtTotal.getText()));
            ve.setFacturado(frmV.txtFacturado.getText());
            
            if(ctrlB.modificarVentas(ve))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar verifique si el código no está repetido");
                limpiar();
            }
             }
        }
        
        if (et.getSource() == frmV.btnEliminar) {
            
            ve.setId(Integer.parseInt(frmV.txtBuscar.getText()));
           
            if(ctrlB.eliminarVenta(ve))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
             }
        
        
        if (et.getSource() == frmV.btnBuscar) {
           
           
               if(!frmV.txtFacturado.getText().isEmpty()){
                frmV.mostrarTabla(frmV.txtFacturado.getText());
                
                 frmV.TotalVentas.setText("$"+String.valueOf(frmV.dameTotal()));
                  frmV.iniciarTabla();
           }else{
                    if(frmV.txtCliente.getText().isEmpty()||frmV.txtFechaVenta.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar fecha y cliente ");
              return;
            } 
                  
              ve.setCliente(frmV.txtCliente.getText());
               ve.setFecha(frmV.txtFechaVenta.getText());
            if(ctrlB.buscarVentas(ve))
            {
                frmV.txtBuscar.setText(String.valueOf(ve.getId()));
                frmV.txtProducto.setText(ve.getProducto());
                frmV.txtFechaVenta.setText(ve.getFecha());
                frmV.txtCliente.setText(ve.getCliente());
                frmV.txtTotal.setText(String.valueOf(ve.getTotal()));
                frmV.txtFacturado.setText(ve.getFacturado());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro registro");
                limpiar();
            }
            
               }
        }
        

        if(et.getSource()==frmV.btnLimpiar){
            limpiar();
            frmV.limpiarTabla();
        }
     if(et.getSource()==frmV.btnSalir){
            frmV.setVisible(false);
        }

}
}


