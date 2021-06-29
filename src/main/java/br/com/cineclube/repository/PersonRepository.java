package br.com.cineclube.repository;

import br.com.cineclube.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

	List<Person> findAll();

	static Optional<Person> findByUuid(UUID uuid) {
		return Optional.empty();
	}

	Optional<Person> findById(UUID uuid);
}
