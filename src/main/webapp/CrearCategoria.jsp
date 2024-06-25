<%@ page import="com.documentos.usuario" %>
<%@ page import="com.documentos.categoria" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    usuario user = (usuario) request.getAttribute("user");
    categoria cat = (categoria) request.getAttribute("cat");
     
%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Nueva Categoria</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 2rem; /* Add padding to body to offset content */
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
                            <option value="descripcion">Descripcion</option>
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

        <h1 class="mt-5"><b>Nueva Categoria</b></h1>
        <h2>DATOS:</h2>
        <form action="MainServlet?op=agregarC" method="post" class="mt-3">
            <div class="form-group">
                <input type="hidden" name="id" value="<%= cat.getId() %>">
            </div>
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= cat.getNombre() %>" required>
            </div>
            <div class="form-group">
                <label for="descripcion">Descripción:</label>
                <input type="text" class="form-control" id="descripcion" name="descripcion" value="<%= cat.getDescripcion() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Guardar</button>
        </form>
    </div>

    <!-- Bootstrap JS (Optional) - If you need Bootstrap JavaScript functionalities -->
    <!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
</body>
</html>
