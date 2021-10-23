/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    int r;

    public List Listar() {
        String consulta = "SELECT * FROM productos WHERE visibilidad= 0";
        List<Producto> lista = new ArrayList();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idproducto"));
                producto.setNombreProducto(rs.getString("nombreproducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioProducto(rs.getInt("precio"));
                producto.setCantidadProducto(rs.getInt("cantidad"));
                lista.add(producto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public List Listarkardex(String texto) {

        String consulta = "SELECT * FROM productos WHERE visibilidad =0 AND (nombreproducto LIKE '%" + texto + "%' or idproducto LIKE '%" + texto + "%' or Estado LIKE '%" + texto + "%') ";
        List<Producto> lista = new ArrayList();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("idproducto"));
                producto.setNombreProducto(rs.getString("nombreproducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioProducto(rs.getInt("precio"));
                producto.setCantidadProducto(rs.getInt("cantidad"));
                lista.add(producto);

            }
        } catch (SQLException ex) {

        }
        return lista;

    }

    public void AgregarProducto(Producto producto) {
        int r = 0;
        con = cn.Conexion();
        String sentencia = "INSERT INTO productos (nombreproducto, descripcion, precio, cantidad, visibilidad,Estado) VALUES (?,?,?,?,?,?)";
        try {
            String estado;
            ps = con.prepareStatement(sentencia);
            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getPrecioProducto());
            ps.setInt(4, producto.getCantidadProducto());
            ps.setInt(5, producto.getVisivilidad());
            if (producto.getCantidadProducto() == 0) {
                estado = "Agotado";
                ps.setString(6, estado);
            } else if (producto.getCantidadProducto() > 0 && producto.getCantidadProducto() <= 20) {
                estado = "Pocas Existencias";
                ps.setString(6, estado);
            } else {
                estado = "En Stock";
                ps.setString(6, estado);
            }
            ps.executeUpdate();

        } catch (Exception e) {

        }

    }

    public Producto ConsultaPorCodigo(int codigoProducto) {
        Producto producto = new Producto();
        con = cn.Conexion();
        String consulta = "SELECT * FROM productos WHERE  visibilidad = 0 AND idproducto = " + codigoProducto;

        try {
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto.setIdProducto(rs.getInt(1));
                producto.setNombreProducto(rs.getString(2));
                producto.setDescripcion(rs.getString(3));
                producto.setPrecioProducto(rs.getInt(4));
                producto.setCantidadProducto(rs.getInt(5));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

    public void ActualizarProducto(Producto producto) {
        String sentencia = "UPDATE productos set nombreproducto=?,descripcion=?,precio=?,cantidad=? WHERE idproducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);

            ps.setString(1, producto.getNombreProducto());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getPrecioProducto());
            ps.setInt(4, producto.getCantidadProducto());
            ps.setInt(5, producto.getIdProducto());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ActualizarKardex(Producto producto) {
        String sentencia = "UPDATE productos set nombreproducto=?,precio=?,cantidad=?,Estado=? WHERE idproducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            String estado;
            ps.setString(1, producto.getNombreProducto());
            ps.setInt(2, producto.getPrecioProducto());
            ps.setInt(3, producto.getCantidadProducto());
            if (producto.getCantidadProducto() == 0) {
                estado = "Agotado";
                ps.setString(4, estado);
            } else if (producto.getCantidadProducto() > 0 && producto.getCantidadProducto() <= 20) {
                estado = "Pocas Existencias";
                ps.setString(4, estado);
            } else {
                estado = "En Stock";
                ps.setString(4, estado);
            }
            ps.setInt(5, producto.getIdProducto());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EliminarProducto(int id) {

        String sql = "UPDATE productos set visibilidad=1 WHERE idproducto=" + id;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int Descontarventa(int descuento, Venta venta) {
        String sentencia = "UPDATE productos set cantidad=?,Estado=? WHERE idproducto=?";
        try {

            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            String estado;
            ps.setInt(1, descuento);
            if (descuento == 0) {
                estado = "Agotado";
                ps.setString(2, estado);
            } else if (descuento > 0 && descuento <= 20) {
                estado = "Pocas Existencias";
                ps.setString(2, estado);
            } else {
                estado = "En Stock";
                ps.setString(2, estado);
            }
            ps.setInt(3, venta.getIdProducto());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int Devolucion(int devolucion, int id) {
        String sentencia = "UPDATE productos set cantidad=?,Estado=? WHERE idproducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            String estado;
            ps.setInt(1, devolucion);

            if (devolucion == 0) {
                estado = "Agotado";
                ps.setString(2, estado);
            } else if (devolucion > 0 && devolucion <= 20) {
                estado = "Pocas Existencias";
                ps.setString(2, estado);
            } else {
                estado = "En Stock";
                ps.setString(2, estado);
            }

            ps.setInt(3, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int cantidaProducto(int id) {
        String consulta = "SELECT cantidad FROM productos WHERE idproducto =" + "'" + id + "'";
        int cantidad = 0;

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();

                producto.setCantidadProducto(rs.getInt("cantidad"));
                cantidad = producto.getCantidadProducto();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;

    }

}
