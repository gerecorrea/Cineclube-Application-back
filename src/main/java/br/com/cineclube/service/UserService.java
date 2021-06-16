package br.com.cineclube.service;

import br.com.cineclube.entity.User;
import br.com.cineclube.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User create(User user) {
		return userRepository.save( user );
	}

	public User update(UUID uuid, User user) {
		user.setUuid( uuid );
		return userRepository.save( user );
	}

	public void remove(UUID uuid) {
		Optional<User> user = userRepository.findById( uuid );
		if (user.isPresent()) {
			user.get().getLogin().setActive(false);
			userRepository.save(user.get());
		}
	}

	public void activateInactivateUser(UUID uuid) {
		Optional<User> user = userRepository.findById( uuid );
		if (user.isPresent()) {
			user.get().getLogin().setActive(!user.get().getLogin().isActive());
			userRepository.save(user.get());
		}
	}

	public void changeAdmin(UUID uuid) {
		Optional<User> optionalUser = userRepository.findById( uuid );
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.getLogin().setAdmin(!user.getLogin().isAdmin());
			userRepository.save( user );
		}
	}

	public Optional<User> findUserByLoginUuid(UUID uuid) {
		return userRepository.findUserByLoginUuid(uuid);
	}

	public Optional<User> findById(UUID uuid) {
		return this.userRepository.findById( uuid );
	}

	public List<User> findAll(){ return userRepository.findAll();}
}