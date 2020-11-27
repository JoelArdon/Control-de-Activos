<%-- 
    Document   : view
    Created on : Mar 26, 2019, 3:55:37 PM
    Author     : Pc
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Activos.logic.Bien"%>
<%@page import="Activos.logic.Solicitud"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
             <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
        <%@ include file="/Presentation/head.jsp" %>
    </head>
    <body>
        <%@ include file="/Presentation/header.jsp" %>
        <% Solicitud model = (Solicitud) request.getAttribute("solicitud");%>
         <% Bien bien    = (Bien) request.getAttribute("bien");%>
         <% Map<String,String> errors = (Map<String,String>) request.getAttribute("errors"); %>
         <% Map<String,String> errorsBien = (Map<String,String>) request.getAttribute("errorsBien"); %>
             <% Map<String,String[]> values =(errorsBien== null)? this.getValues(bien):request.getParameterMap();%>
          
         
        <div style="text-align: center;">
                <h1 style="color: #373B60;">Solicitud</h1>
            <form class="form "  method="POST" name="formulario" action= "Presentation/Solicitud/show/modificar">
                <table class="table-striped wrappler" style=" background-color: #E8E8E8;">
                    <tr>

                            <td>#Comprobante</td>  <td><input class=" form-control <%=validity("comprobante", errors)%> " type="text" name="comprobante" value="<%=model.getComprobante()%>" style="width: 80px;" ></td>
                        <td> Fecha de adquisicion</td><td>  <input id="datepicker" width="270" name="fecha" value="<%=model.getFecha()%>" /> </td>
                        <td> Tipo de adquisicion </td><td><select class=" form-control <%=validity("tipo", errors)%> "  name="tipo" id="exampleFormControlSelect1" value="<%=model.getTipo()%>">
                                <option>Compra</option>
                                <option>Donacion</option>
                                <option>Produccion Institucional</option
                            </select></td>
                            <td> Cantidad </td><td><input class=" form-control <%=validity("cantidadsol", errors)%> " type="number" name="cantidadsol" disabled value=" <%=model.getCantidad() %>"style="width: 80px;" ></td>
                            <td> Total </td><td><input class=" form-control <%=validity("total", errors)%> " type="number" name="total" disabled value="<%=model.getTotal()%>"style="width: 80px;"></td>
                    </tr>
                </table>

                <button type="submit" name="submit" class=" login btn btn-primary btn-block wrappler " style="width: 110px;margin-top: 10px;">Enviar</button>
            </form>

        </div>
        <div style="text-align: center;">
            <form class="form "  method="POST" name="formulario" action="Presentation/Solicitud/show/insert">
                <table class="wrappler table-striped">
                    <tr>
                        <div style="text-align: center;">
                            <h1  style="color: #373B60;"> Bien  </h1>
                            
                            
                        <td>Descripcion</td> <td><input class="form-control <%=validityBien("descripcion", errorsBien)%>" type="text" name="descripcion" style="width: 120px;" value="<%=value("descripcion", values)%>" </td>
                        <td>Marca</td>  <td><input class="form-control <%=validityBien("marca", errorsBien)%>" type="text" name="marca"  style="width: 120px;" value="<%=value("marca", values)%>"  ></td>
                        <td>Modelo</td><td><input class="form-control <%=validityBien("modelo", errorsBien)%>"  type="text" name="modelo" style="width: 120px;" value="<%=value("modelo", values)%>" ></td>
                        <td>Precio </td><td><input class="form-control <%=validityBien("precio", errorsBien)%>" type ="number" class="form-control" name="precio" id="exampleFormControlSelect1"  style="width: 120px;"value="<%=value("precio", values)%>" ></td>
                        <td>Cantidad</td><td><input class="form-control <%=validityBien("cantidad", errorsBien)%>" type ="number" name="cantidad" style="width: 90px;" value="<%=value("cantidad", values)%>"   ></td>     

                        </tr>
                        </table>

                        <button type="submit" name="submit" class="login btn btn-primary btn-block wrappler" style="width: 140px;margin-top: 10px;margin-bottom: 10px;">Agregar Bien</button>
                        </form>

                    </div>
                    <div class="table-wrapper-scroll-y my-custom-scrollbar " style=" text-align: center; ">
                        <table class="table table-bordered table-striped mb-0 wrappler" style="width: 80%;">
                            <thead><tr><th>Descripcion</th><th>Marca</th><th>Modelo</th><th>Precio</th><th>Cantidad</th></tr></thead>
                            <tbody >
                                <% for (Bien p : model.getLista_bienes()) {%>
                                <tr>

                                    <td><%=p.getDescripcion()%></a></td>
                                    <td><%=p.getMarca()%></a></td>
                                    <td><%=p.getModelo()%></a></td>
                                    <td><%=p.getPrecio()%></a></td>
                                    <td><%=p.getCantidad()%></a></td>

                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                 
                    <%@ include file="/Presentation/footer.jsp" %>
                      <script>
        $('#datepicker').datepicker({
            uiLibrary: 'bootstrap',format: 'yyyy-mm-dd'
 
            
        });
    </script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
                    </body>
                    </html>
<%!
private String validity(String field, Map<String,String> errors){
      if ( (errors!=null) && (errors.get(field)!=null) )
        return "is-invalid";
      else
        return "";
    }

private String validityBien(String field, Map<String,String> errorsBien){
      if ( (errorsBien!=null) && (errorsBien.get(field)!=null) )
        return "is-invalid";
      else
        return "";
    }
   private String value(String field, Map<String,String[]> values){
        return values.get(field)[0];
    }
  private Map<String,String[]> getValues(Bien model){
       Map<String,String[]> values = new HashMap<>();
       values.put("descripcion", new String[]{model.getDescripcion()});
       values.put("marca", new String[]{model.getMarca()});
       values.put("modelo", new String[]{model.getModelo()});
       values.put("precio", new String[]{Integer.toString(model.getPrecio())});
       values.put("cantidad", new String[]{Integer.toString(model.getCantidad())});
       return values;
    } 


   %>