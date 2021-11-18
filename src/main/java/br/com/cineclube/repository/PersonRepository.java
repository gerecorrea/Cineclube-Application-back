package br.com.cineclube.repository;

import br.com.cineclube.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

	List<Person> findAll();

	Optional<Person> findById(UUID uuid);

	@Query(value = "SELECT * FROM person WHERE numfavorites > 0 ORDER BY numfavorites DESC LIMIT :limit", nativeQuery = true)
	List<Person> findTopArtists(int limit);
}
