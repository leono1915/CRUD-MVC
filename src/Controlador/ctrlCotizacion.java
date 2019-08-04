/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cotizaciones;
import Modelo.controlBaseDeDatos;
import Vista.frmCotizaciones;
import com.oracle.jrockit.jfr.DataType;
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
public class ctrlCotizacion implements ActionListener{
    Cotizaciones co;
    frmCotizaciones frmC;
    controlBaseDeDatos ctrlB;

    public ctrlCotizacion(Cotizaciones co, frmCotizaciones frmC, controlBaseDeDatos ctrlB) {
        this.co = co;
        this.frmC = frmC;
        this.ctrlB = ctrlB;
       this.frmC.btnGuardar.addActionListener(this);
        this.frmC.btnModificar.addActionListener(this);
        this.frmC.btnEliminar.addActionListener(this);
        this.frmC.btnLimpiar.addActionListener(this);
        this.frmC.btnBuscar.addActionListener(this);
        this.frmC.btnSalir.addActionListener(this);
    }
    public void limpiar(){
       frmC.txtCliente.setText(null);
       frmC.txtDescripcion.setText(null);
       frmC.txtFecha.setText(null);
       frmC.txtId.setText(null);
       frmC.txtTotal.setText(null);
    }
    public void iniciarCotizacion(){
       frmC.setLocationRelativeTo(null);
       frmC.setTitle("Control de Cotizaciones");
       frmC.txtId.setVisible(false);
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
        if(e.getSource()==frmC.btnGuardar){
             if(!fechavalida(frmC.txtFecha.getText())){
               JOptionPane.showMessageDialog(null, "el formato de fecha no es valido");
              return; 
           }
             if(frmC.txtDescripcion.getText().isEmpty()
                   ||frmC.txtCliente.getText().isEmpty()||frmC.txtTotal.getText().isEmpty()
                  ){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                return;
           }
           co.setCliente(frmC.txtCliente.getText());
            co.setFecha(frmC.txtFecha.getText());
            co.setTotal(Double.parseDouble(frmC.txtTotal.getText()));
            co.setDescripcion(frmC.txtDescripcion.getText());
         if(ctrlB.registrarCotizacion(co)){
             JOptionPane.showMessageDialog(null,"registro guardado");
             limpiar();
         }
         else{
             JOptionPane.showMessageDialog(null,"no se pudo guardar");
             limpiar();
         }
         
       }
        if (e.getSource() == frmC.btnModificar) {
            if(frmC.txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"no se ha seleccionado registro");
                        
                return;
            }
            co.setId(Integer.parseInt(frmC.txtId.getText()));
            co.setCliente(frmC.txtCliente.getText());
            co.setFecha(frmC.txtFecha.getText());
            co.setTotal(Double.parseDouble(frmC.txtTotal.getText()));
            co.setDescripcion(frmC.txtDescripcion.getText());
            
            if(ctrlB.modificarCotizacion(co))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }
        
        if (e.getSource() == frmC.btnEliminar) {
            if(frmC.txtCliente.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"no se ha seleccionado registro");
                        
                return;
            }
            co.setCliente(frmC.txtCliente.getText());
            co.setFecha(frmC.txtFecha.getText());
            if(ctrlB.eliminarCotizacion(co))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }
        
        if (e.getSource() == frmC.btnBuscar) {
            if(!frmC.txtCliente.getText().isEmpty()&&!frmC.txtFecha.getText().isEmpty()){
                co.setCliente(frmC.txtCliente.getText());
                co.setFecha(frmC.txtFecha.getText());
                if(!ctrlB.buscarCotizacion(co)){
                   JOptionPane.showMessageDialog(null,"no se encontro registro");
                }else{
                    frmC.txtId.setText(String.valueOf(co.getId()));
                     frmC.txtTotal.setText(String.valueOf(co.getTotal()));
                     frmC.txtDescripcion.setText(co.getDescripcion());
                }
            }
            else
           if(frmC.txtCliente.getText().isEmpty()){
               frmC.iniciarTabla();
               frmC.mostrarTabla(frmC.txtFecha.getText());
           }
           else{
                frmC.iniciarTabla();
               frmC.mostrarTabla(frmC.txtCliente.getText());
           }
        }
        
        if (e.getSource() == frmC.btnLimpiar) {
            limpiar();
            frmC.limpiarTabla();
            
        }
        if(e.getSource()==frmC.btnSalir){
            frmC.setVisible(false);
        }
      
    }
    
}
