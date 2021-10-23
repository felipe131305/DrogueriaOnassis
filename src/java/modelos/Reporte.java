/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author USER
 */
public class Reporte {
    private int idreporte,idproducto,cantidadventa;
    private String producto,fechaVenta,factura;

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }
     int salidas;
     double PrecioProducto;

    public double getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(double PrecioProducto) {
        this.PrecioProducto = PrecioProducto;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public Reporte() {
        
    }

    public int getIdreporte() {
        return idreporte;
    }

    public void setIdreporte(int idreporte) {
        this.idreporte = idreporte;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidadventa() {
        return cantidadventa;
    }

    public void setCantidadventa(int cantidadventa) {
        this.cantidadventa = cantidadventa;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Reporte(int idreporte, int idproducto, int cantidadventa, String producto, String fechaVenta, int salidas, double PrecioProducto, String factura) {
        this.idreporte = idreporte;
        this.idproducto = idproducto;
        this.cantidadventa = cantidadventa;
        this.producto = producto;
        this.fechaVenta = fechaVenta;
        this.salidas = salidas;
        this.PrecioProducto=PrecioProducto;
        this.factura=factura;
    }
}
