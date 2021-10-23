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

/**
 *
 * @author USER
 */
public class ReporteDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public void GuardarReporte(Reporte reporte){
          String sentencia = "INSERT INTO reportes (cantidadventa,producto,producto_id,fecha,precioproducto,factura) VALUES(?,?,?,?,?,?)";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sentencia);
            ps.setInt(1,reporte.getCantidadventa());
            ps.setString(2, reporte.getProducto());
            ps.setInt(3, reporte.getIdproducto());
            ps.setString(4, reporte.getFechaVenta());
            ps.setDouble(5, reporte.getPrecioProducto());
            ps.setString(6, reporte.getFactura());
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List Reporte(String fechaI, String fechaF) {
        String fechaInicial=fechaI;
         String fechaFinal=fechaF;
        String consulta = "SELECT SUM(cantidadventa) as totalventa, SUM(precioproducto*cantidadventa) as ventap,producto,producto_id,precioproducto from reportes WHERE fecha BETWEEN"+"'"+fechaInicial+"'"+"and"+"'"+fechaFinal+"'"+"GROUP by producto_id DESC";
        List<Reporte> lista = new ArrayList();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setIdproducto(rs.getInt("producto_id"));
                reporte.setProducto(rs.getString("producto"));
                reporte.setSalidas(rs.getInt("totalventa"));
                reporte.setPrecioProducto(rs.getInt("ventap"));
                
                
                lista.add(reporte);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }
    
    public int ReporteTotalventa(String fechaI, String fechaF) {
        String fechaInicial=fechaI;
         String fechaFinal=fechaF;
         int venta = 0;
        String consulta = "SELECT SUM(cantidadventa) as totalventa, SUM(precioproducto*cantidadventa) from reportes WHERE fecha BETWEEN"+"'"+fechaInicial+"'"+"and"+"'"+fechaFinal+"'"+"GROUP by producto_id DESC";
        

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Reporte reporte = new Reporte();
               
                reporte.setPrecioProducto(rs.getInt("SUM(precioproducto*cantidadventa)"));
                 
                
                venta = (int) reporte.getPrecioProducto()+venta;
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return venta;

    }
    
     public void EliminarReporte(int factura) {

        String sql = "DELETE FROM reportes WHERE factura=" + factura;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
     
    
}
