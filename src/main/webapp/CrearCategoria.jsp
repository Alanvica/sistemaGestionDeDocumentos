<%-- 
    Document   : CrearCategoria
    Created on : 21 may. de 2024, 22:20:28
    Author     : nivek
--%>
<%@page import="com.documentos.categoria"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    categoria cat = (categoria) request.getAttribute("categoria");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
    <center>
        <h1><b>Nueva Categoria</b></h1>
        <h2>DATOS:</h2>
        <form action="MainServlet" method="post">
            <label style="color: red; font-size: 20px;"><b>Id_usr:</b></label>
            <input type="text" name="id_usr">
            <br>
            <label style="color: red;font-size:  20px;"><b>Nombre:</b></label>
            <input type="text" name="nombre">
            <br>
            <label style="color: red;font-size:  20px;"><b>Descripcion:</b></label>
            <input class="text" name="descripcion"> 
            <br>
            <br>
           <input type="submit" value="Guardar">
           
        </form>
      </center>
    </body>
</html>
