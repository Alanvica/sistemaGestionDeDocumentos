<%@page import="com.documentos.categoria"%>
<%@page import="com.documentos.usuario"%>
<%
    usuario user = (usuario) request.getAttribute("user");
    categoria cate = (categoria) request.getAttribute("cate");
    if (cate == null) {
    out.println("Error: El objeto 'cate' es nulo.");
} else {
    out.println("Objeto 'cate' encontrado: " + cate.toString());
}
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td><a href="MainServlet">OnlyDocs</a></td>
            <form action="MainServlet?buscar" method="post">
                <td><input type="text" name="busqueda"></td>
                <td>
                    <select id="color">
                        <option value="general">sin filtro</option>
                        <option value="nombre">Nombre</option>
                        <option value="fecha">Fecha</option>
                        <option value="descripcion">Descripcion</option>
                        <option value="usuario">Usuario</option>
                    </select> </td>
                </td>
                <td><input type="submit" value="Buscar"></td>
            </form>
        </tr>
    </table>
    <style>
        .description {
            width: 55%;
            padding: 30px;
            border: 5px solid black;
            border-radius: 15px;
            background-color: #fff;
        }
    </style>

<center><h1>CATEGORIA SELECCIONADA</h1></center>
<div class="description">
    <form action="MainServlet?op=descripcion" method="post" >
        <table>  
            <input type="hidden" name="id" value="<%=cate.getId() %>">
            <tr>
                <td>Nombre: </td>
                <td><input type="text" name="nombre" value="<%=cate.getNombre()%>"></td>
            </tr>
            <tr>
                <td>Descripcion: </td>
                <td><input type="text" name="descripcion" value="<%=cate.getDescripcion()%>"></td>
            </tr>

    </form>
</div>
</body>
</html>
