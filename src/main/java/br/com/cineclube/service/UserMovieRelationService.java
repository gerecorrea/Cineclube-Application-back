package br.com.cineclube.service;

import br.com.cineclube.entity.User;
import br.com.cineclube.entity.UserMovieRelation;
import br.com.cineclube.repository.UserMovieRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserMovieRelationService {

	@Autowired
	UserMovieRelationRepository userMovieRelationRepository;

	public UserMovieRelation create(UserMovieRelation userMovieRelation){
		return userMovieRelationRepository.save(userMovieRelation);
	}
	public void delete(UserMovieRelation userMovieRelation){
		userMovieRelationRepository.delete(userMovieRelation);
	}

	public Optional<UserMovieRelation> findByUuid(UUID uuid){
		return userMovieRelationRepository.findByUuid(uuid);
	}

	public Optional<UserMovieRelation> findByUserUuidAndMovieUuid(UUID user, UUID movie){
		return userMovieRelationRepository.findByUserUuidAndMovieUuid(user, movie);
	}

	public List<UserMovieRelation> findByMovieUuid(UUID uuid){
		return userMovieRelationRepository.findByMovieUuid(uuid);
	}

	public List<UserMovieRelation> findByUserUuidAndFavorite(UUID uuid, boolean favorite){
		return userMovieRelationRepository.findByUserUuidAndFavorite(uuid, favorite);
	}

	public List<UserMovieRelation> findByUserUuidAndWatchlist(UUID uuid, boolean watchlist){
		return userMovieRelationRepository.findByUserUuidAndWatchlist(uuid, watchlist);
	}

	public List<UserMovieRelation> findByUserUuidAndRating(UUID uuid, int rating){
		return userMovieRelationRepository.findByUserUuidAndRating(uuid, rating);
	}

	public List<UserMovieRelation> findByUserUuidAndIsRated(UUID uuid, boolean rated){
		return userMovieRelationRepository.findByUserUuidAndIsRated(uuid, rated);
	}

}
