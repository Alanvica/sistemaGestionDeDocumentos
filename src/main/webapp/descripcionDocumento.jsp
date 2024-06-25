<%@ page import="com.documentos.categoria" %>
<%@ page import="com.documentos.detalle_documento" %>
<%@ page import="com.documentos.usuario" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //documento doc = (documento) request.getAttribute("doc");
    detalle_documento det_docum = (detalle_documento) request.getAttribute("detalle_documento");
    usuario user = (usuario) request.getAttribute("user");
    categoria categoria = (categoria) request.getAttribute("categoria");
    usuario usr = (usuario) request.getAttribute("usuario");
    int c = 0;
%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Descripción Documento</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 2rem; /* Add padding to body to offset content */
        }
        .centered {
            text-align: center;
        }
        .image-thumbnail {
            max-width: 250px;
            max-height: 250px;
        }
        .table-bordered {
            border: 2px solid #dee2e6; /* Bootstrap table border */
        }
    </style>
</head>
<body>
    <div class="container">
        <table class="table">
            <tr>
                <td><a class="navbar-brand" href="MainServlet">OnlyDocs</a></td>
                <form class="form-inline" action="MainServlet?op=buscar" method="post">
                    <td><input class="form-control mr-sm-2" type="text" name="busqueda"></td>
                    <td>
                        <select class="form-control mr-sm-2" id="color">
                            <option value="general">sin filtro</option>
                            <option value="nombre">Nombre</option>
                            <option value="fecha">Fecha</option>
                            <option value="descripcion">Descripción</option>
                            <option value="usuario">Usuario</option>
                        </select>
                    </td>
                    <td><button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button></td>
                </form>
                <% if (user.getId() != 0) { %>
                    <td><a class="nav-link" href="MainServlet?action=usr"><%= user.getUsuario() %></a></td>
                <% } else { %>
                    <td><a class="nav-link" href="MainServlet?action=login">Iniciar Sesión</a></td>
                <% } %>
            </tr>
        </table>

        <hr>
        <center><h1><b>Descripcion del Documento</b></h1></center>
        <br>
        <div class="row">
            <div class="col-md-4">
                <img src="https://cdn.icon-icons.com/icons2/2036/PNG/512/blank_file_page_empty_document_icon_124196.png" class="img-fluid image-thumbnail" alt="PDF Thumbnail">
                <h1><%= det_docum.getNombre() %></h1>
            </div>
            <div class="col-md-8">
                
                <table class="table table-bordered">
                     
                    <tr>
                        <td>Subido por:</td>
                        <td><%= usr.getNombres()%></td>
                    </tr>
                    
                   <tr>
                        <td>Subido el:</td>
                        <td><%= det_docum.getFecha()%></td>
                    </tr>
                    <tr>
                        <td>Descripcion del Documento:</td>
                        <td><%= det_docum.getDescripcion() %></td>
                    </tr>
                    <tr>
                        <td>Nombre de la categoría:</td>
                        <td><%= categoria.getNombre() %></td>
                    </tr>
                    <tr>
                        
                        <td>Descripción de la categoría:</td>
                        <td><%= categoria.getDescripcion() %></td>
                    </tr>
                   
                </table>
                <div class="centered mt-4">
                    <a href="MainServlet?action=editDoc&id=<%= det_docum.getId_det()%>" class="btn btn-primary">Editar</a>
                    <a href="MainServlet?action=verDoc&id_detalle=<%= det_docum.getId_det()%>" class="btn btn-info">Ver</a>
                    <a href="MainServlet?action=descargar&id=<%= det_docum.getId_det()%>" class="btn btn-success">Descargar</a>
                </div>
            </div>
        </div>

        <div class="centered mt-4">
            
            <c:forEach var="item" items="${listaDocCat}">
                <div class="card mb-3" style="max-width: 540px;">
                    <div class="row no-gutters">
                        <div class="col-md-4">
                            <a href="descripcionDocumento.jsp?id=${item.id_det}">
                                <img src="pdf.png" class="card-img image-thumbnail" alt="PDF Thumbnail">
                            </a>
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${item.nombre}</h5>
                                <p class="card-text">Fecha: ${item.fecha}</p>
                                <p class="card-text">Descripción: ${item.descripcion}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </div>

    <!-- Bootstrap JS (Optional) - If you need Bootstrap JavaScript functionalities -->
    <!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
</body>
</html>
