<%@page import="com.documentos.usuario"%>
<%@page import="com.documentos.categoria"%>
<%@page import="com.documentos.detalle_documento"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.ArrayList"%>
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
    <table>
        <tr>
            <%int c = 0;%>
            <c:forEach var="item" items="${lista2}">
                <%
                    if (c != 11) {
                %>
                <td><a href="categoria.jsp?id=${item.id}"><input type="button" value="${item.nombre}"></a>
                        <%
                            if (user.getId() != 0) {

                        %>
                    <a href="borrarcategoria.jsp?id=${item.id}"><input type="button" value="X"></a>
                        <%            }
                        %>
                </td>
                <%   c++;
                } else {
                    c = 0;
                %>
            </tr>
        </table>
        <table >
            <tr>
                <td><a href="categoria.jsp?id=${item.id}"><input type="button" value="${item.nombre}"></a>
                        <%
                            if (user.getId() != 0) {

                        %>
                    <a href="borrarcategoria.jsp?id=${item.id}"><input type="button" value="X"></a>
                        <%            }
                        %>
                </td>
                <%
                        c++;
                    }
                %>
            </c:forEach>
            <%
                if (user.getId() != 0) {

            %>
            <td><a href="MainServlet?action=categoria"><input type="button" value="+"></a></td>
            <td><a href="MainServlet?action=nuevo"><input type="button" value="▲"></a></td>
                    <%            }
                    %>
        </tr>
    </table>
    <hr>
    <table>
        <tr>
            <%c = 0;%>
            <c:forEach var="item" items="${lista}">
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
