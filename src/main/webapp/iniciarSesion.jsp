<%-- 
    Document   : iniciarSesion.jsp
    Created on : 13 may. 2024, 1:57:52
    Author     : Villalba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>INICIO DE SESION</h1>
        <form action="MainServlet?op=login" method="post" >
            <table>
                <tr>
                    <td>USUARIO: <input type="text" name="usuario"></td>
                </tr>
                <tr>
                    <td>CONTRASEÑA: <input type="text" name="contrasena"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="INICIAR SESION"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
