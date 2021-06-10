package br.com.cineclube.repository;

import br.com.cineclube.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findUserByLoginUuid(UUID uuid);
}