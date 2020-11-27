<%-- 
    Document   : view
    Created on : May 31, 2019, 11:48:03 AM
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
   <div class="container border " style="width: 60%; position: relative;">
             
              
              
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col" style="font-size: 18px;">No</th>
                        <th scope="col" style="font-size: 18px;">Comprobante</th>
                        <th scope="col" style="font-size: 18px;" >Fecha</th>
                        <th scope="col" style="font-size: 18px;">Tipo</th>
                        <th scope="col" style="font-size: 18px;">Cantidad</th>
                        <th scope="col" style="font-size: 18px;">Total</th>
                        <th scope="col" style="font-size: 18px;">Estado</th>
                        
                        <th scope="col" style="font-size: 18px;">Procesar</th>
                    </tr>
                </thead>
                <tbody id="SolicitudesData"></tbody>
            </table>
   </div>

<!-- Modal -->
<div id="myModal" class="modal " role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 align="center" class="modal-title" >Insertar categoria</h4>
      </div>
      <div class="modal-body">
     <form class="form-horizontal" method="POST" name="formulario" id="formulario">
  <div class="form-group">
    <label class="control-label col-sm-2" for="Codigo">Codigo </label>
    <div class="col-sm-10">
      <input type="number" class="form-control" id="codigo" placeholder="Escriba el codigo de la categoria">
    </div>
  </div>
  <div class="form-group">
      <label class="control-label col-sm-2"  for="nombre">Nombre</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" id="nombre" placeholder="ingresa el nombre">
    </div>
  </div>
  
         
  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" id="insertar" class="btn btn-default">Insertar Categoria</button>
    </div>
  </div>
</form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" >Cerrar</button>
      </div>
    </div>

  </div>
</div>
    </div>
    
  <div id="ModalBien" class="modal " role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        
        <h4 align="center" class="modal-title" >Lista de categorias </h4>
      </div>
      <div class="modal-body">
     <table class="table">
         
  <thead>
    <tr>
      <th scope="col">Numero</th>
      <th scope="col">Descripcion</th>
        <th scope="col">Modelo</th>
      <th scope="col">Precio</th>
         <th scope="col">Cantidad</th>
      <th scope="col">Marca</th>
        <th scope="col">Categoria</th>
      
    </tr>
  </thead>
  <tbody id="BienesData">
 
  </tbody>
</table>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" >Cerrar</button>
        <button type="button" id="procesar" class="btn btn-default">Procesar</button>
      </div>
    </div>

  </div>
</div>
      </div>
      
      
        <div id="ModalActivo" class="modal " role="dialog">
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        
        <h4 align="center" class="modal-title" >Lista de Activos </h4>
      </div>
      <div class="modal-body">
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
      <th scope="col">Asignar datos</th>
       
      
    </tr>
  </thead>
  <tbody id="ActivosData">
 
  </tbody>
</table>
   </div>
          <div class="auto" id="divActivo" style="display: none">
    <form class="form-horizontal" method="POST" name="formulario" id="formulario">
  <div class="form-group">
    <label class="control-label col-sm-2" for="CodigoAct">Codigo </label>
    <div class="col-sm-10">
      <input type="text" disabled class="form-control" id="CodigoAct" placeholder=>
    </div>
  </div>
  <div class="form-group">
      <label class="control-label  col-sm-2"  for="nombreBi">Bien</label>
    <div class="col-sm-10"> 
      <input type="text" disabled class="form-control" id="nombreBi" placeholder=>
    </div>
  </div>
          <div class="form-group">
      <label class="control-label col-sm-2"  for="nombreDep">Dependencia</label>
    <div class="col-sm-10"> 
      <input type="text" disabled  class="form-control" id="nombreDep" placeholder=>
    </div>
  </div>
        <div class="form-group">
      <label class="control-label col-sm-2" disabled  for="nombreCat">Categoria</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" id="nombreCat" placeholder=>
    </div>
  </div>
         <div class="form-group">
      <label class="control-label col-sm-2"  for="puesto">Puesto</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" id="puesto" placeholder=>
    </div>
  </div>
          <div class="form-group">
      <label class="control-label col-sm-2"  for="funcionario">Funcionario</label>
    <div class="col-sm-10"> 
      <input type="text" class="form-control" id="funcionario" placeholder=>
    </div>
  </div>
  
         
  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" id="ModActivo" class="btn btn-default">ModificarActivo</button>
       <button type="button" id="cerrarDivActivo" class="btn btn-default">Cerrar</button>
    </div>
  </div>
