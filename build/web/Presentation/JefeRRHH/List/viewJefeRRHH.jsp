<%-- 
    Document   : viewJefeRRHH
    Created on : Jun 4, 2019, 9:13:05 PM
    Author     : Pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
           <%@ include file="/Presentation/head.jsp" %>
               <script src="JS/ajax.js"></script>
        <title></title>
    </head>
    <body>
          <%@ include file="/Presentation/header.jsp" %>
        
          <div class="container border " style="width: 100%; position: relative;">
                <table class="table table-borderless "style="width:15px">
  <thead>
    <tr>
        <th scope="col"><button type="button" id="BuscarTema" class="btn btn-primary">Buscar</button> </th>
      <th scope="col"> <input style="width:150px" type="text"  class="form-control" id="Buscar" placeholder=Buscar></th>
     
    </tr>
  </thead>
  <tbody id="BienesData">
 
  </tbody>
</table>
              <div class="table-wrapper-scroll-y my-custom-scrollbar">
     
  <table class="table table-bordered table-striped mb-0">
    <thead>
         
  <thead>
    <tr>
      <th scope="col">Codigo</th>
      <th scope="col">Bien</th>
        <th scope="col">Dependencia</th>
      <th scope="col">Categoria</th>
         <th scope="col">Puesto</th>
      <th scope="col">Funcionario</th>
      
       
      
    </tr>
  </thead>
  <tbody id="ActivosData">
 
  </tbody>
      </table>
          </div>
              <script src="JS/Activos.js"></script>
      
      
    </body>
</html>
