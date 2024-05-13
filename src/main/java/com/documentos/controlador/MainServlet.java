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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_sis_gestion_documentos";
        String usuario = "root";
        String password = "";
        Connection conn = null;
        String sql = "select t1.nombre as titulo, DATE_FORMAT(t1.fecha, '%Y-%m-%d') as fecha, t1.descripcion as descripcion from detalle_documento as t1, documento as t2 where t1.id_detalle=t2.id_detalle";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<detalle_documento> lista = new ArrayList<detalle_documento>();
        try {
            Class.forName(driver);
            
            conn=DriverManager.getConnection(url, sql, password);
            
            sql = "select t1.nombre as titulo, DATE_FORMAT(t1.fecha, '%Y-%m-%d') as fecha, t1.descripcion as descripcion from detalle_documento as t1, documento as t2 where t1.id_detalle=t2.id_detalle";
            
            ps = conn.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
                detalle_documento doc = new detalle_documento();
                doc.setNombre(rs.getString("titulo"));
                doc.setFecha(rs.getString("fecha"));
                doc.setDescripcion(rs.getString("descripcion"));
                lista.add(doc);
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error en driver "+e.getMessage());
        }catch(SQLException e){
            System.out.println("Error al conectar "+e.getMessage());
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
