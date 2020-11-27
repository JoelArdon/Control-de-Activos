<%@page import="Activos.logic.Usuario"%>
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
               <script src="JS/ajax.js"></script>

        <title></title>
    </head>
    <body>

        <%@ include file="/Presentation/header.jsp" %>
        <%if(logged.getRol().equals("Jefe")) { %>
                <h3 class="font-weight-normal " style=" position: relative; text-align: center;" > Solicitudes </h3>
                <div class="form-row align-items-center">
                    <div class="col-sm-3 my-1">
                       
                    </div>
                    <div class="col-sm-3 my-1">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                
                            </div>
                            <input class="form-control" type="text" name="Comprobante"  size=50 maxlength=50 value="" id="comprobante" >
                        </div>
                    </div>
               
                    
                    <div class="col-auto my-1">
                        <input  type="button" id="Buscar"  class="btn btn-primary"  value="Buscar"  >
                    </div>
                
                
                </div>
            </form>
        </div>   
        <br>
        
        <div class="container border " style="width: 60%; position: relative;">
              <%List<Solicitud> soli = (List<Solicitud>) request.getAttribute("solicitudes");%>
            
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
                        <th scope="col" style="font-size: 18px;">Registrador ID</th>
                        <th scope="col" style="font-size: 18px;">Asignar</th>
                    </tr>
                </thead>
                <tbody id="listadoJ"></tbody>
            </table>
        <%}%>
           <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col" style="font-size: 18px;">Nombre</th>
                        <th scope="col" style="font-size: 18px;">ID</th>
                    </tr>
                </thead>
                <tbody id="RegistradoresTabla"></tbody>
            </table>

        
        
        <script>
         function loaded(event){	     
document.getElementById("Buscar").addEventListener("click",buscarJ);
buscarJ();

 
    }
      function rowJ(listadoJ,Solicitudes){
	var tr =document.createElement("tr");
	tr.innerHTML="<td>"+Solicitudes.numero+"</td>"+
				"<td>"+Solicitudes.comprobante+"</td>"+
                                "<td>"+Solicitudes.fecha+"</td>"+
                                "<td>"+Solicitudes.tipo+"</td>"+
                                "<td>"+Solicitudes.cantidad+"</td>"+
                                "<td>"+Solicitudes.total+"</td>"+
                                "<td>"+Solicitudes.estado+"</td>"+
                                "<td><select class='registradores'id='registradores' name='opciones'></select></td>"+
                                "<td><img src='https://img.icons8.com/metro/26/000000/checked.png' onclick='asignar(\""+Solicitudes.numero+"\");'></td>";
	listadoJ.appendChild(tr);
        
  }
    function listJ(Solicitud){
    var listado=document.getElementById("listadoJ"); 

        listado.innerHTML="";
    Solicitud.forEach((s)=>{
        rowJ(listado,s);
        });	
         ajax({type: "GET", 
          url:"api/solicitudesJefe/registradores?registrador=",                
        }).then(function (Usuarios){
               var selectores = Array.from(document.getElementsByClassName('registradores'))
               selectores.forEach(selector=>{
                Usuarios.forEach((s)=>{selectR(selector,s,Solicitud);})
               })
            },function(status){ alert(errorMessage(status));});
     }
   function buscarJ(){
   var comprobante=document.getElementById("comprobante").value;
    ajax({type: "GET", 
          url:"api/solicitudesJefe?comprobante="+comprobante,                
        }).then(function (Solicitudes){listJ(Solicitudes);},
                function(status){ alert(errorMessage(status));}); 
  } 
    function selectR(listadoJ,Usuario){
	var option =document.createElement("option");
	 option.id =Usuario.id;
      
        
       option.innerHTML="<option selected>"+Usuario.funcionario.nombre+"</option>";
        
        				                             
	listadoJ.appendChild(option);
  }
   function asignar(comprobante){
        var select =document.getElementById("registradores");
        var nombre = select.options[select.selectedIndex].id;
        
        funcionario = {id:comprobante,nombre:nombre}; 
      console.log(JSON.stringify(funcionario));
  
        ajax({type: "PUT", 
          url:"api/solicitudesJefe",
          data:JSON.stringify(funcionario),
          contentType: "application/json"
       
        }).then(function (Solicitudes){listJ(Solicitudes);
                                       alert(nombre+" ha sido asignado como registrador para la solicitud con el comprobante "+comprobante);},
                function(status){ alert("no funciona");}); 
  }
    
    
  document.addEventListener("DOMContentLoaded",loaded);
    </script>

 </body>
</html>
