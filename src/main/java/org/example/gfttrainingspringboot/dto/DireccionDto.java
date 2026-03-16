package org.example.gfttrainingspringboot.dto;

public class DireccionDto {

    private String numero;
    private String calle;
    private int codigoPostal;

    public DireccionDto() {
    }

    public DireccionDto(String numero, String calle, int codigoPostal) {
        this.numero = numero;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
    }
}
