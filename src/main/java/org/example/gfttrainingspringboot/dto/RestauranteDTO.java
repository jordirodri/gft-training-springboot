package org.example.gfttrainingspringboot.dto;

public class RestauranteDTO {
    private String nombre;
    private String direccion;
    private String[] imagenes;
    private String categoria;

    public RestauranteDTO() {
    }

    public RestauranteDTO(String nombre, String direccion, String[] imagenes, String categoria) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagenes = imagenes;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
