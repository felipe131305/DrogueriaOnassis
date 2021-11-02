/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Producto;
import modelos.ProductoDAO;
import modelos.Reporte;
import modelos.ReporteDAO;
import modelos.Usuario;
import modelos.UsuarioDAO;
import modelos.Venta;
import modelos.VentaDAO;

/**
 *
 * @author USER
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario();
    Usuario usuarioEmpleado = new Usuario();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    UsuarioDAO usuarioDAOE = new UsuarioDAO();
    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();
    int codProducto, cantidad;
    int item = 0;
    double precio, totalapagar, subtotal, efectivo;
    String descripcion;
    List<Venta> listaVentas = new ArrayList();
    List<Venta> retorno = new ArrayList();
    int idUsuario;
    int validar = 1;
    int idProducto, itemproducto;
    String texto = "";
    Venta venta = new Venta();
    Venta ventaD = new Venta();
    VentaDAO ventaDAO = new VentaDAO();
    Reporte reporte = new Reporte();
    ReporteDAO reporteDAO = new ReporteDAO();
    int numfac = 0;
    int bandera = 0;
    int idventa = 0;
    String total1;
    String totalV;
    NumberFormat formatoNumero1;
    ArrayList<Integer> descontar = new ArrayList();
    int documento;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Productos")) {

            switch (accion) {

                case "Listarp":
                    List lista = productoDAO.Listar();
                    request.setAttribute("Producto", lista);
                    break;

                case "Agregar":
                    String nombreProducto = request.getParameter("txtNombreProducto");
                    String descripcionProducto = request.getParameter("txtDescripcion");
                    int precioProducto = Integer.parseInt(request.getParameter("txtprecio"));
                    int cantidadProducto = Integer.parseInt(request.getParameter("txtcantidad"));
                    producto.setDescripcion(descripcionProducto);
                    producto.setNombreProducto(nombreProducto);
                    producto.setPrecioProducto(precioProducto);
                    producto.setCantidadProducto(cantidadProducto);
                    producto.setVisivilidad(0);
                    productoDAO.AgregarProducto(producto);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listarp").forward(request, response);

                    break;

                case "Cargar":

                    idProducto = Integer.parseInt(request.getParameter("id"));
                    producto = productoDAO.ConsultaPorCodigo(idProducto);
                    request.setAttribute("producto", producto);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listarp").forward(request, response);
                    break;

                case "Actualizar":
                    Producto producto = new Producto();

                    String nombreUpdate = request.getParameter("txtNombreProducto");
                    String descripcionUpdate = request.getParameter("txtDescripcion");
                    int precioUpdate = Integer.parseInt(request.getParameter("txtprecio"));
                    int cantidadUpdate = Integer.parseInt(request.getParameter("txtcantidad"));

                    producto.setNombreProducto(nombreUpdate);
                    producto.setDescripcion(descripcionUpdate);
                    producto.setPrecioProducto(precioUpdate);
                    producto.setCantidadProducto(cantidadUpdate);

                    producto.setIdProducto(idProducto);
                    productoDAO.ActualizarProducto(producto);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listarp").forward(request, response);
                    break;

                case "Eliminar":
                    idProducto = Integer.parseInt(request.getParameter("id"));
                    productoDAO.EliminarProducto(idProducto);
                    request.getRequestDispatcher("Controlador?menu=Productos&accion=Listarp").forward(request, response);

                    break;

            }

            request.getRequestDispatcher("Productos.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            switch (accion) {

                case "Listar":
                    List lista = usuarioDAO.ListarCliente();
                    request.setAttribute("usuarios", lista);

                    break;

                case "Agregar":
                    int documento = Integer.parseInt(request.getParameter("txtdocumento"));
                    String nombre = request.getParameter("txtnombre");
                    String correo = request.getParameter("txtcorreo");
                    String password = request.getParameter("txtpassword");
                    String rol = request.getParameter("txtrol");
                    String estado = request.getParameter("txtestado");
                    usuario.setDocumento(documento);
                    usuario.setNombre(nombre);
                    usuario.setPassword(password);
                    usuario.setCorreo(correo);
                    usuario.setRol(rol);
                    usuario.setEstado(estado);
                    usuario.setVisibilidad(0);
                    usuarioDAO.Agregar(usuario);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    Usuario clienteActualizado = new Usuario();
                    int documentoUpdate = Integer.parseInt(request.getParameter("txtdocumento"));
                    String nombreUpdate = request.getParameter("txtnombre");
                    String correoUpdate = request.getParameter("txtcorreo");
                    String passwordUpdate = request.getParameter("txtpassword");
                    String rolUpdate = request.getParameter("txtrol");
                    String estadoUpdate = request.getParameter("txtestado");
                    clienteActualizado.setDocumento(documentoUpdate);
                    clienteActualizado.setNombre(nombreUpdate);
                    clienteActualizado.setPassword(passwordUpdate);
                    clienteActualizado.setCorreo(correoUpdate);
                    clienteActualizado.setRol(rolUpdate);
                    clienteActualizado.setEstado(estadoUpdate);
                    clienteActualizado.setId(idUsuario);
                    usuarioDAO.Actualizar(clienteActualizado);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Cargar":
                    idUsuario = Integer.parseInt(request.getParameter("id"));
                    Usuario usuario = usuarioDAO.ListarPorId(idUsuario);
                    request.setAttribute("usuarioSeleccionado", usuario);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    idUsuario = Integer.parseInt(request.getParameter("id"));
                    usuarioDAO.Eliminar(idUsuario);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);

                    break;
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Empleados")) {
            switch (accion) {

                case "Listar":
                    List lista = usuarioDAO.Listar();
                    request.setAttribute("usuarios", lista);

                    break;

                case "Agregar":
                    int documento = Integer.parseInt(request.getParameter("txtdocumento"));
                    String nombre = request.getParameter("txtnombre");
                    String correo = request.getParameter("txtcorreo");
                    String password = request.getParameter("txtpassword");
                    String rol = request.getParameter("txtrol");
                    String estado = request.getParameter("txtestado");
                    usuario.setDocumento(documento);
                    usuario.setNombre(nombre);
                    usuario.setPassword(password);
                    usuario.setCorreo(correo);
                    usuario.setRol(rol);
                    usuario.setEstado(estado);
                    usuario.setVisibilidad(0);
                    usuarioDAO.Agregar(usuario);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    Usuario empleadoActualizado = new Usuario();
                    int documentoUpdate = Integer.parseInt(request.getParameter("txtdocumento"));
                    String nombreUpdate = request.getParameter("txtnombre");
                    String correoUpdate = request.getParameter("txtcorreo");
                    String passwordUpdate = request.getParameter("txtpassword");
                    String rolUpdate = request.getParameter("txtrol");
                    String estadoUpdate = request.getParameter("txtestado");
                    empleadoActualizado.setDocumento(documentoUpdate);
                    empleadoActualizado.setNombre(nombreUpdate);
                    empleadoActualizado.setPassword(passwordUpdate);
                    empleadoActualizado.setCorreo(correoUpdate);
                    empleadoActualizado.setRol(rolUpdate);
                    empleadoActualizado.setEstado(estadoUpdate);
                    empleadoActualizado.setId(idUsuario);
                    usuarioDAO.Actualizar(empleadoActualizado);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;

                case "Cargar":
                    idUsuario = Integer.parseInt(request.getParameter("id"));
                    Usuario usuario = usuarioDAO.ListarPorId(idUsuario);
                    request.setAttribute("usuarioSeleccionado", usuario);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    idUsuario = Integer.parseInt(request.getParameter("id"));
                    usuarioDAO.Eliminar(idUsuario);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);

                    break;
            }
            request.getRequestDispatcher("Empleados.jsp").forward(request, response);

        }
        if (menu.equals("Kardex")) {

            switch (accion) {
                case "Listarp":
                    List lista = productoDAO.Listarkardex(texto);
                    request.setAttribute("Producto", lista);
                    break;
                case "Buscar":
                    String dato = request.getParameter("txtBuscar");
                    List lista1 = productoDAO.Listarkardex(dato);
                    if (lista1.isEmpty()) {
                        int existencia = 1;
                        request.setAttribute("Existencia", existencia);
                        request.setAttribute("Producto", lista1);
                    } else {
                        int existencia = 0;
                        request.setAttribute("Existencia", existencia);
                        request.setAttribute("Producto", lista1);
                    }
                    break;
                case "Editar":

                    idProducto = Integer.parseInt(request.getParameter("id"));
                    producto = productoDAO.ConsultaPorCodigo(idProducto);
                    request.setAttribute("producto", producto);
                    request.getRequestDispatcher("Controlador?menu=Kardex&accion=Listarp").forward(request, response);
                    break;
                case "Actualizar":
                    Producto productoactualizado = new Producto();

                    String nombreUpdate = request.getParameter("txtNombreProducto");
                    int precioUpdate = Integer.parseInt(request.getParameter("txtprecio"));
                    int cantidadUpdate = Integer.parseInt(request.getParameter("txtcantidad"));

                    productoactualizado.setNombreProducto(nombreUpdate);
                    productoactualizado.setPrecioProducto(precioUpdate);
                    productoactualizado.setCantidadProducto(cantidadUpdate);

                    productoactualizado.setIdProducto(idProducto);
                    productoDAO.ActualizarKardex(productoactualizado);
                    request.getRequestDispatcher("Controlador?menu=Kardex&accion=Listarp").forward(request, response);
                    break;
            }

            request.getRequestDispatcher("Kardex.jsp").forward(request, response);
        }
        if (menu.equals("Ventas")) {

            switch (accion) {
                case "Buscar":
                    int documentoCliente = Integer.parseInt(request.getParameter("documentocliente"));
                    int documentoEmpleado = Integer.parseInt(request.getParameter("documentoempleado"));
                    usuarioEmpleado = usuarioDAOE.BuscarEmpleado(documentoEmpleado);
                    usuario = usuarioDAO.BuscarCliente(documentoCliente);
                    if (usuario.getNombre() == null) {

                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    } else if (usuarioEmpleado.getNombre() == null) {

                        request.setAttribute("Validar", validar);
                    } else {
                        request.setAttribute("cliente", usuario);
                        request.setAttribute("empleado", usuarioEmpleado);
                        request.setAttribute("numerofactura", numfac);
                    }
                    break;

                case "Buscarproducto":
                    int codigoproducto = Integer.parseInt(request.getParameter("codigoproducto"));
                    producto = productoDAO.ConsultaPorCodigo(codigoproducto);
                    if (producto.getNombreProducto() == null) {
                        request.setAttribute("Validarp", validar);
                    } else {
                        request.setAttribute("productoseleccionado", producto);
                        request.setAttribute("cliente", usuario);
                        request.setAttribute("numerofactura", numfac);
                        request.setAttribute("empleado", usuarioEmpleado);
                        for (int i = 0; i < listaVentas.size(); i++) {
                            totalapagar += listaVentas.get(i).getSubTotal();
                        }
                        formatoNumero1 = NumberFormat.getNumberInstance();
                        total1 = formatoNumero1.format(totalapagar);
                        request.setAttribute("listaventas", listaVentas);
                        totalapagar = 0;
                        for (int i = 0; i < listaVentas.size(); i++) {
                            totalapagar += listaVentas.get(i).getSubTotal();
                        }
                        formatoNumero1 = NumberFormat.getNumberInstance();
                        total1 = formatoNumero1.format(totalapagar);
                        request.setAttribute("totalapagar", total1);
                    }
                    break;
                case "AgregarProducto":
                    totalapagar = 0;
                    venta = new Venta();
                    codProducto = Integer.parseInt(request.getParameter("codigoproducto"));
                    descripcion = request.getParameter("nombreproducto");
                    precio = Integer.parseInt(request.getParameter("precioproducto"));
                    cantidad = Integer.parseInt(request.getParameter("cantidadproducto"));
                    subtotal = precio * cantidad;
                    venta.setItem(item);
                    venta.setDescripcionproducto(descripcion);
                    venta.setCantidad(cantidad);
                    venta.setPrecio(precio);
                    venta.setSubTotal(subtotal);
                    venta.setIdProducto(codProducto);
                    listaVentas.add(venta);
                    item++;
                    int disponible = Integer.parseInt(request.getParameter("cantidadproductop"));
                    descontar.add(disponible);

                    request.setAttribute("listaventas", listaVentas);
                    for (int i = 0; i < listaVentas.size(); i++) {
                        totalapagar += listaVentas.get(i).getSubTotal();
                    }
                    formatoNumero1 = NumberFormat.getNumberInstance();
                    total1 = formatoNumero1.format(totalapagar);
                    String dinero = request.getParameter("txtdinero");
                    request.setAttribute("vueltas", dinero);
                    request.setAttribute("totalapagar", total1);
                    request.setAttribute("numerofactura", numfac);
                    request.setAttribute("cliente", usuario);
                    request.setAttribute("empleado", usuarioEmpleado);
                    break;
                case "cambio":
                    double efectivo = Integer.parseInt(request.getParameter("efectivo"));
                    String cambio = "su cambio es " + (efectivo - totalapagar);
                    request.setAttribute("efectivo", efectivo);
                    request.setAttribute("cambio", cambio);
                    request.setAttribute("listaventas", listaVentas);
                    request.setAttribute("totalapagar", total1);
                    request.setAttribute("cliente", usuario);
                    request.setAttribute("empleado", usuarioEmpleado);
                    request.setAttribute("numerofactura", numfac);

                    break;
                case "GenerarVenta":

                    venta.setIdCliente(usuario.getId());
                    venta.setIdEmpleado(usuarioEmpleado.getId());
                    venta.setComprobante("" + numfac);
                    java.util.Date fecha = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(fecha);
                    int dias = -1;
                    calendar.add(calendar.MONTH, dias);

                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                    Date fechaactual = new Date();
                    sdf.format(calendar.getTime());

                    sdf.format(fechaactual);
                    venta.setFecha(sdf.format(fechaactual));
                    venta.setMonto(totalapagar);
                    ventaDAO.RegistrarVenta(venta);
                    int cantidadUpdate = 0;
                    int idv = ventaDAO.ObtenerMaximoIdVentas();
                    String facturaReporte = ventaDAO.ObtenerNumeroDeFactura();
                    int facturar;
                    facturar = Integer.parseInt(facturaReporte);
                    facturaReporte = Integer.toString(facturar);
                    for (int i = 0; i < listaVentas.size(); i++) {

                        int descuento;
                        venta = new Venta();
                        reporte = new Reporte();
                        venta.setIdventa(idv);
                        venta.setIdProducto(listaVentas.get(i).getIdProducto());
                        reporte.setIdproducto(listaVentas.get(i).getIdProducto());
                        venta.setCantidad(listaVentas.get(i).getCantidad());
                        reporte.setCantidadventa(listaVentas.get(i).getCantidad());
                        venta.setPrecio(listaVentas.get(i).getPrecio());
                        reporte.setPrecioProducto(listaVentas.get(i).getPrecio());
                        reporte.setProducto(listaVentas.get(i).getDescripcionproducto());
                        reporte.setFechaVenta(sdf.format(fechaactual));
                        reporte.setFactura(facturaReporte);
                        ventaDAO.GuardarDetalleVenta(venta);
                        reporteDAO.GuardarReporte(reporte);

                        cantidadUpdate = venta.getCantidad();
                        int descontarProducto = productoDAO.cantidaProducto(reporte.getIdproducto());
                        descontar.set(i, descontar.get(i) - cantidadUpdate);
                        descuento = descontarProducto - cantidadUpdate;

                        productoDAO.Descontarventa(descuento, venta);
                        item = 0;
                    }

                    listaVentas.clear();
                    descontar.clear();
                    String factura = ventaDAO.ObtenerNumeroDeFactura();

                    if (factura == null) {
                        factura = "1";
                    } else {
                        numfac = Integer.parseInt(factura) + 1;
                    }
                    request.setAttribute("numerofactura", numfac);

                    break;

                case "Eliminar":
                    itemproducto = Integer.parseInt(request.getParameter("item"));
                    for (int i = 0; i < listaVentas.size(); i++) {

                        if (listaVentas.get(i).getItem() == itemproducto) {
                            request.setAttribute("numerofactura", numfac);
                            listaVentas.remove(i);
                            request.setAttribute("listaventas", listaVentas);
                            request.setAttribute("ventas", venta);
                            request.setAttribute("totalapagar", total1);
                        }

                    }
                    totalapagar = 0;
                    for (int i = 0; i < listaVentas.size(); i++) {
                        totalapagar += listaVentas.get(i).getSubTotal();
                    }
                    formatoNumero1 = NumberFormat.getNumberInstance();
                    total1 = formatoNumero1.format(totalapagar);
                    request.setAttribute("totalapagar", total1);

                    break;

                case "Buscarventa":
                    int NumeroFactura = Integer.parseInt(request.getParameter("NumeroFactura"));

                    venta = ventaDAO.BuscarVenta(NumeroFactura);
                    idventa = venta.getIdventa();
                    request.setAttribute("ventas", venta);

                    break;

                case "EliminarVenta":

                    retorno = ventaDAO.BuscarDetalleVenta(idventa);
                    retorno.add(ventaD);

                    for (int i = 0; i < retorno.size(); i++) {
                        int DevolverProducto = 0;
                        int IdProducto;
                        IdProducto = retorno.get(i).getIdProducto();
                        DevolverProducto = productoDAO.cantidaProducto(IdProducto);
                        DevolverProducto = DevolverProducto + retorno.get(i).getCantidad();
                        System.err.print("la cantidad es" + DevolverProducto);
                        productoDAO.Devolucion(DevolverProducto, IdProducto);

                    }
                    int NuFactura = Integer.parseInt(request.getParameter("NumeroFactura"));
                    int Idventas = Integer.parseInt(request.getParameter("IDventa"));
                    ventaDAO.EliminarVenta(NuFactura);
                    ventaDAO.EliminarDetalle(Idventas);
                    reporteDAO.EliminarReporte(NuFactura);

                    request.setAttribute("ventas", venta);

                    break;

                default:
                    factura = ventaDAO.ObtenerNumeroDeFactura();
                    if (factura == null) {

                        numfac = 1;
                    } else {
                        numfac = Integer.parseInt(factura) + 1;
                    }
                    request.setAttribute("numerofactura", numfac);

            }
            request.getRequestDispatcher("Ventas.jsp").forward(request, response);
        }
        if (menu.equals("Reporte")) {
            switch (accion) {
                case "Reporte":
                    String fechaI = request.getParameter("fechainicio");
                    String fechaF = request.getParameter("fechafinal");
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                    Date fechavalidacion = new Date();
                    sdf.format(fechavalidacion);
                    System.err.print("fechaactua" + fechaI);

                    List lista = reporteDAO.Reporte(fechaI, fechaF);
                    double total = 0;
                    double TotalVestas = reporteDAO.ReporteTotalventa(fechaI, fechaF);
                    total = total + TotalVestas;
                    formatoNumero1 = NumberFormat.getNumberInstance();
                    totalV = formatoNumero1.format(total);
                    request.setAttribute("totaventa", totalV);
                    request.setAttribute("Reporte", lista);

                    break;

            }
            request.getRequestDispatcher("Reporte.jsp").forward(request, response);
        }
        
        if (menu.equals("Cliente_reporte")) {
            switch (accion) {
                case "ver":
                    String fechaI = request.getParameter("fechainicio");
                    String fechaF = request.getParameter("fechafinal");
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                    Date fechavalidacion = new Date();
                    sdf.format(fechavalidacion);
                    

                    List lista = ventaDAO.ReporteCliente(fechaI, fechaF);
                    
                    List listacliente = usuarioDAO.ReporteCliente(fechaI, fechaF);
                    
                    formatoNumero1 = NumberFormat.getNumberInstance();
                    
                    request.setAttribute("venta", lista);
                    request.setAttribute("Usuario", listacliente);

                    break;

            }
            request.getRequestDispatcher("Cliente_reporte.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