</form>
          </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" >Cerrar</button>
      </div>
    </div>

  </div>
</div>
        </div>
    <div id="ModalCate" class="modal " role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 align="center" class="modal-title" >Lista de categorias </h4>
      </div>
      <div class="modal-body">
     <table class="table">
  <thead>
    <tr>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody id="CategoriasData">
 
  </tbody>
</table>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" >Cerrar</button>
           
      </div>
    </div>

  </div>
</div>
        </div>
    <script>
          function loaded(event){	
  
document.getElementById("insertar").addEventListener("click",insertar);
document.getElementById("ModActivo").addEventListener("click",ModActivo);
document.getElementById("procesar").addEventListener("click",procesar);
document.getElementById("cerrarDivActivo").addEventListener("click",cerrarDivActivo);


listarCategorias();
listarSolicitudes();


 
    }
    function insertar(){
       categoria = {codigo:document.getElementById("codigo").value,
           nombre:document.getElementById("nombre").value};
       ajax({type:"POST",
           url:"api/categorias",
           data: JSON.stringify(categoria),
           contentType : "application/json"}).then(function(){limpiacampos  ();mensaje();},
               function(){alert("no funciona");})
               
  }
  function mensaje(){
    
      alert("Se inserto la categoria con exito");
  }
  
  function limpiacampos(){
      document.getElementById("nombre").value = " ";
      document.getElementById("codigo").value = " ";
     
     
     
  }
  function cerrarDivActivo(){
             $(document).ready(function()
   {
      $("#divActivo").hide();
   });
  }
   function listAct(Activos){
    var listado=document.getElementById("ActivosData");
   listado.innerHTML="";
    Activos.forEach((s)=>{rowActivo(listado,s);});	
  }
  function rowActivo(listado,Activos){
    
  	var tr =document.createElement("tr");
	tr.innerHTML="<td>"+Activos.codigo+"</td>"+
				"<td >"+Activos.bien.descripcion+"</td>"+
				"<td >"+Activos.dependencia.nombre+"</td>"+ 
				"<td>"+Activos.categoria.nombre+"</td>"+
				"<td>"+Activos.funcionario+"</td>"+
                                "<td>"+Activos.puesto+"</td>"+
                                  "<td><img src='https://img.icons8.com/ios/50/000000/assignment-return.png' onclick='editarActivo(\""+Activos.codigo+"\");'></td>";
	listado.appendChild(tr); 
  }
  function cargaDatosfunc(Activo){
      document.getElementById("CodigoAct").value = Activo.codigo;
        document.getElementById("nombreBi").value = Activo.bien.descripcion;
          document.getElementById("nombreDep").value = Activo.dependencia.nombre;
            document.getElementById("nombreCat").value = Activo.categoria.nombre;
              document.getElementById("puesto").value = Activo.puesto;
                document.getElementById("funcionario").value = Activo.funcionario;
  }
  function editarActivo(codigoActivo){
        $(document).ready(function()
   {
      $("#divActivo").show();
   });
    ajax({type: "GET", 
          url:"api/activos/get?codigo="+codigoActivo,                
        }).then(function (Activo){cargaDatosfunc(Activo);},
                function(status){ alert(errorMessage(status));}); 
                
   
   
  }
  function procesar(){
      var ArrayCat = document.getElementsByClassName('categorias');
      Ar =[];
      for(var i = 0 ;i<ArrayCat.length;i++){
          Ar.push(ArrayCat[i].options[ArrayCat[i].selectedIndex].id);
      }
      
      console.log(JSON.stringify(Ar));
    ajax({ async: false,
        type:"POST",
           url:"api/bienes",
           
           data: JSON.stringify(Ar),
           contentType : "application/json"
        }).then(function (){listarSolicitudes(); mensajeSolictitud();},
                function(status){ alert("fregados");}); 
  }
  function mensajeSolictitud(){
       $(document).ready(function()
   {
      $("#ModalBien").modal("hide");
   });
   alert("Se proceso la solicitud correctamente");
  }
   function listCat(Categoria){
    var listado=document.getElementById("CategoriasData");
    listado.innerHTML="";
    Categoria.forEach((s)=>{rowCat(listado,s);});	
  }
  
  function rowCat(listado,Categorias){
  	var tr =document.createElement("tr");
	tr.innerHTML="<td>"+Categorias.nombre+"</td>"+
				"<td>"+Categorias.codigo+"</td>";                          
	listado.appendChild(tr); 
  }
  function listarCategorias(){
       
        ajax({type: "GET", 
          url:"api/categorias"
        }).then(function (Categorias){listCat(Categorias);},
                function(status){ alert(errorMessage(status));}); 
  }
  function rowBien(listadoB,bien){
      var tr =document.createElement("tr");
	tr.innerHTML="<td id='bien'>"+bien.numero+"</td>"+
				"<td>"+bien.descripcion+"</td>"+
                                "<td>"+bien.modelo+"</td>"+
                                "<td>"+bien.precio+"</td>"+
                                "<td>"+bien.cantidad+"</td>"+
                                "<td>"+bien.marca+"</td>"+
                                 "<td><select class='categorias'id='categorias' name='opciones'></select></td>";
	listadoB.appendChild(tr);
  }
  function rowSol(listadoS,Solicitudes){
      var tr =document.createElement("tr");
      if (Solicitudes.estado == 'Por Verificar' ){
	tr.innerHTML="<td>"+Solicitudes.numero+"</td>"+
				"<td>"+Solicitudes.comprobante+"</td>"+
                                "<td>"+Solicitudes.fecha+"</td>"+
                                "<td>"+Solicitudes.tipo+"</td>"+
                                "<td>"+Solicitudes.cantidad+"</td>"+
                                "<td>"+Solicitudes.total+"</td>"+
                                "<td>"+Solicitudes.estado+"</td>"+
                                "<td><img src='https://img.icons8.com/ios/50/000000/assignment-return.png' onclick='asignar(\""+Solicitudes.numero+"\");'></td>";
                    }
                    if(Solicitudes.estado=='Espera de rotulacion'){
	tr.innerHTML="<td>"+Solicitudes.numero+"</td>"+
				"<td>"+Solicitudes.comprobante+"</td>"+
                                "<td>"+Solicitudes.fecha+"</td>"+
                                "<td>"+Solicitudes.tipo+"</td>"+
                                "<td>"+Solicitudes.cantidad+"</td>"+
                                "<td>"+Solicitudes.total+"</td>"+
                                "<td>"+Solicitudes.estado+"</td>"+
                                 "<td><button id='Procesar' type='button' onclick='AsingaProcesada("+Solicitudes.numero+")' class='btn btn-secondary'>procesada</button><button id='valida' onclick='asignarActivos(\""+Solicitudes.numero+"\");' type='button' onclick='validaEstado()' class='btn btn-secondary'>Asignar Act.</button></td>";
                    }
                         if(Solicitudes.estado=='Procesada'){
	tr.innerHTML="<td>"+Solicitudes.numero+"</td>"+
				"<td>"+Solicitudes.comprobante+"</td>"+
                                "<td>"+Solicitudes.fecha+"</td>"+
                                "<td>"+Solicitudes.tipo+"</td>"+
                                "<td>"+Solicitudes.cantidad+"</td>"+
                                "<td>"+Solicitudes.total+"</td>"+
                                "<td>"+Solicitudes.estado+"</td>"+
                                 "<td>Procesada</td>";
                    }
	listadoS.appendChild(tr);
  }
  function asignarActivos(numero){
      {
     $(document).ready(function()
   {
      $("#ModalActivo").modal("show");
   });
   ajax({type: "GET", 
          url:"api/activos?numero="+numero                
        }).then(function (Activos){listAct(Activos);},
                function(status){ alert(errorMessage(status));}); 
               
  } 
  }
   function selectR(listadoJ,Categoria){
	var option =document.createElement("option");
	option.id = Categoria.codigo;
        
       option.innerHTML="<option >"+Categoria.nombre+"</option>";
        
        				                             
	listadoJ.appendChild(option);
  }
  function listS(Solicitud){
      var listado=document.getElementById("SolicitudesData"); 
     
    listado.innerHTML="";
    Solicitud.forEach((s)=>{rowSol(listado,s);});	
 
  }
  function listarSolicitudes(){
      
        ajax({type: "GET", 
          url:"api/categorias/listar"                
        }).then(function (Solicitudes){listS(Solicitudes);},
                function(status){ alert(errorMessage(status));}); 
  } 
  function listaB(Bien){
       var listado=document.getElementById("BienesData"); 
      listado.innerHTML="";
    Bien.forEach((s)=>{
        rowBien(listado,s);
         });
          ajax({type: "GET", 
          url:"api/categorias"
        }).then(function (Categorias){
               var selectores = Array.from(document.getElementsByClassName('categorias'))
               selectores.forEach(selector=>{
                Categorias.forEach((s)=>{selectR(selector,s,Bien);})
               })
            },function(status){ alert(errorMessage(status));});
  }
 
  function asignar(numero)
  {
     $(document).ready(function()
   {
      $("#ModalBien").modal("show");
   });
   ajax({type: "GET", 
          url:"api/bienes?numero="+numero,                
        }).then(function (Bienes){listaB(Bienes);},
                function(status){ alert(errorMessage(status));}); 
                
                
  } 
  function ModActivo(Activo){
  var  codigo =  document.getElementById("CodigoAct").value;
          var puest=   document.getElementById("puesto").value;
          var funcionar=     document.getElementById("funcionario").value;
                Activo ={codigo:codigo,puesto:puest,funcionario:funcionar};
                console.log( JSON.stringify(Activo));
                ajax({type:"PUT",url:"api/activos", data: JSON.stringify(Activo),contentType : "application/json"
        }).then(function(Activos){listAct(Activos);validaEstado()},
                function(status){ alert(errorMessage(status));}); 
                
                
  }
  function validaEstado(){
       var table = document.getElementById( "ActivosData" );
       var bandera = false;
       var aux = table.rows.length;
       var contador=0;
       for ( var i = 0; i < table.rows.length; i++ ) {
 
       if(table.rows[i].cells[5].innerHTML!=" "&&table.rows[i].cells[6].innerHTML!=" "){
           contador++;
       }
       if(contador == aux){
           bandera = true;
       }
       console.log(table.rows[i].cells[5].innerHTML);
    
  }
  console.log(contador);
         console.log(bandera);
         return bandera;
  }
  function AsingaProcesada(SolNumero){
      if(validaEstado()==true){
             ajax({type: "PUT", 
          url:"api/solicitudes/procesada",
          data:SolNumero,
          contentType: "application/json"
        }).then(function (){listarSolicitudes();},
                function(status){ alert("no funciona");}); 
      }
      else{
          alert("Debe asignar los responsables a los activos para poder procesar la solicitud");
      }
  }
   
   document.addEventListener("DOMContentLoaded",loaded);
</script>
          <%@ include file="/Presentation/footer.jsp" %>
    </body>
</html>
