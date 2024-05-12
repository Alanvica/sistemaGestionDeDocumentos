package com.documentos;

/**
 *
 * @author Villalba
 */
public class categoria {
    private int id;
    private int id_usr;
    private String nombre;
    private String descripcion;

    public categoria() {
        this.id = 0;
        this.id_usr = 0;
        this.nombre = "";
        this.descripcion = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
