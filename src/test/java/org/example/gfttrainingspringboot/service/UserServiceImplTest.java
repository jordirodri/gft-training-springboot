package org.example.gfttrainingspringboot.service;

import org.example.gfttrainingspringboot.dto.UserDTO;
import org.example.gfttrainingspringboot.exception.ResourceNotFoundException;
import org.example.gfttrainingspringboot.model.User;
import org.example.gfttrainingspringboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void givenUsersWhenFindAllThenReturnMappedDtos() {
        when(userRepository.findAll()).thenReturn(List.of(
                new User(1L, "Ana"),
                new User(2L, "Luis")
        ));

        List<UserDTO> result = userService.findAll();

        assertEquals(2, result.size());
        assertEquals("Ana", result.get(0).getName());
        assertEquals("Luis", result.get(1).getName());
    }

    @Test
    void givenNoUsersWhenFindAllThenReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(List.of());

        List<UserDTO> result = userService.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void givenExistingIdWhenFindByIdThenReturnDto() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "Ana")));

        UserDTO result = userService.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Ana", result.getName());
    }

    @Test
    void givenMissingIdWhenFindByIdThenThrowResourceNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> userService.findById(99L));

        assertTrue(ex.getMessage().contains("99"));
    }

    @Test
    void givenValidDtoWhenCreateThenPersistAndReturnDto() {
        UserDTO input = new UserDTO(null, "Marta");
        when(userRepository.save(any(User.class))).thenReturn(new User(10L, "Marta"));

        UserDTO result = userService.create(input);

        assertEquals(10L, result.getId());
        assertEquals("Marta", result.getName());

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertEquals("Marta", captor.getValue().getName());
    }

    @Test
    void givenExistingIdWhenUpdateThenPersistChanges() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User(1L, "Nombre antiguo")));
        when(userRepository.save(any(User.class))).thenReturn(new User(1L, "Nombre nuevo"));

        UserDTO result = userService.update(1L, new UserDTO(null, "Nombre nuevo"));

        assertEquals(1L, result.getId());
        assertEquals("Nombre nuevo", result.getName());
        verify(userRepository, times(1)).save(any(User.class));

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertEquals(1L, captor.getValue().getId());
        assertEquals("Nombre nuevo", captor.getValue().getName());
    }

    @Test
    void givenMissingIdWhenUpdateThenThrowResourceNotFound() {
        when(userRepository.findById(50L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.update(50L, new UserDTO(null, "X")));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void givenExistingIdWhenDeleteThenCallRepositoryDelete() {
        User user = new User(3L, "Pepe");
        when(userRepository.findById(3L)).thenReturn(Optional.of(user));

        userService.delete(3L);

        verify(userRepository).delete(user);
    }

    @Test
    void givenMissingIdWhenDeleteThenThrowResourceNotFound() {
        when(userRepository.findById(77L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.delete(77L));
        verify(userRepository, never()).delete(any(User.class));
    }
}

