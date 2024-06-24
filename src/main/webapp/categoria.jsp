<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.documentos.detalle_documento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Array"%>
<%@page import="com.documentos.categoria"%>
<%@page import="com.documentos.usuario"%>
<%
    usuario user = (usuario) request.getAttribute("user");
    //ArrayList<detalle_documento> listaDocCat = (ArrayList<detalle_documento>) request.getAttribute("listaDocCat");
    int c=0;
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
    <table>
        <tr>
            <%c = 0;%>
            <c:forEach var="item" items="${listaDocCat}">
                <%
                    if (c != 3) {
                %>
                <td>
                    <a href="descripcionDocumento.jsp?id=${item.id_det}"> <img src="pdf.png" width="250" height="250"> </a>
                </td>
                <td>
                    <table><tr><td>${item.nombre}</td></tr><tr><td>${item.fecha}</td></tr><tr><td>${item.descripcion}</td></tr></table>
                </td>
                <%   c++;
                } else {
                    c = 0;
                %>
            <tr>
                <td>
                    <a href="descripcionDocumento.jsp?id=${item.id_det}"> <img src="pdf.png" width="250" height="250"> </a>
                </td>
                <td>
                    <table><tr><td>${item.nombre}</td></tr><tr><td>${item.fecha}</td></tr><tr><td>${item.descripcion}</td></tr></table>
                </td>
                <%
                        c++;
                    }
                %>
            </c:forEach>
        </tr>
    </table>
</body>
</html>
