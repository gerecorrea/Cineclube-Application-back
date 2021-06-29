package br.com.cineclube.repository;

import br.com.cineclube.entity.MoviePersonRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MoviePersonRelationRepository extends JpaRepository<MoviePersonRelation, UUID> {

	List<MoviePersonRelation> findAll();

	Optional<MoviePersonRelation> findByUuid(UUID uuid);

	List<MoviePersonRelation> findByMovieUuid(UUID uuid);

	List<MoviePersonRelation> findByMovieUuidAndJobContainingIgnoreCase(UUID uuid, String job);

	List<MoviePersonRelation> findByPersonUuid(UUID uuid);

	List<MoviePersonRelation> findByPersonUuidAndJobContainingIgnoreCase(UUID uuid, String job);

	List<MoviePersonRelation> findByJob(String Job);
}
