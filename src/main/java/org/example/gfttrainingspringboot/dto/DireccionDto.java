package org.example.gfttrainingspringboot.dto;

public class DireccionDto {

    private String numero;
    private String calle;
    private int codigoPostal;
    private String localidad;

    public DireccionDto() {
    }

    public DireccionDto(String numero, String calle, int codigoPostal, String localidad) {
        this.numero = numero;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
    }
}
