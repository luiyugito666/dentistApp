<%@page import="logica.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<%@include file="components/header.jsp"%>
<%@include file="components/topbody.jsp"%>

   
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Lista de Usuarios</h1>
                    <p class="mb-4">usuarios </p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Usuarios</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>Usuario</th>
                                            <th>Rol</th>
                                            <th style="width:210px" >Acción</th>
                                         
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>id</th>
                                            <th>Usuario</th>
                                            <th>Rol</th>
                                             <th style="width:210px" >Acción</th>
                                           
                                        </tr>
                                    </tfoot>
                                    <% List<User> listUser=
                                            (List) request.getSession().getAttribute("listUser"); %>
                                    
                                    <tbody>
                                        
                                        
                                        <%for(User use:listUser){ %>
                                        <tr>
                                            <td id="id_usu<%=use.getIdUser() %>"><%=use.getIdUser() %></td>
                                            <td><%=use.getUserName() %></td>
                                            <td><%=use.getRol() %></td>
                                            <td style="display:flex; width:230px;">
                                                
                                                <form name="delete" action="SvDelUser" method="POST">
                                                     <input type="hidden" name="id" value="<%=use.getIdUser() %>">     </input>   
                                                    <button type="submit" class="btn btn-primary btn-user btn-block" 
                                                            style="background: red;margin-right:5px;">
                                                        <i class="fas fa-trash-alt"></i> Eliminar
                                                     </button>  
                                                    
                                                  
                                                </form>
                                                        <form name="edit" action="SvEditUser" method="GET">
                                                     <input type="hidden" name="id" value="<%=use.getIdUser() %>">
                                                    
                                                        
                                                    </input>          
                                                    <button type="submit" class="btn btn-primary btn-user btn-block" 
                                                            style="margin-left:5px;">
                                                        <i class="fas fa-pencil-alt"></i> Ediar
                                                     </button>  
                                                    
                                                    
                                                    
                                                </form>
                                            
                                            
                                            
                                            
                                            
                                            </td>
                                          
                                        </tr>
                                 
                                        
                                        <%}%>
                                 
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

     



<%@include file="components/footbody.jsp"%>

