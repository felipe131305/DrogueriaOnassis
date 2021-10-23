<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <title>Sistema de ventas</title>
        <style>
            @media print{
                .seccion1, .btn, .columna{
                    display:none;
                }
            }
        </style>
    </head>
    <body>
        <div class="row">
            <div class="col-md-5 seccion1">
                <div class="card">
                    <h5 class="card-header">Datos cliente y trabajador</h5>
                    <div class="card-body">
                        <form method="post" action="Controlador?menu=Ventas">
                            <div class="row">

                                <div class="col-md7 d-flex form-group">
                                    <input type="number" name="documentocliente" class="form-control" placeholder="documento cliente" value="${cliente.getDocumento()}" required="">

                                </div>
                                <div class="col-md8 d-flex form-group">
                                    <input type="number" name="documentoempleado" class="form-control" placeholder="documento empleado" value="${empleado.getDocumento()}" required="">

                                </div>
                                <input type="submit" name="accion" value="Buscar" class="btn btn-outline-dark">
                                <c:if test="${Validar ==1}">
                                    <script>
                                        {
                                            alert("El Trabajador no se encuentra registrado")
                                        }
                                    </script>
                                </c:if>

                                <div class="col-md-6 d-flex">
                                    <input type="text" name="nombrecliente" class="form-control" placeholder="Nombre cliente" value="${cliente.getNombre()}">
                                </div>
                            </div>
                            <div class="row"></div>

                        </form>
                    </div>
                </div>
                <div class="card">
                    <h5 class="card-header">Datos producto</h5>
                    <div class="card-body">
                        <form action="Controlador?menu=Ventas" method="post">
                            <div class="row">
                                <div class="col-md-6 d-flex form-group">
                                    <input type="number" name="codigoproducto" class="form-control" placeholder="Codigo Producto" value="${productoseleccionado.getIdProducto()}" required="">
                                    <input type="submit" name="accion" value="Buscarproducto" class="btn btn-outline-dark">
                                    <c:if test="${Validarp ==1}">
                                        <script>
                                            {
                                                alert("El codigo del producto no se encuentra registrado por favor verifique nuevamente")
                                            }
                                        </script>
                                    </c:if>
                                </div>
                                <div class="col-md-7 d-flex form-group">
                                    <input type="text" name="nombreproducto" class="form-control" placeholder="Nombre Producto"value="${productoseleccionado.getNombreProducto()}">
                                </div>
                                <div class="col-md-4 d-flex form-group">
                                    <input type="text" name="precioproducto" class="form-control" placeholder="$ 0000.00" value="${productoseleccionado.getPrecioProducto()}">
                                </div>

                                <div class="col-md-8 d-flex form-group">
                                    <input type="number"  name="cantidadproductop" class="form-control" placeholder="Cantidad en stock"value="${productoseleccionado.getCantidadProducto()}"readonly="" >
                                </div>
                                <small class="form-text text-muted">Cantidad disponible</small>
                                <div class="col-md-8 d-flex form-group">
                                    <input type="number"  name="cantidadproducto" class="form-control" placeholder="Cantidad de venta" required="" value="1">
                                </div>
                                <small class="form-text text-muted">Cantidad para vender</small>

                            </div>
                            <input type="submit" name="accion" value="AgregarProducto" class="btn btn-outline-primary col text-center">
                            <div class="row"></div>

                        </form>
                    </div>
                </div>

                <div class="card">
                    <h5 class="card-header">Cancelar Venta</h5>
                    <div class="card-body">
                        <form action="Controlador?menu=Ventas" method="post">
                            <div class="row">
                                <div class="col-md-8 d-flex form-group">
                                    <script>
                                        function mostrarMensaje() {
                                            alert("La venta ha sido eliminada")
                                        }
                                    </script>
                                    <input type="number" name="NumeroFactura" class="form-control" placeholder="Numero de factura" value="${ventas.getComprobante()}" required="">
                                    <input type="submit" name="accion" value="Buscarventa" class="btn btn-outline-danger">

                                </div>
                                <div class="col-md-7 d-flex form-group">
                                    <input type="text" name="IDventa" class="form-control" placeholder="Id de la venta"value="${ventas.getIdventa()}">
                                </div>
                                <div class="col-md-4 d-flex form-group">
                                    <input type="text" name="TotalVneta" class="form-control" placeholder="total de la venta" value="${ventas.getMonto()}">
                                </div>




                            </div>
                            <input type="submit" name="accion" value="EliminarVenta" class="btn btn-outline-primary col text-center" onclick="mostrarMensaje()">
                            <div class="row"></div>

                        </form>
                    </div>
                </div>

            </div>





            <div class="col-md-7">
                <div class="card">
                    <div class="card-header">
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label">Numero de factura</label>
                            <input class="form-control col-md-4" type="text" name="txtnumerofactura" value="${numerofactura}">
                        </div>

                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">item</th>
                                    <th scope="col">Codigo</th>
                                    <th scope="col">Producto</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Total</th>
                                    <th scope="col" class="columna">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="lista" items="${listaventas}">
                                    <tr>
                                        <th scope="row" style="width: 30px;">${lista.getItem()}</th>
                                        <td style="width: 30px;">${lista.getIdProducto()}</td>
                                        <td style="width: 350px;">${lista.getDescripcionproducto()}</td>
                                        <td>$ ${lista.getPrecio()}</td>
                                        <td style="width: 30px;">${lista.getCantidad()}</td>
                                        <td>$ ${lista.getSubTotal()}</td>
                                        <td class="columna">
                                            <a class="btn btn-danger"href="Controlador?menu=Ventas&accion=Eliminar&item=${lista.getItem()}" >Eliminar</a>

                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-md-8 text-center">
                            <c:if test="${listaventas !=null}">
                                <a class="btn btn-success" onclick="print()" href="Controlador?menu=Ventas&accion=GenerarVenta">Generar Venta</a>

                            </c:if>




                        </div>
                        <div class="col-md-4">  
                            <input type=text" name="txttotalapagar" class="form-control" placeholder="$ 00.000.00" disabled="disabled" value="${totalapagar}">
                        </div>





                    </div>



                </div>

                <div class="card">
                    
        
                    <div class="col-md-12 text-right">
                        <form method="post" action="Controlador?menu=Ventas">
                            <div class="row">
                                <div class="col-md7 d-flex form-group">
                                <input type="submit" name="accion" value="cambio" class="btn btn-outline-primary col text-center">
                                </div>
                                <div class="col-md8 d-flex ">
                                    
                                    <input type="number" name="efectivo" class="form-control" placeholder="ingrese efectivo" value="${efectivo}" required="">
                                </div>
                                
                               
                                <div class="col-md-3 d-flex">
                
                                    <input type="text" name="vueltas"  class="form-control" placeholder="su cambio es " value="${cambio}" disabled="disabled">
                                </div>
                                
                            </div>
                            <div class="row"></div>

                        </form>
                    </div>

                </div>









            </div>



            <!-- Optional JavaScript; choose one of the two! -->

            <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

            <!-- Option 2: jQuery, Popper.js, and Bootstrap JS
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
            -->
    </body>
</html>





