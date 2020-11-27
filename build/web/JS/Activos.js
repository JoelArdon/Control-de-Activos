/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
             function loaded(event){	
  listarActivo();
 
    }

function listarActivo(){
     ajax({type: "GET", 
          url:"api/activos/listar"                
        }).then(function (Activos){listAct(Activos);},
                function(status){ alert(errorMessage(status));}); 
               
  } 

 function listAct(Activos){
    var listado=document.getElementById("ActivosData");
   listado.innerHTML="";
     for(var i=0; i<Activos.length; i++)
        rowActivo(listado, Activos[i]);
    } 
  function prueba(){
       var listado=document.getElementById("ActivosData");
        var hilera = document.createElement("tr");
        var celda = document.createElement("td");   
        var textoCelda = document.createTextNode("celda en la hilera  columna ");
      celda.appendChild(textoCelda);
      hilera.appendChild(celda);
      listado.appendChild(hilera);
  }
  
  
  function rowActivo(listado,Activos){
    
  	var tr =document.createElement("tr");
	tr.innerHTML="<td>"+Activos.codigo+"</td>"+
				"<td >"+Activos.bien.descripcion+"</td>"+
				"<td >"+Activos.dependencia.nombre+"</td>"+ 
				"<td>"+Activos.categoria.nombre+"</td>"+
				"<td>"+Activos.funcionario+"</td>"+
                                "<td onclick='prueba();'>"+Activos.puesto+"</td>";
                               
	listado.appendChild(tr); 
  }


document.addEventListener("DOMContentLoaded",loaded);

