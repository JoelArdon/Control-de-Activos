

<%@page import="Activos.logic.Solicitud"%>
<%@page import="java.util.List"%>

<%@page import="Activos.logic.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
             <% Usuario usuario= (Usuario) session.getAttribute("usuario");%> 
               <%@ include file="/Presentation/head.jsp" %>

        <title></title>
    </head>
    <body>

        <%@ include file="/Presentation/header.jsp" %>

        <div class="wrappler" style="margin-top: 50px;">
            <h1 class="wrappler" style="width: 40%; "> Lista de Solicitudes Escuela de <%=usuario.getFuncionario().getDependencia().getNombre()%></h1>
            <% List<Solicitud> model = (List<Solicitud>) request.getAttribute("lista");%>
            <div class="table-responsive"> 
                <div class="table-wrapper-scroll-y my-custom-scrollbar " style=" text-align: center; ">
                    <table >
                      <form class="form "  method="POST" name="formulario" action="Presentation/Solicitud/list/buscar">
                          <thead> <tr><th><th><div style="margin-right:  10px;"> Comprobante</th><th> 
                              
                              <input type="text" name="compbuscar"></th><th> <button type="submit" name="submit" class=" btn-bucar btn btn-primary btn-block" style="margin-left: 5px; background-color: transparent;border: 0;"><img src="https://img.icons8.com/ios/50/000000/google-web-search-filled.png"></th></tr></thead>
                    </form>
                    </table>
                    <table class="table table-bordered table-striped mb-0" style="width: 200%;padding-top: 50%;">
                        <thead><tr><th>No.</th><th>Comprobante</th><th>Fecha</th><th>Tipo</th><th>Estado</th></tr></thead>
                        <tbody >
                            <% for (Solicitud p : model) {%>
                            <tr>
                                <td><a href="Presentation/Solicitud/show?numero=<%=p.getNumero()%>"</a><%=p.getNumero()%></td>
                                <td><%=p.getComprobante()%></a></td>
                                <td><%=p.getFecha()%></a></td>
                                <td> <img src="<%=iconoTipo(p.getTipo())%>" alt="<%=p.getTipo()%>" title="<%=p.getTipo()%>"> </a></td>
                               <td>        <% if(usuario.getRol().equals("Secretaria")) { %>
                                <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="tipo" >
                            <option name="estado" selected value="Recibida"> Recibida </option>
                            <option name="estado" value="Rechazada">Rechazada</option>
                             <option name="estado" value="Por Verificar">Por Verificar</option>
                             
                                    </select>
                                    <% } %> 
                                     <% if(usuario.getRol().equals("Administrador")) { %>
                                    <img src="<%=iconoEstado(p.getEstado())%>" alt="<%=p.getEstado()%>" title="<%=p.getEstado()%>"> </a></td>
                          <% } %> 

                            </tr>
                            <% }%>
                        </tbody>
                    </table>

                </div>
            </div>
           </div>
                 <%@ include file="/Presentation/footer.jsp" %>


    </body>
</html>
<%! 
    private String iconoEstado(String estado){
        switch(estado){
            case "Recibida":
                return  "https://img.icons8.com/material/24/000000/reading-confirmation.png"; 
            case "Pendiente": 
                return "https://img.icons8.com/material/24/000000/hourglass.png";
            case "Rechazada":
                return "https://img.icons8.com/metro/26/000000/delete-sign.png";
            case "Espera rotulacion":
                return "https://img.icons8.com/material/24/000000/tags.png";
            case "Procesada":
                return "https://img.icons8.com/ios/50/000000/checkmark-filled.png";
            
              
        }
        return "";
    }
    
    private String iconoTipo(String tipo){
        switch(tipo){
            case "Compra": 
                return "https://img.icons8.com/ios/50/000000/buy-filled.png";
            case "Donacion":
                return "https://img.icons8.com/dusk/64/000000/receive-cash.png";
            case "Produccion Institucional":
                return "https://img.icons8.com/ios/50/000000/robot-filled.png";
        }
        return "";
    }
%>
    

