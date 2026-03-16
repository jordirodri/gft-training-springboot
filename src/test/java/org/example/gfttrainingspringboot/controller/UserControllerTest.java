package org.example.gfttrainingspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gfttrainingspringboot.dto.UserDTO;
import org.example.gfttrainingspringboot.exception.GlobalExceptionHandler;
import org.example.gfttrainingspringboot.exception.ResourceNotFoundException;
import org.example.gfttrainingspringboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(GlobalExceptionHandler.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void whenGetAllThenReturn200AndList() throws Exception {
        when(userService.findAll()).thenReturn(List.of(
                new UserDTO(1L, "Ana"),
                new UserDTO(2L, "Luis")
        ));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Ana"))
                .andExpect(jsonPath("$[1].name").value("Luis"));
    }

    @Test
    void whenGetByIdThenReturn200AndUser() throws Exception {
        when(userService.findById(1L)).thenReturn(new UserDTO(1L, "Ana"));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Ana"));
    }

    @Test
    void whenGetByIdAndUserDoesNotExistThenReturn404() throws Exception {
        when(userService.findById(99L)).thenThrow(new ResourceNotFoundException("Usuario no encontrado con id: 99"));

        mockMvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Usuario no encontrado con id: 99"));
    }

    @Test
    void whenCreateWithValidPayloadThenReturn201() throws Exception {
        UserDTO request = new UserDTO(null, "Marta");
        when(userService.create(any(UserDTO.class))).thenReturn(new UserDTO(10L, "Marta"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.name").value("Marta"));
    }

    @Test
    void whenCreateWithInvalidPayloadThenReturn400() throws Exception {
        UserDTO request = new UserDTO(null, "");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("name: El nombre es obligatorio"));
    }

    @Test
    void whenUpdateThenReturn200AndUpdatedUser() throws Exception {
        UserDTO request = new UserDTO(null, "Nombre nuevo");
        when(userService.update(eq(5L), any(UserDTO.class))).thenReturn(new UserDTO(5L, "Nombre nuevo"));

        mockMvc.perform(put("/api/users/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.name").value("Nombre nuevo"));
    }

    @Test
    void whenUpdateWithInvalidPayloadThenReturn400() throws Exception {
        UserDTO request = new UserDTO(null, "");

        mockMvc.perform(put("/api/users/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("name: El nombre es obligatorio"));
    }

    @Test
    void whenUpdateAndUserDoesNotExistThenReturn404() throws Exception {
        UserDTO request = new UserDTO(null, "Nombre nuevo");
        when(userService.update(eq(999L), any(UserDTO.class)))
                .thenThrow(new ResourceNotFoundException("Usuario no encontrado con id: 999"));

        mockMvc.perform(put("/api/users/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Usuario no encontrado con id: 999"));
    }

    @Test
    void whenDeleteThenReturn204() throws Exception {
        doNothing().when(userService).delete(3L);

        mockMvc.perform(delete("/api/users/3"))
                .andExpect(status().isNoContent());

        verify(userService).delete(3L);
    }

    @Test
    void whenDeleteAndUserDoesNotExistThenReturn404() throws Exception {
        doThrow(new ResourceNotFoundException("Usuario no encontrado con id: 33")).when(userService).delete(33L);

        mockMvc.perform(delete("/api/users/33"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Usuario no encontrado con id: 33"));
    }

    @Test
    void whenServiceThrowsUnexpectedErrorThenReturn500() throws Exception {
        when(userService.findById(12L)).thenThrow(new RuntimeException("boom"));

        mockMvc.perform(get("/api/users/12"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Error interno del servidor"));
    }
}

