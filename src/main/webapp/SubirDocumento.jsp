<%@page import="java.util.ArrayList"%>
<%@page import="com.documentos.categoria"%>
<%@page import="java.util.List"%>
<%@page import="com.documentos.usuario"%>
<%@page import="com.documentos.detalle_documento"%>
<%@page import="com.documentos.documento"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<categoria> categorias = (ArrayList<categoria>) request.getAttribute("listaCat");
    usuario user = (usuario) request.getAttribute("user");
    documento documento = (documento) request.getAttribute("documento");
    detalle_documento det_doc = (detalle_documento) request.getAttribute("det_doc");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->
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
                        <option value="descripcion">Descripción</option>
                        <option value="usuario">Usuario</option>
                    </select>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
                </form>
                <ul class="navbar-nav ml-auto">
                    <% if (user.getId() != 0) { %>
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
            <hr>
            <% if (det_doc.getId_det() != 0) { %>
                <h1>Editar documento</h1>
            <% } else { %>
                <h1>Nuevo documento</h1>
            <% } %>
            <form action="MainServlet?op=guardar" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="hidden" name="id_det" value="<%=det_doc.getId_det()%>">
                    <input type="hidden" name="id_doc" value="<%=documento.getId()%>">
                    <input type="hidden" name="contenido" value="<%=det_doc.getArchivo()%>">
                </div>
                <div class="form-group">
                    <label for="titulo">Título:</label>
                    <input type="text" class="form-control" id="titulo" name="titulo" value="<%=det_doc.getNombre()%>">
                </div>
                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" class="form-control" id="fecha" name="fecha" value="<%=det_doc.getFecha()%>">
                </div>
                <% if (det_doc.getId_det() != 0) { %>
                    <div class="form-group">
                        <label>Archivo Actual:</label>
                        <iframe id="pdfViewer" class="d-block mb-2" width="180" height="200" frameborder="0"></iframe>
                        <div class="form-check">
                            <p>¿cambiar archivo?</p>
                            <input type="file" class="form-control-file" name="archivo" accept=".txt, .pdf, .doc, .docx">
                        </div>
                    </div>
                <% } else { %>
                    <div class="form-group">
                        <label for="archivo">Archivo:</label>
                        <input type="file" class="form-control-file" id="archivo" name="archivo" accept=".txt, .pdf, .doc, .docx">
                    </div>
                <% } %>
                <input type="hidden" name="formato" value="<%=det_doc.getFormato()%>">
                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" value="<%=det_doc.getDescripcion()%>">
                </div>
                <div class="form-group">
                    <label for="categoria">Categoría:</label>
                    <select class="form-control" id="categoria" name="categoria_id">
                        <% for (categoria cat : categorias) { %>
                            <option value="<%=cat.getId()%>" <%= (cat.getId() == det_doc.getId_cat()) ? "selected" : "" %>>
                                <%=cat.getNombre()%>
                            </option>
                        <% } %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
            <script>
                // Cadena base64 del archivo PDF
                var base64PDF = "<%=det_doc.getArchivo()%>"; // Reemplaza base64String con la cadena base64 del archivo PDF

                // Construir el objeto de datos URI
                var pdfDataUri = "data:<%=det_doc.getFormato()%>;base64," + base64PDF;

                // Asignar el src del iframe al objeto de datos URI
                document.getElementById('pdfViewer').setAttribute('src', pdfDataUri);

                console.log(pdfDataUri);
            </script>
        </div>
        <!-- Bootstrap JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
