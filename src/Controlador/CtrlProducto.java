package Controlador;

import Modelo.controlBaseDeDatos;
import Modelo.Producto;
import Vista.frmProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlProducto implements ActionListener {

    private Producto mod;
    private controlBaseDeDatos modC;
    private frmProducto frm; 
    public CtrlProducto(Producto mod, controlBaseDeDatos modC, frmProducto frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnSalir.addActionListener(this);
    }

    public void iniciar( ) {
        frm.setTitle("Control de Productos");
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
       
       
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnGuardar) {
           
            if(frm.txtCodigo.getText().isEmpty()||frm.txtNombre.getText().isEmpty()
                    ||frm.txtPrecio.getText().isEmpty()||frm.txtCantidad.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "No puede haber campos sin llenar");
               
                return ;
            }
           
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
           
            
            if(modC.registrarProducto(mod))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar verifique si el código no está repetido");
                limpiar();
            }
            
        }
        
        if (e.getSource() == frm.btnModificar) {
            if(frm.txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
            else{
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
            
            if(modC.modificarProducto(mod))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar verifique si el código no está repetido");
                limpiar();
            }
            }
        }
        
        if (e.getSource() == frm.btnEliminar) {
             if(frm.txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"debe seleccionar un registro");
            }
             else{
            mod.setId(Integer.parseInt(frm.txtId.getText()));
            
            if(modC.eliminarProducto(mod))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
             }
        }
        
        if (e.getSource() == frm.btnBuscar) {
            mod.setCodigo(frm.txtCodigo.getText());
            if(frm.txtCodigo.getText().isEmpty()){
                 frm.iniciarTabla();
                 frm.mostrarTabla(frm.txtNombre.getText());
            }else{
            if(modC.buscarProducto(mod))
            {
                frm.txtId.setText(String.valueOf(mod.getId()));
                frm.txtCodigo.setText(mod.getCodigo());
                frm.txtNombre.setText(mod.getNombre());
                frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));

            } else {
                JOptionPane.showMessageDialog(null, "No se encontró registro");
                limpiar();
            }
        }
        }
        
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
            frm.limpiarTabla();
        }
        if(e.getSource()==frm.btnSalir){
          frm.setVisible(false);
            
        
            
            
        }

    }
    
   
    
    public void limpiar()
    {
        frm.txtId.setText(null);
        frm.txtCodigo.setText(null);
        frm.txtNombre.setText(null);
        frm.txtPrecio.setText(null);
        frm.txtCantidad.setText(null);
       
    }
   

}
