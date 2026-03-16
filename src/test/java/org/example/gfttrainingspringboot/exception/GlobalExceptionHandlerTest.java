package org.example.gfttrainingspringboot.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void whenHandleNotFoundThenReturn404() {
        ResponseEntity<Map<String, String>> response =
                handler.handleNotFound(new ResourceNotFoundException("Usuario no encontrado con id: 1"));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario no encontrado con id: 1", response.getBody().get("error"));
    }

    @Test
    void whenHandleValidationThenReturn400WithFieldMessage() throws Exception {
        Method method = DummyController.class.getDeclaredMethod("create", String.class);
        MethodParameter methodParameter = new MethodParameter(method, 0);

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult("", "userDTO");
        bindingResult.addError(new FieldError("userDTO", "name", "El nombre es obligatorio"));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<Map<String, String>> response = handler.handleValidation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("name: El nombre es obligatorio", response.getBody().get("error"));
    }

    @Test
    void whenHandleGenericThenReturn500() {
        ResponseEntity<Map<String, String>> response = handler.handleGeneric(new RuntimeException("boom"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error interno del servidor", response.getBody().get("error"));
    }

    private static class DummyController {
        public void create(String name) {
        }
    }
}

