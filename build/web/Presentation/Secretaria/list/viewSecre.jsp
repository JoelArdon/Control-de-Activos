

<%@page import="Activos.logic.Solicitud"%>
<%@page import="java.util.List"%>

<%@page import="Activos.logic.Funcionario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
             
              <%@ include file="/Presentation/head.jsp" %>
               <script src="JS/ajax.js"></script>
           <title></title>
    </head>
    <body>
            <%@ include file="/Presentation/header.jsp" %>

      

   
                <h3 class="font-weight-normal " style=" position: relative; text-align: center;" > Solicitudes </h3>
                <div class="form-row align-items-center">
                    <div class="col-sm-3 my-1">
                        
                       
                    </div>
                    <div class="col-sm-3 my-1">
                        <div class="input-group">
                            <div class="input-group-prepend">
                             
                            </div>
                            <input class="form-control" type="text" name="Comprobante"  size=50 maxlength=50 value="" id="comprobante" >
                            <div class="col-auto my-1">
                        <input  type="button" id="Buscar"  class="btn btn-primary"  value="Buscar"  >
                    </div>
                        </div>
                    </div>
                    
               
                    
                    
                    
                
                
                </div>
            </form>
        </div>   
        <br>
        
        <div class="container border " style="width: 50%; position: relative;">
             
            
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col" style="font-size: 20px;">No</th>
                        <th scope="col" style="font-size: 20px;">Comprobante</th>
                        <th scope="col" style="font-size: 20px;" >Fecha</th>
                        <th scope="col" style="font-size: 20px;">Tipo</th>
                        <th scope="col" style="font-size: 20px;">Estado</th>
                        <th scope="col" style="font-size: 20px;">Aceptar</th>
                        <th scope="col" style="font-size: 20px;">Rechazar</th>
                    </tr>
                </thead>
                <tbody id="listado"></tbody>
            </table>
    
        
      
        </div>


    <script>
         function loaded(event){	     
document.getElementById("Buscar").addEventListener("click",buscar);
buscar();

 
    }
 

 function buscar(){
   var comprobante=document.getElementById("comprobante").value;
        ajax({type: "GET", 
          url:"api/solicitudes?comprobante="+comprobante,
        }).then(function (Solicitudes){list(Solicitudes);},
                function(status){ alert(errorMessage(status));}); 
  }  
  
    function aceptar(comprobante){
    
        ajax({type: "PUT", 
          url:"api/solicitudes",
          data:comprobante,
          contentType: "application/json"
        }).then(function (Solicitudes){list(Solicitudes);},
                function(status){ alert("no funciona");}); 
  }
  
   function rechazar(comprobante){
        ajax({type: "POST", 
          url:"api/solicitudes",
          data:comprobante,
          contentType: "application/json"
        }).then(function (Solicitudes){list(Solicitudes);},
                function(status){ alert("no funciona");}); 
  } 
   function list(Solicitud){
    var listado=document.getElementById("listado");
    listado.innerHTML="";
    Solicitud.forEach((s)=>{row(listado,s);});	
  } 
     function row(listado,Solicitudes){
	var tr =document.createElement("tr");
	tr.innerHTML="<td>"+"<a href="+"Presentation/Solicitud/show?numero="+Solicitudes.numero+"</a>"+Solicitudes.numero+"</td>"+
				"<td>"+Solicitudes.comprobante+"</td>"+
                                "<td>"+Solicitudes.fecha+"</td>"+
                                "<td>"+Solicitudes.tipo+"</td>"+
                                "<td>"+Solicitudes.estado+"</td>"+
				"<td><img src='https://img.icons8.com/ios/50/000000/checkmark-filled.png' onclick='aceptar(\""+Solicitudes.numero+"\");'></td>"+
                                "<td><img src='https://img.icons8.com/metro/26/000000/delete-sign.png' onclick='rechazar(\""+Solicitudes.numero+"\");'></td>";                                
	listado.appendChild(tr); 
  }
   document.addEventListener("DOMContentLoaded",loaded);
        </script> 

  <%@ include file="/Presentation/footer.jsp" %>
</body>
</html>