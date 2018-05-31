package com.senasoft.com.tradicionalplases.modelo;

/**
 * Created by Desarrolladores Sena on 27/09/2016.
 */

public class CartaModel {
    private String nombre;
    private String descripcion;
    private String precio;
    private String imagen;
    private String id;

    public CartaModel(String nombre, String descripcion, String precio, String imagen, String id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.id = id;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
