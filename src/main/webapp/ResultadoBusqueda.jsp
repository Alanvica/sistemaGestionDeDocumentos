<%-- 
    Document   : ResultadoBusqueda
    Created on : 18 may. de 2024, 21:49:28
    Author     : nivek
--%>
<%@page import="com.documentos.categoria"%>
<%@page import="com.documentos.documento"%>
<%@page import="com.documentos.usuario"%>
<%@page import="com.documentos.detalle_documento"%>

<%
   usuario user = (usuario) request.getAttribute("user");
    documento documento = (documento) request.getAttribute("documento");
    detalle_documento det_doc = (detalle_documento) request.getAttribute("det_doc");
    categoria cat=(categoria) request.getAttribute("cat");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>Resultados de Búsqueda</h1>
        <form action="MainServlet?op=buscar" method="post" >
            <table>
                <input type="hidden" name="id_det" value="<%=det_doc.getId_det()%>">         
                
                <tr>
                <td>Sin filtro </td>
                <td><input type="text" name="sin filtro" value="<%=%>"></td>
            </tr>
            <tr>
                <td>Nombre </td>
                <td><input type="text" name="nombre" value="<%=det_doc.getNombre()%>"></td>
            </tr>
            <tr>
                <td>Fecha:</td>
                <td><input type="date" name="fecha" value="<%=det_doc.getFecha()%>"></td>
            </tr>
            <input type="hidden" name="formato" value="<%=det_doc.getFormato()%>">
            <tr>
                <td>Descripcion: </td>
                <td><input type="text" name="descripcion" value="<%=det_doc.getDescripcion()%>"></td>
            </tr>
            </table>

        </form>
       
           
    </body>
</html>
