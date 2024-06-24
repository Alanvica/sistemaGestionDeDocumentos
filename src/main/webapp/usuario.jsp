<%-- 
    Document   : usuario
    Created on : 2 jun. 2024, 22:41:23
    Author     : Villalba
--%>

<%@page import="com.documentos.usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //ArrayList<detalle_documento> lista = (ArrayList<detalle_documento>) request.getAttribute("lista");
    //ArrayList<categoria> lista2 = (ArrayList<categoria>) request.getAttribute("lista2");
    usuario user = (usuario) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <table>
        <tr>
            <td><a href="MainServlet">OnlyDocs</a></td>
        <form action="MainServlet" method="post">
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
        <%
            if (user.getId() != 0) {

        %>
        <td ><a href="MainServlet?action=usr"><%=user.getUsuario()%></a></td>
            <%            } else {
            %>
        <td ><a href="MainServlet?action=login">Iniciar Sesion</a></td>
        <%                }
        %>
    </tr>
</table>
<hr>
<body>
    <h1>DATOS</h1>
    <table>
        <tr>
            <td>Nombres: </td>
            <td><%=user.getNombres()%></td>
        </tr>
        <tr>
            <td>Apellidos: </td>
            <td><%=user.getApellidos()%></td>
        </tr>
        <tr>
            <td>Celular: </td>
            <td><%=user.getTelefono()%></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><%=user.getEmail()%></td>
        </tr>

    </table>
    <form action="MainServlet?op=logout" method="post">
        <input type="submit" value="CERRAR SESION">
    </form>
</body>
</html>
