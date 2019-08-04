package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class controlBaseDeDatos extends Conexion {

    public boolean registrarProducto(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO productos (codigo, nombre, precio , cantidad) VALUES(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarProducto(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE productos SET codigo=?, nombre=?, precio=?, cantidad=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setDouble(3, pro.getPrecio());
            ps.setInt(4, pro.getCantidad());
            ps.setInt(5, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarProducto(Producto pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM productos WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarProducto(Producto pro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM productos WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               pro.setId( Integer.parseInt(rs.getString("id")));
               pro.setCodigo(rs.getString("codigo"));
               pro.setNombre(rs.getString("nombre"));
               pro.setPrecio(Double.parseDouble(rs.getString("precio")));
               pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    public boolean registrarCliente(Cliente cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO clientes (codigo, nombre,domicilio, rfc , telefono) VALUES(?,?,?,?,?)";

        try {
           ps = con.prepareStatement(sql);
            ps.setString(1, cli.getCodigo());
            ps.setString(2, cli.getNombre());
            ps.setString(3, cli.getDomicilio());
            ps.setString(4, cli.getRfc());
            ps.setString(5, cli.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarCliente(Cliente cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE clientes SET codigo=?, nombre=?,domicilio=?, rfc=?, telefono=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getCodigo());
            ps.setString(2, cli.getNombre());
            ps.setString(3, cli.getDomicilio());
            ps.setString(4, cli.getRfc());
            ps.setString(5, cli.getTelefono());
            ps.setInt(6,cli.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarCliente(Cliente cli) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM clientes WHERE id=? ";
          
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cli.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
           
    
    }
    
    public boolean buscarCliente(Cliente cli) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM clientes WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getCodigo());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               cli.setId( Integer.parseInt(rs.getString("id")));
               cli.setCodigo(rs.getString("codigo"));
               cli.setNombre(rs.getString("nombre"));
               cli.setDomicilio(rs.getString("domicilio"));
               cli.setRfc(rs.getString("rfc"));
               cli.setTelefono(rs.getString("telefono"));
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
     public boolean registrarTecnico(Tecnico tec) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO tecnicos (codigo, nombre,telefono,especialidad, empresa) VALUES(?,?,?,?,?)";

        try {
           ps = con.prepareStatement(sql);
            ps.setString(1, tec.getCodigo());
            ps.setString(2, tec.getNombre());
            ps.setString(3, tec.getTelefono());
            ps.setString(4, tec.getEspecialidad());
            ps.setString(5, tec.getEmpresa());
          
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarTecnico(Tecnico tec) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO tecnicos (codigo, nombre,telefono,especialidad, empresa) VALUES(?,?,?,?,?)";

        try {
           ps = con.prepareStatement(sql);
            ps.setString(1, tec.getCodigo());
            ps.setString(2, tec.getNombre());
            ps.setString(3, tec.getTelefono());
            ps.setString(4, tec.getEspecialidad());
            ps.setString(5, tec.getEmpresa());
            ps.setInt(6, tec.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarTecnico(Tecnico tec) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM tecnicos WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, tec.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarTecnico(Tecnico tec) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM tecnicos WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, tec.getCodigo());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               tec.setId( Integer.parseInt(rs.getString("id")));
               tec.setCodigo(rs.getString("codigo"));
               tec.setNombre(rs.getString("nombre"));
               tec.setTelefono(rs.getString("telefono"));
               tec.setEspecialidad(rs.getString("especialidad"));
               tec.setEmpresa(rs.getString("empresa"));
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    public boolean registrarEmpleado(Empleados em) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO empleados (nss, nombre,domicilio ,fecha_ingreso ,fecha_nacimiento, telefono) VALUES(?,?,?,?,?,?)";

        try {
           ps = con.prepareStatement(sql);
            ps.setString(1, em.getNss());
            ps.setString(2,em.getNombre());
            ps.setString(3, em.getDomicilio());
            ps.setString(4, em.getFechaIngreso());
            ps.setString(5, em.getFechaNacimiento());
            ps.setString(6, em.getTelefono());
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarEmpleado(Empleados em) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE empleados SET nss=?, nombre=?,domicilio=?, fecha_ingreso=?,fecha_nacimiento=?, telefono=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNss());
            ps.setString(2, em.getNombre());
            ps.setString(3, em.getDomicilio());
            ps.setString(4, em.getFechaIngreso());
            ps.setString(5, em.getFechaNacimiento());
            ps.setString(6, em.getTelefono());
            ps.setInt(7, em.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarEmpleado(Empleados em) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM empleados WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, em.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarEmpleado(Empleados em) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM empleados WHERE nss=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNss());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               em.setId( Integer.parseInt(rs.getString("id")));
               em.setNss(rs.getString("nss"));
               em.setNombre(rs.getString("nombre"));
               em.setDomicilio(rs.getString("domicilio"));
               em.setFechaIngreso(rs.getString("fecha_ingreso"));
               em.setFechaNacimiento(rs.getString("fecha_nacimiento"));
               em.setTelefono(rs.getString("telefono"));
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    } 
    public boolean registrarCotizacion(Cotizaciones co){
        PreparedStatement ps=null;
        Connection con = getConexion();
        String sql="INSERT INTO cotizaciones (cliente,fecha,total,descripcion) VALUES(?,?,?,?)";
        try{
         ps=con.prepareStatement(sql);
         ps.setString(1,co.getCliente());
         ps.setString(2,co.getFecha());
         ps.setDouble(3,co.getTotal());
         ps.setString(4,co.getDescripcion());
         ps.execute();
         return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally {
            try{
            con.close();
        }catch(SQLException e){
            System.err.println(e);
        }
            
        }
    }
    public boolean modificarCotizacion(Cotizaciones co) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE cotizaciones SET cliente=?, fecha=?,total=?, descripcion=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, co.getCliente());
            ps.setString(2, co.getFecha());
            ps.setDouble(3, co.getTotal());
            ps.setString(4, co.getDescripcion());
            ps.setInt(5, co.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarCotizacion(Cotizaciones co) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM cotizacones WHERE cliente =? AND fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, co.getCliente());
            ps.setString(2,co.getFecha());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarCotizacion(Cotizaciones co) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM cotizaciones WHERE cliente=? AND fecha =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, co.getCliente());
            ps.setString(2,co.getFecha());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               co.setId( Integer.parseInt(rs.getString("id")));
               co.setCliente(rs.getString("cliente"));
               co.setFecha(rs.getString("fecha"));
               co.setTotal(Double.parseDouble(rs.getString("total")));
               co.setDescripcion(rs.getString("descripcion"));
             
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    } 
    public boolean registrarFactura(Facturas fa) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO facturas (folio, fecha, cliente, total) VALUES(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
             ps.setString(1, fa.getFolio());
            ps.setString(2, fa.getFecha());
            ps.setString(3, fa.getCliente());
            ps.setInt(4, fa.getTotal());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarFactura(Facturas fa) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE facturas SET folio=?, fecha=?, cliente=?, total=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, fa.getFolio());
            ps.setString(2, fa.getFecha());
            ps.setString(3, fa.getCliente());
            ps.setInt(4, fa.getTotal());
            ps.setInt(5, fa.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarFactura(Facturas fa) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM facturas WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, fa.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarFactura(Facturas fa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM facturas WHERE folio=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, fa.getFolio());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               fa.setId( Integer.parseInt(rs.getString("id")));
               fa.setFolio(rs.getString("folio"));
               fa.setFecha(rs.getString("fecha"));
               fa.setCliente(rs.getString("cliente"));
               fa.setTotal(Integer.parseInt(rs.getString("total")));
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
     public boolean registrarCitas(Citas ci) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO citas (descripcion, fecha, hora , tipo) VALUES(?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ci.getDescripcion());
            ps.setString(2, ci.getFecha());
            ps.setString(3, ci.getHora());
            ps.setString(4, ci.getTipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarCita(Citas ci) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE citas SET descripcion=?, fecha=?,hora=?,  tipo=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ci.getDescripcion());
            ps.setString(2, ci.getFecha());
            ps.setString(3, ci.getHora());
            ps.setString(4, ci.getTipo());
            ps.setInt(5, ci.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarCita(Citas ci) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM citas WHERE hora=? AND fecha=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ci.getHora());
             ps.setString(2, ci.getFecha());
          
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarCitas(Citas ci) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM citas WHERE fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ci.getFecha());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               ci.setId( Integer.parseInt(rs.getString("id")));
               ci.setDescripcion(rs.getString("descripcion"));
               ci.setFecha(rs.getString("fecha"));
               ci.setHora(rs.getString("hora"));
               ci.setTipo(rs.getString("tipo"));
             
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    public boolean registrarProovedores(Proovedores pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO proovedores (nombre, domicilio, telefono , giro,codigo) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombreEmpresa());
            ps.setString(2, pro.getDomcilio());
            ps.setString(3, pro.getTelefono());
            ps.setString(4, pro.getGiro());
            ps.setString(5, pro.getCodigo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarProovedor(Proovedores pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE proovedores SET nombre=?, domicilio=?, telefono=?, giro=?, codigo=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombreEmpresa());
            ps.setString(2, pro.getDomcilio());
            ps.setString(3, pro.getTelefono());
            ps.setString(4, pro.getGiro());
            ps.setString(5, pro.getCodigo());
            ps.setInt(5, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarProovedor(Proovedores pro) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM proovedores WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarProovedor(Proovedores pro) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM proovedores WHERE codigo=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombreEmpresa());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               pro.setId( Integer.parseInt(rs.getString("id")));
               pro.setNombreEmpresa(rs.getString("nombre"));
               pro.setDomcilio(rs.getString("domicilio"));
               pro.setTelefono(rs.getString("telefono"));
               pro.setGiro(rs.getString("giro"));
               pro.setCodigo(rs.getString("codigo"));
             
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    public boolean registrarVentas(Ventas ve) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO ventas (producto, fecha, cliente ,total, facturado) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getProducto());
            ps.setString(2, ve.getFecha());
            ps.setString(3, ve.getCliente());
            ps.setInt(4, ve.getTotal());
            ps.setString(5, ve.getFacturado());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarVentas(Ventas ve) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE ventas SET producto=?, fecha=?, cliente=?, total=?,facturado=? WHERE id=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getProducto());
            ps.setString(2, ve.getFecha());
            ps.setString(3, ve.getCliente());
            ps.setInt(4, ve.getTotal());
            ps.setString(5, ve.getFacturado());
            ps.setInt(6,ve.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarVenta(Ventas ve) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM ventas WHERE id =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getId());
           
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarVentas(Ventas ve) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM ventas WHERE cliente=? and fecha=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ve.getCliente());
            ps.setString(2, ve.getFecha());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               ve.setId( Integer.parseInt(rs.getString("id")));
               ve.setCliente(rs.getString("cliente"));
               ve.setFecha(rs.getString("fecha"));
               ve.setTotal(Integer.parseInt(rs.getString("total")));
               ve.setFacturado(rs.getString("facturado"));
             
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    public boolean registrarServicio(Servicios se) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO servicios (precio, tipo, fecha ,hora, descripcion) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, se.getPrecio());
            ps.setString(2, se.getTipo());
            ps.setString(3, se.getFecha());
            ps.setString(4, se.getHora());
            ps.setString(5, se.getDescripcion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean modificarServicio(Servicios se) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE servicios SET precio=?, tipo=?, fecha=?, hora=?,descripcion=? WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, se.getPrecio());
            ps.setString(2, se.getTipo());
            ps.setString(3, se.getFecha());
            ps.setString(4, se.getHora());
            ps.setString(5, se.getDescripcion());
            ps.setInt(6, se.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminarServicio(Servicios se) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM servicios WHERE fecha=? AND hora=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, se.getFecha());
            ps.setString(2,se.getHora());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean buscarServicio(Servicios se) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM servicios WHERE fecha=? AND hora=? ";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, se.getFecha());
            ps.setString(2,se.getHora());
            rs = ps.executeQuery();
            
            if(rs.next())
            {
               se.setId( Integer.parseInt(rs.getString("id")));
               se.setPrecio(Double.parseDouble(rs.getString("precio")));
               se.setTipo( rs.getString("tipo"));
               se.setFecha(rs.getString("fecha"));
               se.setHora((rs.getString("hora")));
               se.setDescripcion(rs.getString("descripcion"));
               
             
               return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
