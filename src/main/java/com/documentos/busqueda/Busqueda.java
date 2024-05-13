
package com.documentos.busqueda;

import com.documentos.detalle_documento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nivek
 */
@WebServlet(name = "Busqueda", urlPatterns = {"/Busqueda"})
public class Busqueda extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sistema_de_gestion_de_documentos";
        String usuario = "root";
        String password = "";
        Connection conn = null;
        String sql = "select t1.nombre as titulo, DATE_FORMAT(t1.fecha, '%Y-%m-%d') as fecha, t1.descripcion as descripcion from detalle_documento as t1, documento as t2 where t1.id_detalle=t2.id_detalle";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<detalle_documento> lista = new ArrayList<detalle_documento>();
        try {
            while (rs.next()) {
                detalle_documento doc = new detalle_documento();
                try {
                    doc.setNombre(rs.getString("nombre"));
                } catch (SQLException ex) {
                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    doc.setFecha(rs.getString("fecha"));
                } catch (SQLException ex) {
                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    doc.setDescripcion(rs.getString("descripcion"));
                } catch (SQLException ex) {
                    Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                }
                lista.add(doc);
            }       } catch (SQLException ex) {
            Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
        }

request.setAttribute("lista", lista);
request.getRequestDispatcher("inicio.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
   }}


 


