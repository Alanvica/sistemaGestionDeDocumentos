<%-- 
    Document   : ResultadoBusqueda
    Created on : 18 may. de 2024, 21:49:28
    Author     : nivek
--%>
<%@page import="com.documentos.documento"%>
<%@page import="com.documentos.usuario"%>
<%@page import="com.documentos.detalle_documento"%>

<%
     usuario user = (usuario) request.getAttribute("user");
       detalle_documento item= (detalle_documento)request.getAttribute("det_doc");
           documento documento = (documento) request.getAttribute("documento");

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
       <p>Los resultados de busqueda son :</p>
       <ul>
           <li>Nombre: <%= item.getNombre()%></li>
            <li>Fecha: <%= item.getFecha()%></li>
            <li>Descripcion: <%= item.getDescripcion()%></li>
            <li>Usuario: <%= item.getId_usr()%></li>
       </ul>
        </form>
       
           
    </body>
</html>
