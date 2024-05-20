/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.documentos.controlador;

import com.documentos.documento;
import com.documentos.categoria;
import com.documentos.detalle_documento;
import com.documentos.usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
                            //System.out.println(rs.getString("titulo"));
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
            default:
                throw new AssertionError();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op");
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
            default:
                throw new AssertionError();
        }

    }
    

}
