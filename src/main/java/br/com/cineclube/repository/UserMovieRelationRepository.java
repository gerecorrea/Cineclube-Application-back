package br.com.cineclube.repository;

import br.com.cineclube.entity.MoviePersonRelation;
import br.com.cineclube.entity.UserMovieRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserMovieRelationRepository  extends JpaRepository<UserMovieRelation, UUID> {

	List<UserMovieRelation> findAll();

	UserMovieRelation findByUuid(UUID uuid);

	Optional<UserMovieRelation> findByUserUuidAndMovieUuid(UUID user, UUID movie);

	List<UserMovieRelation> findByMovieUuid(UUID uuid);

	Optional<UserMovieRelation> findByUserUuid(UUID uuid);

	List<UserMovieRelation> findByUserUuidAndFavoriteOrderByCreatedDateDesc(UUID uuid, boolean favorite);

	List<UserMovieRelation> findByUserUuidAndFavoriteAndMovieTypeOrderByCreatedDateDesc(UUID uuid, boolean favorite, String movieType);

	List<UserMovieRelation> findByUserUuidAndWatchlistOrderByCreatedDateDesc(UUID uuid, boolean watchlist);

	List<UserMovieRelation> findFirst25ByUserUuidAndWatchlistOrderByCreatedDateDesc(UUID uuid, boolean watchlist);

	List<UserMovieRelation> findByUserUuidAndRatingOrderByCreatedDateDesc(UUID uuid, int rating);

	List<UserMovieRelation> findByUserUuidAndIsRatedOrderByCreatedDateDesc(UUID uuid, boolean rated);

}
