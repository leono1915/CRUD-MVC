/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Tecnico;
import Modelo.controlBaseDeDatos;
import Vista.frmTecnico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author estrella
 */
public class CtrlTecnico implements ActionListener{
  private Tecnico tec;
    private controlBaseDeDatos modC;
    private frmTecnico frmc;
       
       public CtrlTecnico (Tecnico tec,controlBaseDeDatos modC,frmTecnico frmc){
        this.tec=tec;
        this.frmc=frmc;
        this.modC=modC;
        this.frmc.btnGuardar.addActionListener(this);
        this.frmc.btnModificar.addActionListener(this);
        this.frmc.btnEliminar.addActionListener(this);
        this.frmc.btnLimpiar.addActionListener(this);
        this.frmc.btnbuscar.addActionListener(this);
        this.frmc.btnSalir.addActionListener(this);
    }
   public void iniciarTecnico(){
        frmc.setTitle("Control de Tecnicos");
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
        frmc.txtTelefono.setText(null);
        frmc.txtEspecialidad.setText(null);
        frmc.txtEmpresa.setText(null);
    }
       
    @Override
        public void actionPerformed(ActionEvent et) {

        if (et.getSource() == frmc.btnGuardar) {
            
             boolean bandera=false;
          if(frmc.txtCodigo.getText().length()!=10){
             JOptionPane.showMessageDialog(null,"el Codigo debe tener 10 digitos para ser válido");
             bandera=true;
            }
            if(!validarEntrada(frmc.txtTelefono.getText())||(frmc.txtTelefono.getText().length()>12||frmc.txtTelefono.getText().length()<8)){
                bandera=true;
                JOptionPane.showMessageDialog(null,"el numero telefónico no puede tener letras y debe ser de longitud válida");
            }
           
           if(frmc.txtCodigo.getText().isEmpty()||frmc.txtEmpresa.getText().isEmpty()
                   ||frmc.txtEspecialidad.getText().isEmpty()||frmc.txtNombre.getText().isEmpty()
                   ){
                JOptionPane.showMessageDialog(null,"no puede haber datos sin llenar");
                bandera=true;
           }
            if(bandera==false){
            tec.setCodigo(frmc.txtCodigo.getText());
            tec.setNombre(frmc.txtNombre.getText());
            tec.setTelefono(frmc.txtTelefono.getText());
            tec.setEspecialidad(frmc.txtEspecialidad.getText());
            tec.setEmpresa(frmc.txtEmpresa.getText());
            
            if(modC.registrarTecnico(tec))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
        }
        }
        if (et.getSource() == frmc.btnModificar) {
            boolean bandera=false;
            if(frmc.txtId.getText().isEmpty()){
                bandera=true;
             JOptionPane.showMessageDialog(null,"no se ha seleccionado registro");
            }
            if(bandera==false){
            tec.setId(Integer.parseInt(frmc.btnbuscar.getText()));
            tec.setCodigo(frmc.txtCodigo.getText());
            tec.setNombre(frmc.txtNombre.getText());
            tec.setTelefono(frmc.txtTelefono.getText());
            tec.setEspecialidad(frmc.txtEspecialidad.getText());
            tec.setEmpresa(frmc.txtEmpresa.getText()); 
            
            if(modC.modificarTecnico(tec))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }
        }
        if (et.getSource() == frmc.btnEliminar) {
             boolean bandera=false;
            if(frmc.txtId.getText().isEmpty()){
           
            
                bandera=true;
             JOptionPane.showMessageDialog(null,"no se ha seleccionado registro");
            }
            if(bandera==false){
                 tec.setId(Integer.parseInt(frmc.txtId.getText()));
            if(modC.eliminarTecnico(tec))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }
        }
        if (et.getSource() == frmc.btnbuscar) {
            tec.setCodigo(frmc.txtCodigo.getText());
           if(frmc.txtCodigo.getText().isEmpty()){
                frmc.iniciarTabla();
                frmc.mostrarTabla(frmc.txtEspecialidad.getText());
                
            }
            else{
            if(modC.buscarTecnico(tec))
            {
            frmc.txtCodigo.setText(tec.getCodigo());
            frmc.txtNombre.setText(tec.getNombre());
            frmc.txtTelefono.setText(tec.getTelefono());
            frmc.txtEspecialidad.setText(tec.getEspecialidad());
            frmc.txtEmpresa.setText(tec.getEmpresa());
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
        if(et.getSource()== frmc.btnSalir){
            frmc.setVisible(false);
        }

    }
          
}
