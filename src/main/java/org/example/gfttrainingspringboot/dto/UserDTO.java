package org.example.gfttrainingspringboot.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    public UserDTO() {
    }

    public UserDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
