<%@page import="com.documentos.usuario"%>
<%
  usuario user = (usuario) request.getAttribute("user"); 
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
        <style>
            .description {
            width: 55%;
            padding: 30px;
            border: 5px solid black;
            border-radius: 15px;
            background-color: #fff;
        }
        </style>
        
    <center><h1>DOCUMENTO N</h1></center>
    <center><div class="description">
        <p>
            "Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
            accusantium doloremque laudantium, totam rem aperiam, eaque ipsa 
            quae ab illo inventore veritatis et quasi architecto beatae vitae
            dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
            aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
            qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui 
            dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia
            non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam
            quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem
            ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi
            consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate 
            velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum 
            fugiat quo voluptas nulla pariatur?"
        </p>
        </div>
    </center>
    </body>
</html>
