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
        location = "C:\\Users\\nivek\\Desktop",
        fileSizeThreshold = 1024 * 1024 * 10, //1MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 11 //11MB
)

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    int cd=0;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db_sis_gestion_documento";
    String usuario = "root";
    String password = "1234";
    Connection conn = null;
    String sql = "";
    PreparedStatement ps = null;
    ResultSet rs = null;
    usuario user = new usuario();
    ArrayList<detalle_documento> listar= new ArrayList<detalle_documento>();
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

                        sql = "SELECT t1.id_detalle AS id, t1.nombre AS titulo, DATE_FORMAT(t1.fecha, '%Y-%m-%d') AS fecha, t1.archivo AS arch, t1.formato AS formato, t1.id_categoria AS id_cat,t1.descripcion AS descripcion FROM detalle_documento AS t1, documento AS t2 WHERE t1.id_detalle = t2.id_detalle ORDER BY t2.id_documento DESC;";

                        ps = conn.prepareStatement(sql);

                        rs = ps.executeQuery();

                        while (rs.next()) {
                            System.out.println(rs.getString("titulo"));
                            System.out.println(rs.getString("formato"));
                            detalle_documento doc = new detalle_documento();
                            doc.setId_det(rs.getInt("id"));
                            doc.setNombre(rs.getString("titulo"));
                            doc.setFecha(rs.getString("fecha"));
                            doc.setArchivo(rs.getString("arch"));
                            doc.setFormato(rs.getString("formato"));
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

                        sql = "SELECT t1.id_detalle AS id, t1.nombre AS titulo, DATE_FORMAT(t1.fecha, '%Y-%m-%d') AS fecha, t1.archivo AS arch, t1.formato AS formato, t1.id_categoria AS id_cat,t1.descripcion AS descripcion FROM detalle_documento AS t1, documento AS t2 WHERE t1.id_detalle = t2.id_detalle and t1.id_categoria=1 ORDER BY t2.id_documento DESC;";

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
            case "resuladoCat":
                ArrayList<detalle_documento> listaDocCat = new ArrayList<detalle_documento>();
                int id_categoria1 = Integer.parseInt(request.getParameter("id_categoria"));
                sql = "select * from detalle_documento as t1, documento as t2 where id_categoria = ? and t1.id_detalle=t2.id_detalle";
                 {
                    try {
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, id_categoria1);
                        rs = ps.executeQuery();
                        while (rs.next()) {

                            detalle_documento det_docu = new detalle_documento();
                            det_docu.setId_det(rs.getInt("id_detalle"));
                            det_docu.setNombre(rs.getString("nombre"));
                            det_docu.setFecha(rs.getString("fecha"));
                            det_docu.setArchivo(rs.getString("archivo"));
                            det_docu.setFormato(rs.getString("formato"));
                            det_docu.setId_cat(rs.getInt("id_categoria"));
                            det_docu.setId_usr(rs.getInt("id_usuario"));
                            det_docu.setDescripcion(rs.getString("descripcion"));
                            listaDocCat.add(det_docu);
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
                    } catch (SQLException ex) {
                        System.out.println("mgs" + ex.getMessage());
                    }
                }
                request.setAttribute("user", user);
                request.setAttribute("lista2", lista2);
                request.setAttribute("listaDocCat", listaDocCat);
                request.getRequestDispatcher("categoria.jsp").forward(request, response);
                break;
            case "descripcionDoc":
                detalle_documento det_documento = new detalle_documento();
                ArrayList<detalle_documento> listaDocumento = new ArrayList<detalle_documento>();
                int id_documento1 = Integer.parseInt(request.getParameter("id_documento"));
                sql = "SELECT d.id_detalle,d.nombre,d.fecha,d.archivo,d.formato,d.id_categoria,c.nombre AS nombre_categoria,d.id_usuario,u.nombres AS nombre_usuario,d.descripcion FROM detalle_documento d JOIN categoria c ON d.id_categoria = c.id_categoria JOIN usuario u ON d.id_usuario = u.id_usuario WHERE d.id_detalle=?";
                categoria categoria = new categoria();
                usuario usr = new usuario();
                detalle_documento det_docum = new detalle_documento();
                try {
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, id_documento1);
                    rs = ps.executeQuery();
                    int id_c = 0;
                    int id_u = 0;

                    while (rs.next()) {
                        id_u = rs.getInt("id_usuario");
                        id_c = rs.getInt("id_categoria");
                        det_docum.setId_det(rs.getInt("id_detalle"));
                        det_docum.setNombre(rs.getString("nombre"));
                        det_docum.setFecha(rs.getString("fecha"));
                        det_docum.setArchivo(rs.getString("archivo"));
                        det_docum.setFormato(rs.getString("formato"));
                        det_docum.setId_cat(rs.getInt("id_categoria"));
                        det_docum.setId_usr(rs.getInt("id_usuario"));
                        det_docum.setDescripcion(rs.getString("descripcion"));

                    }
                    sql = "select * from categoria where id_categoria=?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, id_c);
                    rs = ps.executeQuery();
                    while (rs.next()) {

                        categoria.setId(rs.getInt("id_categoria"));
                        categoria.setId_usr(rs.getInt("id_usuario"));
                        categoria.setNombre(rs.getString("nombre"));
                        categoria.setDescripcion(rs.getString("Descripcion"));

                    }
                    sql = "select * from usuario where id_usuario=?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, id_u);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        //System.out.println("jajaja"+rs.getString("usuario"));
                        usr.setId(rs.getInt("id_usuario"));
                        usr.setNombres(rs.getString("nombres"));
                        usr.setApellidos(rs.getString("apellidos"));
                        usr.setTelefono(rs.getInt("telefono"));
                        usr.setEmail(rs.getString("email"));
                        usr.setDireccion(rs.getString("direccion"));
                        usr.setContrasena(rs.getString("contrasena"));
                        usr.setUsuario(rs.getString("usuario"));
                    }
                } catch (SQLException ex) {
                    System.out.println("mgs" + ex.getMessage());
                }

                request.setAttribute("user", user);
                request.setAttribute("usuario", usr);
                request.setAttribute("categoria", categoria);
                request.setAttribute("detalle_documento", det_docum);
                request.getRequestDispatcher("descripcionDocumento.jsp").forward(request, response);
                break;
            case "buscar":
                
                //detalle_documento lista1 = new detalle_documento();
                request.setAttribute("user", user);
                request.setAttribute("lista1", listar);
                request.getRequestDispatcher("ResultadoBusqueda.jsp").forward(request, response);

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
                    System.out.println("archivo: " + archivo);
                    System.err.println("formato: " + formato);
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
            case "verDoc":
                try {
                int id_detalle = Integer.parseInt(request.getParameter("id_detalle"));
                detalle_documento a = new detalle_documento();
                sql = "select * from detalle_documento where id_detalle = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id_detalle);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String archivo = rs.getString("archivo");
                    String formato = rs.getString("formato");
                    showFileInBrowser(response, archivo, formato);
                }

            } catch (SQLException e) {
                System.out.println("msg: " + e.getMessage());
            }
            break;
            case"descargar":
                try{
                 
                int id=Integer.parseInt(request.getParameter("id"));
                sql = "select * from detalle_documento where id_detalle = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String archivo = rs.getString("archivo");
                    String formato = rs.getString("formato");
                    saveBase64StringToFile(archivo, formato);
                }
                
                request.getRequestDispatcher("MainServlet?action=descripcionDoc&id_documento="+id).forward(request, response);
                } catch (SQLException e) {
                System.out.println("msg: " + e.getMessage());
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
                int id_doc = Integer.parseInt(request.getParameter("id_doc"));
                int id_user = user.getId();
                System.out.println("id_det " + id_det);
                System.out.println("id_cat " + id_cat);
                String titulo = request.getParameter("titulo");
                String fecha = request.getParameter("fecha");
                String contenido = "";
                Part archivo = request.getPart("archivo");
                contenido = convertFileToBase64String(archivo);
                System.out.println("qwerty:" + contenido);
                String formato = request.getParameter("formato");
                String descripcion = request.getParameter("descripcion");
                int id_cate = Integer.parseInt(request.getParameter("categoria_id"));
                if (id_det == 0) {
                    formato = archivo.getContentType();
                    System.out.println("formato: " + formato);
                    sql = "INSERT INTO DETALLE_DOCUMENTO (nombre, fecha, archivo, formato, id_categoria, id_usuario, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, titulo);
                    ps.setString(2, fecha);
                    ps.setString(3, contenido);
                    ps.setString(4, formato);
                    ps.setInt(5, id_cate);
                    ps.setInt(6, id_user);
                    ps.setString(7, descripcion);
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
                    if ("".equals(contenido)) {
                        contenido = request.getParameter("contenido");
                        System.out.println("contenido: " + contenido);
                        System.out.println("formato: " + formato);
                    } else {
                        formato = archivo.getContentType();
                    }
                    sql = "INSERT INTO DETALLE_DOCUMENTO (nombre, fecha, archivo, formato, id_categoria, id_usuario, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, titulo);
                    ps.setString(2, fecha);
                    ps.setString(3, contenido);
                    ps.setString(4, formato);
                    ps.setInt(5, id_cate);
                    ps.setInt(6, id_user);
                    ps.setString(7, descripcion);
                    ps.executeUpdate();
                    sql = "SELECT id_detalle FROM DETALLE_DOCUMENTO ORDER BY id_detalle DESC LIMIT 1";
                    rs = ps.executeQuery(sql);
                    if (rs.next()) {
                        id_det = rs.getInt("id_detalle");
                        sql = "UPDATE DOCUMENTO SET id_detalle = ? WHERE id_documento = ?;";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, id_det);
                        ps.setInt(2, id_doc);
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
            case "buscar":
                int id_det = user.getId();
              
                //detalle_documento lista1 = new detalle_documento();
                String buscar = request.getParameter("busqueda");
                try {
                    listar=new ArrayList<detalle_documento>();
                    sql = "select * from detalle_documento as t1, documento as t2 where t1.id_detalle=t2.id_detalle and (t1.nombre LIKE '%" + buscar + "%' or t1.fecha LIKE '%" + buscar + "%' or t1.descripcion LIKE '%" + buscar + "%')" ;
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while(rs.next()){
                        detalle_documento de= new detalle_documento();
                        de.setId_det(rs.getInt("id_detalle"));
                        de.setNombre(rs.getString("nombre"));
                        de.setFecha(rs.getString("fecha"));
                        de.setArchivo(rs.getString("archivo"));
                        de.setFormato(rs.getString("formato"));
                        de.setId_cat(rs.getInt("id_categoria"));
                        de.setId_usr(rs.getInt("id_usuario"));
                        de.setDescripcion(rs.getString("descripcion"));
                        listar.add(de);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                response.sendRedirect("MainServlet?action=buscar");
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
                sql = "select descripcion from categoria ";
                ps = conn.prepareStatement(sql);
                categoria cate = new categoria();
                response.sendRedirect(request.getContextPath() + "/MainServlet");
                String descripcion = rs.getString("descripcion");
                cate.setDescripcion(descripcion);
                response.sendRedirect("MainServlet?action=view");
                break;
            } catch (SQLException e) {
                System.out.println("Error al conectar " + e.getMessage());
            }
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
        String ruta="C:\\Users\\nivek\\Desktop\\";
        String formato="";
        if("text/plain".equals(filePath)){
            cd++;
            formato= "descarga.txt";
            try (OutputStream fileOutputStream = new FileOutputStream(ruta+formato)) {
            fileOutputStream.write(fileBytes);
            }
        }else if("application/pdf".equals(filePath)){
            cd++;
            formato= "descarga.pdf";
            try (OutputStream fileOutputStream = new FileOutputStream(ruta+formato)) {
            fileOutputStream.write(fileBytes);
            }
        }else if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(filePath)){
            cd++;
            formato= "descarga.docx";
            try (OutputStream fileOutputStream = new FileOutputStream(ruta+formato)) {
            fileOutputStream.write(fileBytes);
            }
        }else {
        
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
