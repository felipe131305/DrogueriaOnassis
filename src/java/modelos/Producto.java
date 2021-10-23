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
public class Producto {

    private int idProducto;
    private int cantidadProducto;
    private int precioProducto;
    private int salidas;
    private int visivilidad;
    private String nombreProducto, descripcion;

    public Producto() {
    }

    public Producto(int idProducto, int cantidadProducto, int precioProducto, String nombreProducto, String descripcion, int salidas, int visibilidad) {
        this.idProducto = idProducto;
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.salidas = salidas;
        this.visivilidad=visibilidad;
    }

    public int getSalidas() {
        return salidas;
    }

    public void setSalidas(int salidas) {
        this.salidas = salidas;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public int getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descipcion) {
        this.descripcion = descipcion;
    }

    public int getVisivilidad() {
        return visivilidad;
    }

    public void setVisivilidad(int visivilidad) {
        this.visivilidad = visivilidad;
    }

}
