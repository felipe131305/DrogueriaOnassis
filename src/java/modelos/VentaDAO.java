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
public class VentaDAO {
    
    
     Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    
    public String ObtenerNumeroDeFactura() {
        String numerodefactura ="";
        String consulta = "SELECT MAX(numerofactura) FROM ventasd";
        con = cn.Conexion();
 
        try {
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                numerodefactura = rs.getString(1);
                System.err.println("numfac"+numerodefactura);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return numerodefactura;
 
    }
    
     public void RegistrarVenta(Venta venta){
         String sentencia = "INSERT INTO ventasd (idcliente,idempleado,numerofactura,fechaventa,totalventa) VALUES(?,?,?,?,?)";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sentencia);
            ps.setInt(1,venta.getIdCliente());
            ps.setInt(2, venta.getIdEmpleado());
            ps.setString(3, venta.getComprobante());
            ps.setString(4, venta.getFecha());
            ps.setDouble(5, venta.getMonto());
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void GuardarDetalleVenta(Venta venta){
          String sentencia = "INSERT INTO detalleventa (ventas_id,productos_id,cantidadproducto,precioventa) VALUES(?,?,?,?)";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sentencia);
            ps.setInt(1,venta.getIdventa());
            ps.setInt(2, venta.getIdProducto());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getPrecio());
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int ObtenerMaximoIdVentas(){
        int idventas=0;
        String consulta="SELECT MAX(idventas)FROM ventasd";
         con = cn.Conexion();
             
         try{
         ps= con.prepareStatement(consulta);
         rs=ps.executeQuery();
         while(rs.next()){
             idventas=rs.getInt(1);    
         }
         
         }catch(SQLException ex){
         Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         
      return idventas; 
    }
    
    public Venta BuscarVenta(int NumeroFactura){
        Venta venta = new Venta();
        String consulta = "SELECT * FROM ventasd WHERE numerofactura = ?";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, NumeroFactura);
            rs = ps.executeQuery();
            while(rs.next()){
                venta.setIdventa(rs.getInt("idventas"));
                venta.setMonto(rs.getInt("totalventa"));
                venta.setComprobante(rs.getString("numerofactura"));
                
                System.err.println(""+venta.getIdventa()+"id de la venta");
                System.err.println(""+venta.getMonto()+"total venta");
                System.err.println(""+venta.getComprobante()+"numero factura");
              }
        } catch (Exception e) {
        }
        return venta;
    }
    
    public List BuscarDetalleVenta(int NumeroFactura){
        
        String consulta = "SELECT * FROM detalleventa WHERE ventas_id = ?";
        con = cn.Conexion();
        List<Venta> listaVentas = new ArrayList();
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, NumeroFactura);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta venta = new Venta();
                venta.setCantidad(rs.getInt("cantidadproducto"));
                venta.setIdProducto(rs.getInt("productos_id"));
                listaVentas.add(venta);
                
                
                System.err.println(""+venta.getCantidad()+"cantidad");
                System.err.println(""+venta.getIdProducto()+"id producto");
                
              }
        } catch (Exception e) {
        }
        return listaVentas;
    }
    
     public void EliminarVenta(int factura) {

        String sql = "DELETE FROM ventasd WHERE numerofactura=" + factura;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     public void EliminarDetalle(int ventas) {

        String sql = "DELETE FROM detalleventa WHERE ventas_id=" + ventas;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     
     public List ReporteCliente(String fechaI, String fechaF) {
        String fechaInicial=fechaI;
         String fechaFinal=fechaF;
        String consulta = "SELECT SUM(totalventa)as totalcomprado,nombre,documento,correo FROM usuarios INNER JOIN  ventasd on ventasd.idcliente=usuarios.idusuario WHERE fechaventa BETWEEN"+"'"+fechaInicial+"'"+"and"+"'"+fechaFinal+"'"+"GROUP by idcliente DESC";
        List<Venta> lista = new ArrayList();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Venta venta = new Venta();
                venta.setMonto(rs.getDouble("totalcomprado"));
                
                
                
                lista.add(venta);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }
     
     

}

    

