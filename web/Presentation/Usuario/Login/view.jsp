<%-- 
    Document   : login
    Created on : 15/03/2019, 04:45:32 PM
    Author     : Administrador
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
         
          <%@ include file="/Presentation/head.jsp" %>
        <title></title>
    </head>
    <body>
        <%@ include file="/Presentation/header.jsp" %>
        <% Usuario model = (Usuario) request.getAttribute("model"); %>
        <% Map<String,String> errors = (Map<String,String>) request.getAttribute("errors"); %>
        <% Map<String,String[]> values =(errors== null)? this.getValues(model):request.getParameterMap();%>
        
        <h1 style="text-align:center;">Login</h1>
          <div class="row">
            <div class="col-lg-5"></div>
            <div class="col-lg-5">
         <div class="form-group ">
        <form   method="POST"  action="Presentation/Login/login">
                <div class="input-group">
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-user"></span> 
                    </div>
                    <input class=" form-control <%=validity("username", errors)%> " value="<%=value("username", values)%>" type="text"
                           name="username" size=15 maxlength=40 value="" placeholder="Username"   id="username "style="width: 200px;">
                </div>
            <div class="input-group">
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-lock"></span> 
                </div>
                <input  class="   form-control <%=validity("password", errors)%> wrappler" value="<%=value("password", values)%>" 
                        value="" type="password" 
                        name="password" size=15 maxlength=40 value="" placeholder="Password"  id="password" style="width: 200px;">
            </div>
            <button type="submit" name="submit" class=" btn btn-primary btn-block" style="width: 100px; margin-top: 10px; margin-left: 70px;">Ingresar
        </form>
    </div> 
                          </div><!-- /.col-lg-4 -->
            <div class="col-lg-5"></div>
        </div><!-- /.row -->
                         
                   


  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
                        <%@ include file="/Presentation/footer.jsp" %>
    </body>
</html>


<%!
private String validity(String field, Map<String,String> errors){
      if ( (errors!=null) && (errors.get(field)!=null) )
        return "is-invalid";
      else
        return "";
    }


    private String value(String field, Map<String,String[]> values){
        return values.get(field)[0];
    }

    private Map<String,String[]> getValues(Usuario model){
       Map<String,String[]> values = new HashMap<>();
       values.put("username", new String[]{model.getId()});
       values.put("password", new String[]{model.getClave()});
       return values;
    } 

   %>