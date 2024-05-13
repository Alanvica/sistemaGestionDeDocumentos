<%@page import="com.documentos.detalle_documento"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<detalle_documento> lista = (ArrayList<detalle_documento>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td><a href="inicio.jsp">OnlyDocs</a></td>
            <form action="MainServlet">
                <td><input type="text" name="busqueda"></td>
                <td>
                    <select id="color">
                        <option value="general">filtro</option>
                        <option value="fecha">fecha</option>
                    </select> </td>
                </td>
                <td><input type="submit" value="Buscar"></td>
            </form>
            <td id="user" ><a href="url">iniciar sesion</a></td>
            </tr>
        </table>
        <hr>
        <table>
            <tr>
                <th><a href="categoria.jsp"><input type="button" value="categoria 1"></a><a href="borrarcategoria.jsp"><input type="button" value="X"></a></th>
                <th><a href="CrearCategoria.jsp"><input type="button" value="+"></a></th>
                <th><a href="SubirDocumento.jsp"><input type="button" value="▲"></a></th>
            </tr>
        </table>
        <c:forEach var="item" items="${lista}">
        <table>
            <tr>
                <td>
                    <a href="descripcionDocumento.jsp"> <img src="pdf.png" width="32" height="32"> </a>
                </td>
                <td>
                    <table><tr><td>${item.titulo}</td></tr><tr><td>${item.fecha}</td></tr><tr><td>${item.descripcion}</td></tr></table>
                </td>
            </tr>
        </table>
        </c:forEach>
    </body>
</html>
