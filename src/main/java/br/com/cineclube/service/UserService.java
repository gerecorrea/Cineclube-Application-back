package br.com.cineclube.service;

import br.com.cineclube.entity.User;
import br.com.cineclube.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	UserRepository userRepository;

	public Optional<User> findUserByLoginUuid(UUID uuid) {
		return userRepository.findUserByLoginUuid(uuid);
	}
}