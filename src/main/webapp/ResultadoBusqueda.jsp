<%-- 
    Document   : ResultadoBusqueda
    Created on : 18 may. de 2024, 21:49:28
    Author     : nivek
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.documentos.categoria"%>
<%@page import="com.documentos.documento"%>
<%@page import="com.documentos.usuario"%>
<%@page import="com.documentos.detalle_documento"%>

<%
    ArrayList<detalle_documento> lista1= (ArrayList<detalle_documento>)request.getAttribute("lista1");
    usuario user = (usuario) request.getAttribute("user");
    documento documento = (documento) request.getAttribute("documento");
    detalle_documento det_doc = (detalle_documento) request.getAttribute("det_doc");
    categoria cat = (categoria) request.getAttribute("cat");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
         <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="MainServlet">OnlyDocs</a>
                <form class="form-inline my-2 my-lg-0" action="MainServlet?op=buscar" method="post">
                    <input class="form-control mr-sm-2" type="text" name="busqueda" placeholder="Buscar">
                    <select class="form-control mr-sm-2" id="color">
                        <option value="general">Sin filtro</option>
                        <option value="nombre">Nombre</option>
                        <option value="fecha">Fecha</option>
                        <option value="descripcion">Descripcion</option>
                        <option value="usuario">Usuario</option>
                    </select>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
                </form>
                <ul class="navbar-nav ml-auto">
                    <% if (user.getId() != 0) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="MainServlet?action=usr"><%=user.getUsuario()%></a>
                    </li>
                    <% } else { %>
                    <li class="nav-item">
                        <a class="nav-link" href="MainServlet?action=login">Iniciar Sesión</a>
                    </li>
                    <% } %>
                </ul>
            </nav>
        <h1>Resultados de Búsqueda</h1>
         <hr>
            <div class="row">
                <% int c = 0; %>
                <%for (detalle_documento elem : lista1) {

                %>
                <div class="col-md-4 mb-4">
                    <div class="card card-sm">
                        <%if("text/plain".equals(elem.getFormato())){
                            
                        %>
                        <a href="MainServlet?action=descripcionDoc&id_documento=<%=elem.getId_det() %>">

                            <img class="card-img-top" src="https://img.freepik.com/vector-premium/diseno-plano-moderno-icono-archivo-txt-web-estilo-simple_599062-541.jpg" width="250" height="250">
                        </a>
                        <%
                            }else if("application/pdf".equals(elem.getFormato())){


                        %>


                        <a href="MainServlet?action=descripcionDoc&id_documento=<%=elem.getId_det() %>">

                            <img class="card-img-top" src="https://i0.wp.com/academy.leewayweb.com/wp-content/uploads/2017/05/pdf.png?fit=837%2C837&ssl=1" width="250" height="250">
                        </a>
                        <%}else if("application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(elem.getFormato())){
                        %>


                        <a href="MainServlet?action=descripcionDoc&id_documento=<%=elem.getId_det() %>">

                            <img class="card-img-top" src="https://i.pinimg.com/736x/64/f7/56/64f7560835f74f19bb60c5fb582db529.jpg" width="250" height="250">
                        </a>
                        <%}else{
                            

                        %>
                        <a href="MainServlet?action=descripcionDoc&id_documento=<%=elem.getId_det() %>">

                            <img class="card-img-top" src="https://static.vecteezy.com/system/resources/previews/034/998/724/non_2x/corrupted-pixel-file-icon-damage-document-symbol-sign-broken-data-vector.jpg" width="250" height="250">
                        </a>
                        <%
                            }
                        %>
                        <div class="card-body">
                            <h5 class="card-title"><%=elem.getNombre() %></h5>
                            <p class="card-text"><%=elem.getFecha() %></p>
                            <p class="card-text"><%=elem.getDescripcion()%></p>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   
    </body>
</html>
