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
        <h1>Nuevo</h1>
        <form action="MainServlet?op=guardar" method="post">
            <input type="hidden" name="id_user" value="<%= user.getApellidos() %>">
            <input type="hidden" name="id_doc" value="">
            
            <input type="submit" value="Guardar">
        </form>
    </body>
</html>
