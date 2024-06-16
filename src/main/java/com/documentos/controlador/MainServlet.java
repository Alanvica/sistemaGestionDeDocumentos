package com.documentos.controlador;

import com.documentos.documento;
import com.documentos.categoria;
import com.documentos.detalle_documento;
import com.documentos.usuario;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
        location = "C:\\Users\\Villalba\\Desktop",
        fileSizeThreshold = 1024 * 1024 * 10, //1MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 11 //11MB
)

/**
 *
 * @author Villalba
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db_sis_gestion_documento";
    String usuario = "root";
    String password = "";
    Connection conn = null;
    String sql = "";
    PreparedStatement ps = null;
    ResultSet rs = null;
    usuario user = new usuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "view";

        ArrayList<detalle_documento> lista = new ArrayList<detalle_documento>();
        ArrayList<categoria> lista2 = new ArrayList<categoria>();
        switch (action) {
            case "view":
                if (user.getId() != 0) {
                    try {
                        Class.forName(driver);

                        conn = DriverManager.getConnection(url, usuario, password);

                        sql = "select t1.id_detalle as id, t1.nombre as titulo, DATE_FORMAT(t1.fecha, '%Y-%m-%d') as fecha, t1.archivo as arch, t1.id_categoria as id_cat ,t1.descripcion as descripcion from detalle_documento as t1, documento as t2 where t1.id_detalle=t2.id_detalle";

                        ps = conn.prepareStatement(sql);

                        rs = ps.executeQuery();

                        while (rs.next()) {
                            System.out.println(rs.getString("titulo"));
                            detalle_documento doc = new detalle_documento();
                            doc.setId_det(rs.getInt("id"));
                            doc.setNombre(rs.getString("titulo"));
                            doc.setFecha(rs.getString("fecha"));
                            doc.setArchivo(rs.getString("arch"));
                            doc.setId_cat(rs.getInt("id_cat"));
                            doc.setDescripcion(rs.getString("descripcion"));
                            lista.add(doc);
                            //System.out.println("qwerty");
                            System.out.println("cate: " + lista);
                        }

                        sql = "select * from categoria";
                        ps = conn.prepareStatement(sql);

                        rs = ps.executeQuery();

                        while (rs.next()) {
                            //System.out.println(rs.getString("nombre"));
                            categoria cat = new categoria();
                            cat.setId(rs.getInt("id_categoria"));
                            cat.setId_usr(rs.getInt("id_usuario"));
                            cat.setNombre(rs.getString("nombre"));
                            cat.setDescripcion(rs.getString("descripcion"));
                            lista2.add(cat);
                        }
                        request.setAttribute("user", user);
                        request.setAttribute("lista2", lista2);
                        request.setAttribute("lista", lista);
                        request.getRequestDispatcher("inicio.jsp").forward(request, response);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error en driver " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Error al conectar " + e.getMessage());
                    }

                    break;
                } else {
                    try {
                        Class.forName(driver);

                        conn = DriverManager.getConnection(url, usuario, password);

                        sql = "select t1.id_detalle as id, t1.nombre as titulo, DATE_FORMAT(t1.fecha, '%Y-%m-%d') as fecha, t1.archivo as arch, t1.id_categoria as id_cat ,t1.descripcion as descripcion from detalle_documento as t1, documento as t2 where t1.id_detalle=t2.id_detalle and t1.id_categoria=1";

                        ps = conn.prepareStatement(sql);

                        rs = ps.executeQuery();

                        while (rs.next()) {
                            System.out.println(rs.getString("titulo"));
                            detalle_documento doc = new detalle_documento();
                            doc.setId_det(rs.getInt("id"));
                            doc.setNombre(rs.getString("titulo"));
                            doc.setFecha(rs.getString("fecha"));
                            doc.setArchivo(rs.getString("arch"));
                            doc.setId_cat(rs.getInt("id_cat"));
                            doc.setDescripcion(rs.getString("descripcion"));
                            lista.add(doc);
                            //System.out.println("qwerty");
                        }

                        sql = "select * from categoria where id_categoria=1";
                        ps = conn.prepareStatement(sql);

                        rs = ps.executeQuery();

                        while (rs.next()) {
                            System.out.println(rs.getString("nombre"));
                            categoria cat = new categoria();
                            cat.setId(rs.getInt("id_categoria"));
                            cat.setId_usr(rs.getInt("id_usuario"));
                            cat.setNombre(rs.getString("nombre"));
                            cat.setDescripcion(rs.getString("descripcion"));
                            lista2.add(cat);
                        }
                        request.setAttribute("user", user);
                        request.setAttribute("lista2", lista2);
                        request.setAttribute("lista", lista);
                        request.getRequestDispatcher("inicio.jsp").forward(request, response);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error en driver " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Error al conectar " + e.getMessage());
                    }

                    break;
                }

            case "login":
                request.getRequestDispatcher("iniciarSesion.jsp").forward(request, response);
                break;
            case "nuevo":
                List<categoria> listaCat2 = new ArrayList<categoria>();
                try {

                    sql = "Select * from categoria";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        categoria cate_ = new categoria();
                        int id_categoria = rs.getInt("id_categoria");
                        int id_usuario = rs.getInt("id_usuario");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        cate_.setNombre(nombre);
                        cate_.setDescripcion(descripcion);
                        cate_.setId(id_categoria);
                        cate_.setId_usr(id_usuario);
                        listaCat2.add(cate_);
                    }
                } catch (Exception e) {
                }

                documento documento = new documento();
                detalle_documento det_doc = new detalle_documento();
                request.setAttribute("user", user);
                request.setAttribute("listaCat", listaCat2);
                request.setAttribute("det_doc", det_doc);
                request.setAttribute("documento", documento);
                System.out.println("det " + det_doc.getId_det());
                System.out.println("cat " + det_doc.getId_cat());
                System.out.println("doc " + documento.getId());
                request.getRequestDispatcher("SubirDocumento.jsp").forward(request, response);
                break;
            case "desc":
                documento doc = new documento();
                detalle_documento det_d = new detalle_documento();
                request.setAttribute("user", user);
                request.setAttribute("det_doc", det_d);
                request.setAttribute("documento", doc);
                request.getRequestDispatcher("categoria.jsp").forward(request, response);
                break;

            case "buscar":
                documento D = new documento();
                detalle_documento d_t = new detalle_documento();
                request.setAttribute("user", user);
                request.setAttribute("det_doc", D);
                request.setAttribute("documento", d_t);
                System.out.println("det " + D.getId_det());
                System.out.println("cat " + d_t.getId_cat());

                request.getRequestDispatcher("ResultadoBusquedajsp").forward(request, response);
                break;
            case "categoria":
                categoria cat = new categoria();
                System.out.println(user.getId() + "concha");
                request.setAttribute("user", user);
                request.setAttribute("cat", cat);
                request.getRequestDispatcher("CrearCategoria.jsp").forward(request, response);
                break;

            case "usr":
                request.setAttribute("user", user);
                request.getRequestDispatcher("usuario.jsp").forward(request, response);
                break;
            case "borrarCat":
                try {
                int id_cat = Integer.parseInt(request.getParameter("id"));
                sql = "DELETE FROM CATEGORIA WHERE id_categoria = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id_cat);
                ps.executeUpdate();
                response.sendRedirect(request.getContextPath() + "/MainServlet");
            } catch (SQLException ex) {
                System.out.println("LLave " + ex.getMessage());
                response.sendRedirect(request.getContextPath() + "/MainServlet");
            }
            break;
            case "editDoc":
                try {
                List<categoria> listaCat = new ArrayList<categoria>();

                documento doc_ = new documento();
                detalle_documento det_docu = new detalle_documento();
                int id_det = Integer.parseInt(request.getParameter("id"));
                sql = "Select * from documento where id_detalle = ? limit 1";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id_det);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id_documento = rs.getInt("id_documento");
                    int id_detalle = rs.getInt("id_detalle");
                    doc_.setId(id_documento);
                    doc_.setId_det(id_detalle);
                }
                sql = "Select * from detalle_documento where id_detalle = ? limit 1";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id_det);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id_detalle = rs.getInt("id_detalle");
                    int id_categoria = rs.getInt("id_categoria");
                    String nombre = rs.getString("nombre");
                    String fecha = rs.getString("fecha");
                    String archivo = rs.getString("archivo");
                    String formato = rs.getString("formato");
                    String descripcion = rs.getString("descripcion");
                    det_docu.setId_det(id_detalle);
                    det_docu.setNombre(nombre);
                    det_docu.setFecha(fecha);
                    det_docu.setArchivo(archivo);
                    det_docu.setFormato(formato);
                    det_docu.setDescripcion(descripcion);
                    det_docu.setId_cat(id_categoria);
                }
                sql = "Select * from categoria";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    categoria cate_ = new categoria();
                    int id_categoria = rs.getInt("id_categoria");
                    int id_usuario = rs.getInt("id_usuario");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    cate_.setNombre(nombre);
                    cate_.setDescripcion(descripcion);
                    cate_.setId(id_categoria);
                    cate_.setId_usr(id_usuario);
                    listaCat.add(cate_);
                }
                request.setAttribute("user", user);
                request.setAttribute("listaCat", listaCat);
                request.setAttribute("det_doc", det_docu);
                request.setAttribute("documento", doc_);
                System.out.println("det " + det_docu.getId_det());
                System.out.println("cat " + det_docu.getId_cat());
                System.out.println("doc " + doc_.getId());
                request.getRequestDispatcher("SubirDocumento.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("msg " + e.getMessage());

            }

            break;
            default:
                throw new AssertionError();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
        String busqueda = request.getParameter("busqueda");
        String filtro = request.getParameter("filtro");
        request.setAttribute("resultado", "Resultado de la búsqueda con filtro " + filtro + ": " + busqueda);

        PreparedStatement ps = null;
        ResultSet rs = null;
        switch (op) {
            case "login":
                try {
                String usuario1 = request.getParameter("usuario");
                String contrasena = request.getParameter("contrasena");
                sql = "select * from usuario where usuario=? and contrasena=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, usuario1);
                ps.setString(2, contrasena);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //agregar la al user en la variable sesion

                    int id_usr = rs.getInt("id_usuario");
                    String nombres_usr = rs.getString("nombres");
                    String apellidos_usr = rs.getString("apellidos");
                    Number telefono_usr = rs.getInt("telefono");
                    String email_usr = rs.getString("email");
                    String direccion_usr = rs.getString("direccion");
                    String usuario_usr = rs.getString("usuario");
                    String contraseña_usr = rs.getString("contrasena");

                    user.setId(id_usr);
                    user.setNombres(nombres_usr);
                    user.setApellidos(apellidos_usr);
                    user.setTelefono(telefono_usr);
                    user.setEmail(email_usr);
                    user.setDireccion(direccion_usr);
                    user.setUsuario(usuario_usr);
                    user.setContrasena(contraseña_usr);
                    System.out.println(user.getEmail());
                    //request.getRequestDispatcher("MainServlet?action=view").forward(request, response);
                    response.sendRedirect("MainServlet?action=view");
                    break;
                } else {
                    response.sendRedirect("MainServlet");
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Error al conectar " + e.getMessage());
            }
            break;
            case "guardar":
                try {
                int id_det = Integer.parseInt(request.getParameter("id_det"));
                int id_cat = Integer.parseInt(request.getParameter("categoria_id"));
                System.out.println("id_det " + id_det);
                System.out.println("id_cat " + id_cat);
                String titulo = request.getParameter("titulo");
                String fecha = request.getParameter("fecha");
                String contenido = "vacio";
                Part archivo = request.getPart("archivo");
                contenido = convertFileToBase64String(archivo);
                String formato = request.getParameter("formato");
                String descripcion = request.getParameter("descripcion");
                int id_cate = Integer.parseInt(request.getParameter("categoria_id"));
                if (id_det == 0) {
                    formato = archivo.getContentType();
                    System.out.println("formato: " + formato);
                    sql = "INSERT INTO DETALLE_DOCUMENTO (nombre, fecha, archivo, formato, id_categoria, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, titulo);
                    ps.setString(2, fecha);
                    ps.setString(3, contenido);
                    ps.setString(4, formato);
                    ps.setInt(5, id_cate);
                    ps.setString(6, descripcion);
                    ps.executeUpdate();
                    sql = "SELECT id_detalle FROM DETALLE_DOCUMENTO ORDER BY id_detalle DESC LIMIT 1";
                    rs = ps.executeQuery(sql);
                    if (rs.next()) {
                        id_det = rs.getInt("id_detalle");
                        sql = "INSERT INTO DOCUMENTO(id_detalle) VALUES(?)";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, id_det);
                        ps.executeUpdate();
                    }

                } else {
                    
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            response.sendRedirect("MainServlet?action=view");
            break;
            case "logout":
                System.out.println("Cerrar");
                usuario vacio = new usuario();
                user = vacio;
                response.sendRedirect("MainServlet?action=view");
                break;
            case "buscar":
                try {
                int id_usr = user.getId();
                int id = Integer.parseInt(request.getParameter("id"));
                int id_det = Integer.parseInt(request.getParameter("id_det"));
                int id_cat = Integer.parseInt(request.getParameter("id_cat"));
                String seleccionefiltro = request.getParameter("sin filtro");
                String nombre = request.getParameter("nombre");
                String fecha = request.getParameter("fecha");
                String descripcion = request.getParameter("descripcion");
                String usuario_usr = rs.getString("usuario");
                if (id == 0) {
                    sql = sql = "SELECT t1.id_detalle AS id, t1.nombre AS titulo, DATE_FORMAT(t1.fecha, '%Y-%m-%d') AS fecha, t1.archivo AS arch, t1.id_categoria AS id_cat, t1.descripcion AS descripcion FROM detalle_documento AS t1 JOIN detalle_documento AS t2 ON t1.id_detalle = t2.id_detalle WHERE t2.nombre LIKE '%Documento 1%'";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, id_usr);
                    ps.setString(2, nombre);
                    ps.setString(3, descripcion);
                    ps.executeUpdate();
                    sql = "SELECT id_detalle FROM DETALLE_DOCUMENTO ORDER BY id_detalle DESC LIMIT 1";
                    rs = ps.executeQuery(sql);
                    if (rs.next()) {
                        id_det = rs.getInt("id_detalle");
                        sql = "INSERT INTO DOCUMENTO(id_detalle) VALUES(?)";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, id_det);
                        ps.executeUpdate();
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception es) {
                System.out.println("error: " + es.getMessage());
            }
            response.sendRedirect("MainServlet?action=view");
            break;
            case "agregarC":
                try {
                int id_usr = user.getId();
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");

                if (id == 0) {
                    sql = "INSERT INTO CATEGORIA (id_usuario, nombre, descripcion) VALUES (?,?,?)";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, id_usr);
                    ps.setString(2, nombre);
                    ps.setString(3, descripcion);
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception es) {
                System.out.println("error: " + es.getMessage());
            }
            response.sendRedirect("MainServlet?action=view");
            break;
            case "descripcion":
                try {
                int id_det = Integer.parseInt(request.getParameter("id_det"));
                int id_cat = Integer.parseInt(request.getParameter("id_cat"));
                String titulo = request.getParameter("titulo");
                String fecha = request.getParameter("fecha");
                String contenido = "vacio";
                Part archivo = request.getPart("archivo");
                contenido = convertFileToBase64String(archivo);
                String formato = request.getParameter("formato");
                String descripcion = request.getParameter("descripcion");
                if ("editar".equals(op)) {
                    response.sendRedirect("editarDocumento.jsp?id=" + id_det);
                } else if ("ver".equals(op)) {

                    response.sendRedirect("verDocumento.jsp?id=" + id_det);
                } else if ("descargar".equals(op)) {

                    response.getWriter().println("Descargando documento con ID: " + id_det);
                } else {

                    request.setAttribute("descripcion", descripcion);

                    request.getRequestDispatcher("categoria.jsp").forward(request, response);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            response.sendRedirect("MainServlet?action=view");
            break;
            case "descripcion22":
                try {
                int id_det = Integer.parseInt(request.getParameter("id_det"));
                int id_cat = Integer.parseInt(request.getParameter("id_cat"));
                String titulo = request.getParameter("titulo");
                String fecha = request.getParameter("fecha");
                String contenido = "vacio";
                Part archivo = request.getPart("archivo");
                contenido = convertFileToBase64String(archivo);
                String formato = request.getParameter("formato");
                String descripcion = request.getParameter("descripcion");
                if ("editar".equals(op)) {
                    response.sendRedirect("editarDocumento.jsp?id=" + id_det);
                } else if ("ver".equals(op)) {

                    response.sendRedirect("verDocumento.jsp?id=" + id_det);
                } else if ("descargar".equals(op)) {

                    response.getWriter().println("Descargando documento con ID: " + id_det);
                } else {

                    request.setAttribute("descripcion", descripcion);

                    request.getRequestDispatcher("descripcionDocumento.jsp").forward(request, response);
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            response.sendRedirect("MainServlet?action=view");
            break;
            default:
                throw new AssertionError();

        }

    }

    // funcion para convertir un archivo a base 64
    private String convertFileToBase64String(Part filePart) throws IOException {
        try (InputStream fileContent = filePart.getInputStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1048576 * 10];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] fileBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(fileBytes);
        }
    }

    // funcion para descargar un archivo
    public void saveBase64StringToFile(String base64String, String filePath) throws IOException {
        byte[] fileBytes = Base64.getDecoder().decode(base64String);
        try (OutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(fileBytes);
        }
    }

    // funcion para visualizar documento
    private void showFileInBrowser(HttpServletResponse response, String base64String, String contentType) throws IOException {
        byte[] fileBytes = Base64.getDecoder().decode(base64String);
        response.setContentType(contentType);
        response.setContentLength(fileBytes.length);
        try (OutputStream out = response.getOutputStream()) {
            out.write(fileBytes);
        }
    }
}
