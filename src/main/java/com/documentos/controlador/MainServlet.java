/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.documentos.controlador;

import com.documentos.documento;
import com.documentos.categoria;
import com.documentos.detalle_documento;
import com.documentos.usuario;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
        location = "C:\\Users\\Villalba\\Desktop\\si",
        fileSizeThreshold = 1024 * 1024, //1MB
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
                documento documento = new documento();
                detalle_documento det_doc = new detalle_documento();
                request.setAttribute("user", user);
                request.setAttribute("det_doc", det_doc);
                request.setAttribute("documento", documento);
                request.getRequestDispatcher("SubirDocumento.jsp").forward(request, response);
                break;
            case "buscar":
                request.getRequestDispatcher("ResultadoBusquedajsp").forward(request, response);
                break;
            case "categoria":
                categoria cat = new categoria();
                request.setAttribute("categoria", cat);

                request.getRequestDispatcher("CrearCategoria.jsp").forward(request, response);
            case "usr":
                request.setAttribute("user", user);
                request.getRequestDispatcher("usuario.jsp").forward(request, response);
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
                    int id_cat = Integer.parseInt(request.getParameter("id_cat"));
                    String titulo = request.getParameter("titulo");
                    String fecha = request.getParameter("fecha");
                    String contenido = "vacio";
                    Part archivo = request.getPart("archivo");
                    contenido = convertFileToBase64String(archivo);
                    String formato = request.getParameter("formato");
                    String descripcion = request.getParameter("descripcion");
                    int id_cate = Integer.parseInt(request.getParameter("categoria"));
                    if (id_det == 0) {
                        formato = archivo.getContentType();
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
                            id_det=rs.getInt("id_detalle");
                            sql = "INSERT INTO DOCUMENTO(id_detalle) VALUES(?)";
                            ps = conn.prepareStatement(sql);
                            ps.setInt(1, id_det);
                            ps.executeUpdate();
                        }
                        
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
