<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="all.css" rel="stylesheet"> <!--load all styles -->
        <title>Hello, world!</title>
  
    </head>
    <body style="background: url(medicamentos.jpg)">

        <div class="row">
            
             <div class="card col-md-4" style="background: url(medicamentos.jpg)">
                <div class="card-body">
                    <h5 class="card-title">Productos</h5>
                    <b <h6 class="card-subtitle mb-2 text-black">En este panel podras gestionar los registros de los productos</h6></b>
                    <div>
                <form action="Controlador?menu=Productos" method="POST">
                    <div class="form-group">
                        <label>Nombre de producto</label>
                        <input type="text" class="form-control" name="txtNombreProducto" value="${producto.getNombreProducto()}" required="digite el precio del producto">
                    </div>
                    <div class="form-group">
                        <label>Descripcion producto</label>
                        <input type="text" class="form-control" name="txtDescripcion" value="${producto.getDescripcion()}"required="">
                    </div>
                    <div class="form-group">
                        <label>Precio</label>
                        <input type="number" class="form-control" name="txtprecio" value="${producto.getPrecioProducto()}" min="0" max="999999999" required="digite el precio del producto">
                    </div>
                    <div class="form-group">
                        <label>Cantidad</label>
                        <input type="number" class="form-control" name="txtcantidad" value="${producto.getCantidadProducto()}" min="0" max="999999999" required="digite el precio del producto" >
                    </div>


                    <button type="submit" class="btn btn-primary" name="accion" value="Agregar">Guardar</button>
                    <input type="submit" class="btn btn-success" name="accion" value="Actualizar" ></button>
                </form>
                </div>
                </div>
            </div>
            <div class="col-md-8">
                <table class="table table-striped">
                    <thead>
                           <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nombre producto</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">precio</th>
                            <th scope="col">Acciones</th>
                             </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="producto" items="${Producto}">
                        <tr>
                            <th scope="row">${producto.getIdProducto()}</th>
                            <td>${producto.getNombreProducto()}</td>
                            <td>${producto.getDescripcion()}</td>
                            <td>${producto.getPrecioProducto()}</td>
                            
                            <td>
                                <a class="btn btn-warning" href="Controlador?menu=Productos&accion=Cargar&id=${producto.getIdProducto()}">Cargar</a>
                               <a class="btn btn-danger" href="Controlador?menu=Productos&accion=Eliminar&id=${producto.getIdProducto()}">Eliminar</a>
                            </td>
                           </tr>
                        </c:forEach>
                    </tbody>
                </table>
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