<%-- 
    Document   : SubirDocumento
    Created on : 19 may. 2024, 21:33:08
    Author     : Villalba
--%>

<%@page import="com.documentos.usuario"%>
<%@page import="com.documentos.detalle_documento"%>
<%@page import="com.documentos.documento"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    usuario user = (usuario) request.getAttribute("user");
    documento documento = (documento) request.getAttribute("documento");
    detalle_documento det_doc = (detalle_documento) request.getAttribute("det_doc");
    System.out.println(user.getApellidos());
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
            <td ><a href="MainServlet?action=usuario"><%=user.getUsuario()%></a></td>
                <%            } else {
                %>
            <td ><a href="MainServlet?action=login">Iniciar Sesion</a></td>
            <%                }
            %>
        </tr>
    </table>
    <hr>
    <h1>Nuevo</h1>
    <form action="MainServlet?op=guardar" method="post" enctype="multipart/form-data">
    
        <table>
        <input type="hidden" name="id_det" value="<%=det_doc.getId_det()%>">         
        <input type="hidden" name="id_cat" value="<%=det_doc.getId_cat()%>">
            <tr>
                <td>Titulo: </td>
                <td><input type="text" name="titulo" value="<%=det_doc.getNombre()%>"></td>
            </tr>
            <tr>
                <td>fecha: </td>
                <td><input type="date" name="fecha" value="<%=det_doc.getFecha()%>"></td>
            </tr>
            <tr>
                <td>Archivo:</td>
                <td><input type="file" name="archivo" accept=".txt, .pdf, .doc, .docx"></td>
            </tr>
            <input type="hidden" name="formato" value="<%=det_doc.getFormato()%>">
            <tr>
                <td>Descripcion: </td>
                <td><input type="text" name="descripcion" value="<%=det_doc.getDescripcion()%>"></td>
            </tr>
            <tr>
                <td>Categoria: </td>
                <td>
                    <select name="categoria">
                        <option value="1">defecto</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Guardar">
    </form>
</body>
</html>
