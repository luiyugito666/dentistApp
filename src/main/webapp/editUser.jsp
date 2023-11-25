<%@page import="logica.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <%@include file="components/header.jsp"%>
    <%@include file="components/topbody.jsp"%>
 
      <div class="form-group row  justify-content-center alig-items-center text-center">

            <div class="col-sm-6" >
                <h2>EDITAR USUARIO</h2>
                <hr>
            </div>
        </div>
         <% User use= (User) request.getSession().getAttribute("userEdit");%>
    <form class="user" action="SvEditUser" method="POST">
        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6" >
                <input type="text" class="form-control form-control-user" id="name" name="name"
                       placeholder="Nombre Usuario" value="<%=use.getUserName()%>">
            </div>
        </div>
        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6">
                <input type="password" class="form-control form-control-user" id="password" name="password"
                       placeholder="Contraseña" value="<%=use.getPassword()%>">
            </div>
        </div>

        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6" >
                <input type="text" class="form-control form-control-user" id="rol" name="rol"
                       placeholder="Rol" value="<%=use.getRol()%>">
            </div>
        </div>

       
        <!-- Horarios y usuarios -->

        <div class="form-group row  justify-content-center alig-items-center" >
            <div class="col-sm-4" >
                <button  class=" btn btn-primary btn-user btn-block" type="submit">
                    Crear Usuario
                </button>
            </div>
        </div>

    </form>

    <%@include file="components/footbody.jsp"%>

