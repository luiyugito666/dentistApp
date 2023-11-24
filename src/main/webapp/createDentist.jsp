<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <%@include file="components/header.jsp"%>
    <%@include file="components/topbody.jsp"%>
    
      <div class="form-group row  justify-content-center alig-items-center text-center">

            <div class="col-sm-6" >
                <h2>REGISTRAR ODONOTOLOGO</h2>
                <hr>
            </div>
        </div>
    
    <form class="user">
        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6" >
                <input type="text" class="form-control form-control-user" id="dni"
                       placeholder="DNI">
            </div>
        </div>
        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6">
                <input type="text" class="form-control form-control-user" id="name"
                       placeholder="Nombre">
            </div>
        </div>

        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6" >
                <input type="text" class="form-control form-control-user" id="lastName"
                       placeholder="Apellidos">
            </div>
        </div>

        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6" >
                <input type="text" class="form-control form-control-user" id="phone"
                       placeholder="Telefono">
            </div>
        </div>
        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6">
                <input type="text" class="form-control form-control-user" id="address"
                       placeholder="Direccion">
            </div>
        </div>

        <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6" >
                <input type="text" class="form-control form-control-user" id="birthday"
                       placeholder="Fecha de Nacimiento">
            </div>
        </div>
       
          <div class="form-group row  justify-content-center alig-items-center">

            <div class="col-sm-6" >
                <input type="text" class="form-control form-control-user" id="specialty"
                       placeholder="Especialidad">
            </div>
        </div>
        
        <!-- Horarios y usuarios -->

        <div class="form-group row  justify-content-center alig-items-center">
            <div class="col-sm-6" >
                <a href="login.html" class=" btn btn-primary btn-user btn-block justify-content-center alig-items-center">
                    Registrar Cuenta
                </a>
            </div>
        </div>

    </form>

    <%@include file="components/footbody.jsp"%>

