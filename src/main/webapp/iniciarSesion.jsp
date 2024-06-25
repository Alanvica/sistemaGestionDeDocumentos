<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 2rem; /* Add padding to body to offset content */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="mt-5">INICIO DE SESIÓN</h1>
        <form action="MainServlet?op=login" method="post" class="mt-3">
            <div class="form-group">
                <label for="usuario">USUARIO:</label>
                <input type="text" class="form-control" id="usuario" name="usuario" required>
            </div>
            <div class="form-group">
                <label for="contrasena">CONTRASEÑA:</label>
                <input type="password" class="form-control" id="contrasena" name="contrasena" required>
            </div>
            <button type="submit" class="btn btn-primary">INICIAR SESIÓN</button>
        </form>
    </div>

    <!-- Bootstrap JS (Optional) - If you need Bootstrap JavaScript functionalities -->
    <!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
</body>
</html>
