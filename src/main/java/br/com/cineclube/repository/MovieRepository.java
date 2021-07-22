package br.com.cineclube.repository;

import br.com.cineclube.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

	List<Movie> findByMovieTypeOrderByTitleAsc(String movieType);

	// Top 10 (variável) com mínimo numVotes variável conforme contexto:
	@Query(value = "SELECT * FROM movie m WHERE numvotes > 0 AND movietype = 'MOVIE' ORDER BY avgrating DESC LIMIT 10", nativeQuery = true)
	List<Movie> findTop10();

	Optional<Movie> findByUuid(UUID uuid);

}
