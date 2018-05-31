package com.senasoft.com.tradicionalplases.modelo;

/**
 * Created by Desarrolladores Sena on 27/09/2016.
 */

public class PedidoModel {
    private String id;
    private String cantidad;
    private int total;
    private String nombre;
    private String precio;
    private String id_pedido;

    public PedidoModel(String id, String cantidad, int total, String nombre, String precio, String id_pedido) {
        this.id = id;
        this.cantidad = cantidad;
        this.total = total;
        this.nombre = nombre;
        this.precio = precio;
        this.id_pedido = id_pedido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }
}
