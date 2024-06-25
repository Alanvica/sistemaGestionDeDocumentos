<%@ page import="com.documentos.usuario" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    usuario user= (usuario)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col">
                <a class="navbar-brand" href="MainServlet">OnlyDocs</a>
            </div>
            <div class="col">
                <form class="form-inline" action="MainServlet?op=buscar" method="post">
                    <input class="form-control mr-sm-2" type="text" name="busqueda" placeholder="Buscar">
                    <select class="form-control mr-sm-2" id="color">
                        <option value="general">sin filtro</option>
                        <option value="nombre">Nombre</option>
                        <option value="fecha">Fecha</option>
                        <option value="descripcion">Descripcion</option>
                        <option value="usuario">Usuario</option>
                    </select>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
                </form>
            </div>
            <div class="col">
                <% if (user.getId() != 0) { %>
                    <a class="nav-link" href="MainServlet?action=usr"><%= user.getUsuario() %></a>
                <% } else { %>
                    <a class="nav-link" href="MainServlet?action=login">Iniciar Sesión</a>
                <% } %>
            </div>
        </div>
    </div>
    <hr>
    <div class="container">
        <h1>DATOS</h1>
        <table class="table">
            <tbody>
                <tr>
                    <td>Nombres:</td>
                    <td><%= user.getNombres() %></td>
                </tr>
                <tr>
                    <td>Apellidos:</td>
                    <td><%= user.getApellidos() %></td>
                </tr>
                <tr>
                    <td>Celular:</td>
                    <td><%= user.getTelefono() %></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><%= user.getEmail() %></td>
                </tr>
            </tbody>
        </table>
        <form action="MainServlet?op=logout" method="post">
            <button class="btn btn-danger" type="submit">CERRAR SESIÓN</button>
        </form>
    </div>
    <!-- Bootstrap JS (Optional) - If you need Bootstrap JavaScript functionalities -->
    <!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
</body>
</html>
