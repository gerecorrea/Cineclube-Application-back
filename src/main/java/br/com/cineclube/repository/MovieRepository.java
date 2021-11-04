package br.com.cineclube.repository;

import br.com.cineclube.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

	List<Movie> findByMovieTypeOrderByTitleAsc(String movieType);

	@Query(value="SELECT * FROM movie m" +
			" WHERE movietype = :movieType" +
			" AND lower(m.title) LIKE lower(concat('%', :title, '%'))" +
			" AND lower(m.country) LIKE lower(concat('%', :country, '%'))" +
			" AND m.\"year\" >= :yearMin AND m.\"year\" <= :yearMax" +
			" AND m.runtime >= :durationMin AND m.runtime <= :durationMax" +
			" AND m.numvotes >= :numVotesMin AND m.numvotes <= :numVotesMax" +
			" AND m.avgrating >= :avgRatingMin AND m.avgrating <= :avgRatingMax" +
			" ORDER BY m.title ASC", nativeQuery = true)
	List<Movie> findByFilters(String movieType, String title, String country,
	                          int yearMin, int yearMax, int durationMin, int durationMax,
	                          int numVotesMin, int numVotesMax, float avgRatingMin, float avgRatingMax);

	// Top 10 (variável) com mínimo numVotes variável conforme contexto:
	@Query(value = "SELECT * FROM movie m WHERE numvotes > 0 AND movietype = 'MOVIE' ORDER BY avgrating DESC LIMIT 10", nativeQuery = true)
	List<Movie> findTop10();

	@Query(value = "SELECT * FROM movie WHERE numfavorites > 0 ORDER BY numfavorites DESC LIMIT 10", nativeQuery = true)
	List<Movie> findTopFavoriteAll();

	@Query(value = "SELECT * FROM movie WHERE numvotes> 0 ORDER BY avgrating DESC LIMIT 10", nativeQuery = true)
	List<Movie> findTopRatingAll();

	Optional<Movie> findByUuid(UUID uuid);

	@Query(value = "SELECT * FROM movie ORDER BY datereleased DESC LIMIT 25", nativeQuery = true)
	List<Movie> findFirst25MoviesByDateReleasedDesc();

}
