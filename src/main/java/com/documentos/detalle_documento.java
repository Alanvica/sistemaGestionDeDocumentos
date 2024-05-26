package com.documentos;

/**
 *
 * @author Villalba
 */
public class detalle_documento {
    private int id_det;
    private int id_usr;
    private String nombre;
    private String fecha;
    private String archivo;
    private String formato;
    private int id_cat;
    private String descripcion;

    public detalle_documento() {
        this.id_det = 0;
        this.id_usr = 0;
        this.nombre = "";
        this.fecha = "";
        this.archivo = "";
        this.formato = "";
        this.id_cat = 0;
        this.descripcion = "";
    }

    public int getId_det() {
        return id_det;
    }

    public void setId_det(int id_det) {
        this.id_det = id_det;
    }

    public int getId_usr() {
        return id_usr;
    }

    public void setId_usr(int id_usr) {
        this.id_usr = id_usr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    
    
}
