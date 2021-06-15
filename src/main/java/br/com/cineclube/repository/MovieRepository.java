package br.com.cineclube.repository;

import br.com.cineclube.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

	List<Movie> findAll();

	Optional<Movie> findByUuid(UUID uuid);

}
