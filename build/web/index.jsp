<%-- 
    Document   : index
    Created on : 15/03/2019, 03:37:04 PM
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
        <link href="CSS/estilo.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/Presentation/header.jsp" %>
        <div class="card  wrappler" style="height: auto; width: 50%; background-color: #E2E3E7; padding-top: 50px;">
        <h1 >Bienvenido al Sistema de Gestión de Activos</h1>
        <section >
             El sistema de activos brinda los servicios de información requeridos para la ejecución 
                de un conjunto de proces y actividades realizados por la Oficina Central de Control
                de Bienes (OCCB) y las Unidades de Apoyo Administrativo adscritas a cada facultad o dependencia
                central y que permitan:
                 <ul>
                <li>Realizar el control físico de los bienes y muebles que forman parte de  los activos 
                    fijos tangibles de la Universidad.</li>
                <li>Efectuar el registro de todas las adquisiciones de los bienes realizados por la
                    institución por concepto de compra, donación o producción institucional.</li>
                <li>Mantener el registro y control del movimiento de bienes entre las dependecias
                    universitarias.</li>
                <li>Efectuar el control sobre el estado,uso,conservación y custodia de los bienes de
                    la institución.</li>
             </ul>
        </section>
        </div>
        
        <%@ include file="/Presentation/footer.jsp" %>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
    </body>
</html>
