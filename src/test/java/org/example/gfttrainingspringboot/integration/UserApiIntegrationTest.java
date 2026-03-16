package org.example.gfttrainingspringboot.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gfttrainingspringboot.dto.UserDTO;
import org.example.gfttrainingspringboot.model.User;
import org.example.gfttrainingspringboot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void cleanData() {
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateAndThenGetUser() throws Exception {
        UserDTO createRequest = new UserDTO(null, "Marta");

        MvcResult createResult = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Marta"))
                .andReturn();

        JsonNode json = objectMapper.readTree(createResult.getResponse().getContentAsString());
        long id = json.get("id").asLong();

        mockMvc.perform(get("/api/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Marta"));
    }

    @Test
    void shouldUpdateExistingUser() throws Exception {
        User created = userRepository.save(new User(null, "Inicial"));

        UserDTO updateRequest = new UserDTO(null, "Actualizado");

        mockMvc.perform(put("/api/users/{id}", created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Actualizado"));

        User persisted = userRepository.findById(created.getId()).orElseThrow();
        assertEquals("Actualizado", persisted.getName());
    }

    @Test
    void shouldDeleteExistingUser() throws Exception {
        User created = userRepository.save(new User(null, "Borrar"));

        mockMvc.perform(delete("/api/users/{id}", created.getId()))
                .andExpect(status().isNoContent());

        assertFalse(userRepository.findById(created.getId()).isPresent());
    }

    @Test
    void shouldReturn404WhenUserDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/users/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Usuario no encontrado con id: 999"));
    }
}

