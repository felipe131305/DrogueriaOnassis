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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class UsuarioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    int r;
    
    
    public Usuario BuscarCliente(int documento){
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuarios WHERE documento = ? AND visibilidad =0";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, documento);
            rs = ps.executeQuery();
            while(rs.next()){
                usuario.setId(rs.getInt("idusuario"));
                usuario.setDocumento(rs.getInt("documento"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setRol(rs.getString("rol"));
                System.err.println(""+usuario.getNombre());
              }
        } catch (Exception e) {
        }
        return usuario;
    }
    
    public Usuario BuscarEmpleado(int documento){
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuarios WHERE documento = ? AND visibilidad=0";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, documento);
            rs = ps.executeQuery();
            while(rs.next()){
                usuario.setId(rs.getInt("idusuario"));
                usuario.setDocumento(rs.getInt("documento"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setRol(rs.getString("rol"));
                System.err.println(""+usuario.getNombre());
              }
        } catch (Exception e) {
        }
        return usuario;
    }

    public Usuario Validar(int documento, String password) throws SQLException {
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuarios WHERE documento = ? AND password = ?";
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(consulta);
            ps.setInt(1, documento);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            do {
                usuario.setId(rs.getInt("idusuario"));
                usuario.setDocumento(rs.getInt("documento"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setEstado(rs.getString("ESTADO"));
                usuario.setRol(rs.getString("rol"));
                usuario.setVisibilidad(rs.getInt("visibilidad"));
                
            } while (rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;

    }

    public List Listar() {
        String consulta = "SELECT * FROM usuarios WHERE rol = 'Empleado' AND visibilidad = '0'";
        List<Usuario> lista = new ArrayList();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));
                usuario.setDocumento(rs.getInt("documento"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setEstado(rs.getString("ESTADO"));
                usuario.setRol(rs.getString("rol"));
                lista.add(usuario);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }
    
    
     public List ListarCliente() {
        String consulta = "SELECT * FROM usuarios WHERE rol = 'Cliente'AND visibilidad = '0'";
        List<Usuario> lista = new ArrayList();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));
                usuario.setDocumento(rs.getInt("documento"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setEstado(rs.getString("ESTADO"));
                usuario.setRol(rs.getString("rol"));
                lista.add(usuario);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public void Agregar(Usuario usuario) {

        String sentencia = "INSERT INTO usuarios (documento,nombre,correo,password,rol,ESTADO,visibilidad) VALUES (?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, usuario.getDocumento());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getRol());
            ps.setString(6, usuario.getEstado());
            ps.setInt(7, usuario.getVisibilidad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Usuario ListarPorId(int id) {
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuarios WHERE idusuario=" + id;
        con = cn.Conexion();
        try {
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setDocumento(rs.getInt(2));
                usuario.setNombre(rs.getString(3));
                usuario.setCorreo(rs.getString(4));
                usuario.setPassword(rs.getString(5));
                usuario.setRol(rs.getString(6));
                usuario.setEstado(rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;

    }

    public void Actualizar(Usuario usuario) {
        String sentencia = "UPDATE usuarios set documento=?,nombre=?,correo=?,password=?,Rol=?,ESTADO=? WHERE idusuario=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, usuario.getDocumento());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getRol());
            ps.setString(6, usuario.getEstado());
            ps.setInt(7, usuario.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void Eliminar(int id) {

        String sql = "UPDATE usuarios set visibilidad=1 WHERE idusuario=" + id;
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
        List<Usuario> lista = new ArrayList();

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setDocumento(rs.getInt("documento"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setCompras(rs.getDouble("totalcomprado"));
                
                
                
                lista.add(usuario);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

}
