package br.com.cineclube.service;

import br.com.cineclube.dto.UserMovieDto;
import br.com.cineclube.entity.Movie;
import br.com.cineclube.entity.User;
import br.com.cineclube.entity.UserMovieRelation;
import br.com.cineclube.repository.UserMovieRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserMovieRelationService {

	@Autowired
	UserMovieRelationRepository userMovieRelationRepository;

	@Autowired
	MovieService movieService;

	public UserMovieRelation create(UserMovieRelation userMovieRelation){

		Optional<Movie> movie = movieService.findByUuid(userMovieRelation.getMovieUuid());
		if (userMovieRelation.getUuid() != null){
			Optional<UserMovieRelation> userMovieRelation1 = userMovieRelationRepository.findById(userMovieRelation.getUuid());
			if (movie.isPresent() && userMovieRelation1.isPresent()){
				if (userMovieRelation1.get().getRating() == 0){
					// Existe relação, mas não com rating
					movie.get().setAvgRating((movie.get().getNumVotes() * movie.get().getAvgRating() + userMovieRelation.getRating()) /
							(movie.get().getNumVotes() + 1));
					movie.get().setNumVotes(movie.get().getNumVotes() + 1);
				}
				else{
					// Se já existe relação user movie com rating e rating anterior
					movie.get().setAvgRating( (movie.get().getAvgRating() * movie.get().getNumVotes() - userMovieRelation1.get().getRating()
							+ userMovieRelation.getRating()) / movie.get().getNumVotes());
					if (userMovieRelation.getRating() == 0){
						movie.get().setNumVotes(movie.get().getNumVotes() - 1);
						userMovieRelation.setRated(false);
					}
				}
			}
		}else {
			// Se não existe relação, então criada agora. Só adiciona se rating != 0
			if (movie.isPresent() && userMovieRelation.getRating() != 0) {
				movie.get().setAvgRating((movie.get().getNumVotes() * movie.get().getAvgRating() + userMovieRelation.getRating()) /
						(movie.get().getNumVotes() + 1));
				movie.get().setNumVotes(movie.get().getNumVotes() + 1);
			}
		}
		movie.ifPresent(value -> userMovieRelation.setImageLink(value.getImageLink()));

		return userMovieRelationRepository.save(userMovieRelation);
	}

	public boolean changeRating(UUID uuid, int rating){
		UserMovieRelation userMovieRelation = userMovieRelationRepository.findByUuid(uuid);
		userMovieRelation.setRating(rating);
		if (rating == 0){
			userMovieRelation.setRated(false);
		}
		userMovieRelation = create(userMovieRelation);
		return userMovieRelation.getClass() != null;
	}

	public boolean changeFavorite(UUID uuid){
		UserMovieRelation userMovieRelation = userMovieRelationRepository.findByUuid(uuid);
		Optional<Movie> movie = movieService.findByUuid(userMovieRelation.getMovieUuid());
		userMovieRelation.setFavorite(!userMovieRelation.isFavorite());
		if (movie.isPresent()) {
			if (userMovieRelation.isFavorite()) movie.get().setNumFavorites(movie.get().getNumFavorites() + 1);
			else movie.get().setNumFavorites(movie.get().getNumFavorites() - 1);
		}
		userMovieRelation = userMovieRelationRepository.save(userMovieRelation);
		return userMovieRelation.getClass() != null;
	}

	public boolean changeWatchlist(UUID uuid){
		UserMovieRelation userMovieRelation = userMovieRelationRepository.findByUuid(uuid);
		userMovieRelation.setWatchlist(!userMovieRelation.isWatchlist());
		userMovieRelation = userMovieRelationRepository.save(userMovieRelation);
		return userMovieRelation.getClass() != null;
	}

	public void delete(UserMovieRelation userMovieRelation){
		userMovieRelationRepository.delete(userMovieRelation);
	}

	public Optional<UserMovieRelation> findByUuid(UUID uuid){
		return userMovieRelationRepository.findById(uuid);
	}

	public Optional<UserMovieRelation> findByUserUuidAndMovieUuid(UUID user, UUID movie){
		return userMovieRelationRepository.findByUserUuidAndMovieUuid(user, movie);
	}

	public List<UserMovieRelation> findByMovieUuid(UUID uuid){
		return userMovieRelationRepository.findByMovieUuid(uuid);
	}

	public List<UserMovieDto> findByUserUuidAndFavorite(UUID uuid){
		List<UserMovieDto> userMovieDtos = new ArrayList<>();
		List<UserMovieRelation> userMovieRelationList = this.userMovieRelationRepository.findByUserUuidAndFavorite(uuid, true);
		for (UserMovieRelation userMovieRelation : userMovieRelationList) {
			Optional<Movie> movie = movieService.findByUuid(userMovieRelation.getMovieUuid());

			if (movie.isPresent()){
				userMovieDtos.add(UserMovieDto.convertToDto(userMovieRelation, movie));
			}
		}
		return userMovieDtos;
	}

	public List<UserMovieDto> findByUserUuidAndFavoriteByMovieType(UUID uuid, String movieType){
		List<UserMovieDto> userMovieDtos = new ArrayList<>();
		List<UserMovieRelation> userMovieRelationList = this.userMovieRelationRepository.findByUserUuidAndFavoriteAndMovieType(uuid, true, movieType);
		for (UserMovieRelation userMovieRelation : userMovieRelationList) {
			Optional<Movie> movie = movieService.findByUuid(userMovieRelation.getMovieUuid());

			if (movie.isPresent()){
				userMovieDtos.add(UserMovieDto.convertToDto(userMovieRelation, movie));
			}
		}
		return userMovieDtos;
	}

	public List<UserMovieDto> findByUserUuidAndWatchlist(UUID uuid){
		List<UserMovieDto> userMovieDtos = new ArrayList<>();
		List<UserMovieRelation> userMovieRelationList = this.userMovieRelationRepository.findByUserUuidAndWatchlist(uuid, true);
		for (UserMovieRelation userMovieRelation : userMovieRelationList){
			Optional<Movie> movie = movieService.findByUuid(userMovieRelation.getMovieUuid());

			if (movie.isPresent()){
				userMovieDtos.add(UserMovieDto.convertToDto(userMovieRelation, movie));
			}
		}
		return userMovieDtos;
	}

	public List<UserMovieRelation> findByUserUuidAndRating(UUID uuid, int rating){
		return userMovieRelationRepository.findByUserUuidAndRating(uuid, rating);
	}

	public List<UserMovieRelation> findByUserUuidAndIsRated(UUID uuid, boolean rated){
		return userMovieRelationRepository.findByUserUuidAndIsRatedOrderByLastUpdateDesc(uuid, rated);
	}

	public List<UserMovieDto> findByUserUuidAndRated(UUID uuid){
		List<UserMovieDto> userMovieDtos = new ArrayList<>();
		List<UserMovieRelation> userMovieRelationList = this.userMovieRelationRepository.findByUserUuidAndIsRatedOrderByLastUpdateDesc(uuid, true);
		for (UserMovieRelation userMovieRelation : userMovieRelationList) {
			Optional<Movie> movie = movieService.findByUuid(userMovieRelation.getMovieUuid());

			if (movie.isPresent()){
				userMovieDtos.add(UserMovieDto.convertToDto(userMovieRelation, movie));
			}
		}
		return userMovieDtos;
	}

}
