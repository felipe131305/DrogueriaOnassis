<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

        <title>Hello, world!</title>
    </head>
    <body style="background: url(reporte.png)">


        <div class="card ">
            
            <h5 class="card-header " style="text-align:center;background-color: #343a40;color: white" >Gestion de reporte de ventas de medicamentos</h5> 

            <div class="card-body" style="background: url(reporte.png)">
                <form method="post" action="Controlador?menu=Reporte">
                    <div class="row">
                        <div class="col-md-6 d-flex">
                            <input type="date" name="fechainicio" class="form-control" placeholder="ingrese la fehca de inicio en formato 2021-09-01"  required="" value="">
                          <small class="form-text" style="color: black">Ingrese fecha inicial del reporte</small>
                        </div>
                        <div class="col-md-6 d-flex">
                            <input type="date" name="fechafinal" class="form-control" placeholder="ingrese la fehca de final en formato 2021-10-01"  required="" value="" >
                            <small class="form-text" style="color: black">Ingrese fecha final del reporte</small>
                            <input type="submit" name="accion" value="Reporte" class="btn btn-outline-dark">
                            
                        </div>
                    </div>
                    <div class="row"></div>

                </form>
            </div>
        </div>
     
        <div class="col-md-14">
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Cantidad vendida</th>
                        <th scope="col">Nombre del producto</th>
                        <th scope="col">Precio de venta</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reporte" items="${Reporte}">
                        <tr>


                            <th scope="row"> <strong>${reporte.getIdproducto()}</strong></th>
                            <td> <strong>${reporte.getSalidas()} </strong></td>
                            <td> <strong>${reporte.getProducto()}</strong> </td>
                            <td> <strong>${reporte.getPrecioProducto()} </strong> </td>

                        </tr>
                    </c:forEach>




                </tbody>
            </table>
        </div>


        <div class="col-md-10" >
            <strong> <input type=text" style="width: 160px"  name="txttotalapagar" class="float-right" placeholder="$ 00.000.00" disabled="disabled" value="${totaventa}" > </strong>

        </div>



        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
