function loaded(event){	

  
    listarSolicitudes();
    console.log("wtf");
  }
  
  function enviar(){
      console.log("prueba");
  }
  
  function list(solicitudes){
    var listado=document.getElementById("solicitudesData");
    listado.innerHTML="";
    for(var i=0; i<solicitudes.length; i++)
        row(listado, solicitudes[i]);
    } 
    
    function listarSolicitudes(){
    var xhtt = new XMLHttpRequest();
    
    xhtt.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
             console.log("wtf");    
           var res= JSON.parse(xhtt.responseText);
           list(res);
           console.log(res);
          
        }
    };
   
    xhtt.open("GET", "api/dependencias/listar", true);
    xhtt.send();
}
  function row(listado, solicitud){
	var tr =document.createElement("tr");
	tr.innerHTML="<td>"+solicitud.numero+"</td>"+
				"<td>"+solicitud.comprobante+"</td>"+
				"<td>"+solicitud.cantidad+"</td>"+
                                "<td>"+solicitud.fecha+"</td>"+
                                "<td>"+solicitud.tipo+"</td>"+
                                "<td> "+solicitud.total+"</td>"+
                                "<td>"+"<select>"+
                                "<option value="+"recibida>"+solicitud.estado+"</option>"+
                                "<option value="+"porverficiar>"+"Por verificar"+"</option>"+
                                "<option value="+"Rechazada>"+"Rechazada"+"</option>"
                        
                                +"</select>"+"</td>"+
                                "<td>"+solicitud.dependencia.nombre+"</td>";
				
	listado.appendChild(tr); 
  }

document.addEventListener("DOMContentLoaded",loaded);

  