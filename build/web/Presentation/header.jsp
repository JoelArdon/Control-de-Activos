


<%@page import="Activos.logic.Usuario"%>
  <% Usuario logged= (Usuario) session.getAttribute("usuario");%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  <link href="CSS/estilo.css" rel="stylesheet">
   <%@ include file="/Presentation/head.jsp" %>
</head>   
        <title></title>
    </head>
    <body>
          <header>
        <div class="wrappler">    
            <div class="logo" >Gestion de Activos</div>
            <nav>
                
   <a href="index.jsp">Principal</a></li>
            <% if(logged==null){ %>
  <a href="Presentation/Login/preaprelogin">Iniciar Sesion</a></li>
            <% } %>
    <% if(logged!=null){%>
    <% if(request.getSession()!=null&& logged.getRol().equals("Administrador")){%>
<div class="dropdown">
   <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Administrador
  </button>
  <ul class="dropdown-menu menu" aria-labelledby="dropdownMenu1" >
    <li><a class="dropdown-item" href="Presentation/Solicitud/list">Listar Solicitudes</a></li>
    <li><a class="dropdown-item" href="Presentation/Solicitud/show">Insertar solicitud</a></li>
  </ul>
</div>
   <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <%=logged.getFuncionario().getNombre() %>
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="Presentation/Login/logout">Cerrar sesion</a>
    <a class="dropdown-item" href="#">Editar Perfil</a>
  </div>
</div>
    <% } %> 
              <% if(request.getSession()!=null&& logged.getRol().equals("Secretaria")){%>
<div class="dropdown">
   <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Administrador
  </button>
  <ul class="dropdown-menu menu" aria-labelledby="dropdownMenu1" >
    <li><a class="dropdown-item" href="Presentation/Solicitud/list">Listar Solicitudes</a></li>
  </ul>
</div>
   <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <%=logged.getFuncionario().getNombre() %>
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="Presentation/Login/logout">Cerrar sesion</a>
    <a class="dropdown-item" href="#">Editar Perfil</a>
  </div>
</div>
    <% } %>
             <% if(request.getSession()!=null&& logged.getRol().equals("Jefe")){%>
            <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <%=logged.getFuncionario().getNombre() %>
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="Presentation/Login/logout">Cerrar sesion</a>
    <a class="dropdown-item" href="#">Editar Perfil</a>
  </div>
</div>
            <% } %>     
                <% if(request.getSession()!=null&& logged.getRol().equals("Registrador")){%>
<div class="dropdown">
   <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Administrador
  </button>
  <ul class="dropdown-menu menu" aria-labelledby="dropdownMenu1" >
    <li><a class="dropdown-item" data-toggle="modal" href="#myModal">InsertarCategoria</a></li>
    <li><a class="dropdown-item" data-toggle="modal" href="#ModalCate">ListarCategoria</a></li>
  </ul>
</div>
   <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <%=logged.getFuncionario().getNombre() %>
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="Presentation/Login/logout">Cerrar sesion</a>
    <a class="dropdown-item" href="#">Editar Perfil</a>
  </div>
</div>
    <% } %>
  <% if(request.getSession()!=null&& logged.getRol().equals("JefeRRHH")){%>
<div class="dropdown">
   <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Administrador
  </button>
  <ul class="dropdown-menu menu" aria-labelledby="dropdownMenu1" >
    <li><a class="dropdown-item" data-toggle="modal" href="#myModal">InsertarCategoria</a></li>
    <li><a class="dropdown-item" data-toggle="modal" href="#ModalCate">ListarCategoria</a></li>
  </ul>
</div>
   <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <%=logged.getFuncionario().getNombre() %>
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="Presentation/Login/logout">Cerrar sesion</a>
    <a class="dropdown-item" href="#">Editar Perfil</a>
  </div>
</div>
    <% } %>

    <% } %> 
             
    
          </nav>
               </div>
    </header>

    </body>
</html>
