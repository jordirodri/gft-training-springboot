package org.example.gfttrainingspringboot.service;

import org.example.gfttrainingspringboot.dto.UserDTO;
import org.example.gfttrainingspringboot.exception.ResourceNotFoundException;
import org.example.gfttrainingspringboot.model.User;
import org.example.gfttrainingspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream()
				.map(this::toDto)
				.toList();
	}

	@Override
	public UserDTO findById(Long id) {
		User user = getUserOrThrow(id);
		return toDto(user);
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());

		User savedUser = userRepository.save(user);
		return toDto(savedUser);
	}

	@Override
	public UserDTO update(Long id, UserDTO userDTO) {
		User existingUser = getUserOrThrow(id);
		existingUser.setName(userDTO.getName());

		User savedUser = userRepository.save(existingUser);
		return toDto(savedUser);
	}

	@Override
	public void delete(Long id) {
		User existingUser = getUserOrThrow(id);
		userRepository.delete(existingUser);
	}

	private User getUserOrThrow(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
	}

	private UserDTO toDto(User user) {
		return new UserDTO(user.getId(), user.getName());
	}
}

