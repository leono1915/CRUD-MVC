/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Facturas;
import Modelo.controlBaseDeDatos;
import Vista.frmFacturas;
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
public class ctrlFacturas implements ActionListener{
  Facturas fa; 
  frmFacturas frmF;
  controlBaseDeDatos ctrlB;

    public ctrlFacturas(Facturas fa, frmFacturas frmF, controlBaseDeDatos ctrlB) {
        this.fa = fa;
        this.frmF = frmF;
        this.ctrlB = ctrlB;
        this.frmF.btnGuardar.addActionListener(this);
        this.frmF.btnModificar.addActionListener(this);
        this.frmF.btnEliminar.addActionListener(this);
        this.frmF.btnLimpiar.addActionListener(this);
        this.frmF.btnBuscar.addActionListener(this);
        this.frmF.btnSalir.addActionListener(this);
    }
    public void Limpiar(){
      frmF.txtCliente.setText(null);
      frmF.txtFecha.setText(null);
      frmF.txtFolio.setText(null);
      frmF.txtTotal.setText(null);
      frmF.txtBuscar.setText(null);
      
    }
    public void iniciarFactura(){
      frmF.setTitle("Control de Facturas");
      frmF.txtBuscar.setVisible(false);
      frmF.setLocationRelativeTo(null);
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
         
        if(e.getSource()==frmF.btnGuardar){
             boolean bandera=false;
            
           if(!fechavalida(frmF.txtFecha.getText())){
               JOptionPane.showMessageDialog(null, "el formato de fecha no es valido");
              return; 
           }
          
           
           if(frmF.txtCliente.getText().isEmpty()
                   ||frmF.txtFolio.getText().isEmpty()||frmF.txtTotal.getText().isEmpty()
                   ){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                bandera=true;
           }
            if(bandera==false){
            fa.setCliente(frmF.txtCliente.getText());
            fa.setFecha(frmF.txtFecha.getText());
            fa.setFolio(frmF.txtFolio.getText());
            fa.setTotal(Integer.parseInt(frmF.txtTotal.getText()));
           if(ctrlB.registrarFactura(fa)){
               JOptionPane.showMessageDialog(null,"registro guardado");
               Limpiar();
           }
           else{
               JOptionPane.showMessageDialog(null,"no se pudo agregar");
               Limpiar();
           }
        }
        }
    
     if(e.getSource()==frmF.btnModificar){
           if(frmF.txtBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
            fa.setCliente(frmF.txtCliente.getText());
            fa.setFecha(frmF.txtFecha.getText());
            fa.setFolio(frmF.txtFolio.getText());
            fa.setTotal(Integer.parseInt(frmF.txtTotal.getText()));
           if(ctrlB.modificarFactura(fa)){
               JOptionPane.showMessageDialog(null,"registro modificado");
               Limpiar();
           }
           else{
               JOptionPane.showMessageDialog(null,"no se pudo modificar");
               Limpiar();
           }
        }
     }
     if(e.getSource()==frmF.btnEliminar){
                if(frmF.txtBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
            fa.setId(Integer.parseInt(frmF.txtBuscar.getText()));
           if(ctrlB.eliminarFactura(fa)){
               JOptionPane.showMessageDialog(null,"registro modificado");
               Limpiar();
           }
           else{
               JOptionPane.showMessageDialog(null,"no se pudo eliminar");
               Limpiar();
           }
        }
     }
     if(e.getSource()==frmF.btnBuscar){
          if(frmF.txtFolio.getText().isEmpty()){
               frmF.iniciarTabla();
              if(frmF.txtFecha.getText().isEmpty()){
              
              frmF.mostrarTabla(frmF.txtCliente.getText());
              }
              else{
              frmF.mostrarTabla(frmF.txtFecha.getText());   
              }
          }
          else{
            fa.setFolio(frmF.txtFolio.getText());
           if(ctrlB.buscarFactura(fa)){
            frmF.txtCliente.setText(fa.getCliente());
            frmF.txtFecha.setText(fa.getFecha());
            frmF.txtFolio.setText(fa.getFolio());
            frmF.txtTotal.setText(String.valueOf(fa.getTotal())); 
               
           }
           else{
               JOptionPane.showMessageDialog(null,"no se encontró registro");
               Limpiar();
           }
        }
     }
     if(e.getSource()==frmF.btnLimpiar){
            Limpiar();
            frmF.setVisible(false);
        }
     if(e.getSource()==frmF.btnSalir){
            frmF.setVisible(false);
        }
          
}
  
}
