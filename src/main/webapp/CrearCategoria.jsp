 <%-- 
    Document   : CrearCategoria
    Created on : 21 may. de 2024, 22:20:28
    Author     : nivek
--%>
<%@page import="com.documentos.usuario"%>
<%@page import="com.documentos.categoria"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    usuario user = (usuario) request.getAttribute("user");
    categoria cat = (categoria) request.getAttribute("cat");
     
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
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
            <td ><a href="MainServlet?action=usuario"><%=user.getUsuario()%></a></td>
                <%            } else {
                %>
            <td ><a href="MainServlet?action=login">Iniciar Sesion</a></td>
            <%                }
            %>
         </tr>
         </table>
         
         <hr>
        <h1><b>Nueva Categoria</b></h1>
        <h2>DATOS:</h2>
         <form action="MainServlet?op=agregarC" method="post">
          <table>  
              <input type="hidden" name="id" value="<%=cat.getId()%>">
            <tr>
                <td>Nombre: </td>
                <td><input type="text" name="nombre" value="<%=cat.getNombre()%>"></td>
            </tr>
            <tr>
                <td>Descripcion: </td>
                <td><input type="text" name="descripcion" value="<%=cat.getDescripcion()%>"></td>
            </tr>
          </table>
            <br>
            <br>
           <input type="submit" value="Guardar">
           
        </form>

    </body>
</html>
