<%@page import="com.documentos.usuario"%>
<%@page import="com.documentos.detalle_documento"%>
<%@page import="com.documentos.documento"%>
<%
    documento doc = (documento) request.getAttribute("doc");
    detalle_documento det_docu = (detalle_documento) request.getAttribute("det_docu");
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
           
    </table>
        <hr>
    <center> <h1>Descripcion</h1></center>
        <style>
        .container {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }
        .box {
            width: 30%;
            padding: 30px;
            border: 1px solid darkblue;
            border-radius: 15px;
            background-color: #f0f0f0;
        }
        .description {
            width: 55%;
            padding: 30px;
            border: 5px solid black;
            border-radius: 15px;
            background-color: #fff;
        }
    </style> 
    <br>
    <br>
    <div class="container">
        <div class="box">
            <h3>Titulo de la categoria</h3>
            <p>
                Titulo Titulo Titulo Titulo Titulo 
                Titulo Titulo Titulo Titulo Titulo
                Titulo Titulo Titulo Titulo Titulo
                Titulo Titulo Titulo Titulo Titulo
                
            </p>
        </div>
        <div class="description">
            <h3>Descripción de categoria seleccionada</h3>
            <table>
                <p>
                Esta es una descripción de texto simple para mostrar en una página web.
                Puedes agregar aquí todo el texto que desees, formatearlo como prefieras y
                personalizar la página según tus necesidades.
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                </p>
            </table>
            <p>
                Aquí puedes incluir más contenido HTML o código JSP según tus necesidades.
            </p>
        </div>
    </div>
    <br>
    <br>
         <form action="MainServlet?op=descripcionDoc" method="post">
       <div>
           <center>
        <a href="MainServlet?action=editDoc&id="><button>Editar</button></a>
        <a href="MainServlet?action=verDoc&id="><button>Ver</button></a>
        <a href="MainServlet?action=descargar&id="><button>Descargar</button></a>
           </center>
    </div>
         </form>
    </body>
</html>
