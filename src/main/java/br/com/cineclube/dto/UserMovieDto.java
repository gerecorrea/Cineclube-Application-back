package br.com.cineclube.dto;

import br.com.cineclube.entity.Movie;
import br.com.cineclube.entity.UserMovieRelation;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class UserMovieDto {

	private UUID uuid;

	private Movie movie;

	private boolean rated;

	private int rating;

	private boolean watchlist;

	private boolean favorite;

	private String movieType;

	public static UserMovieDto convertToDto(UserMovieRelation userMovieRelation, Optional<Movie> movie) {
		UserMovieDto userMovieDto = new UserMovieDto();

		if (userMovieRelation.getUuid() != null)
			userMovieDto.setUuid(userMovieRelation.getUuid());

		if(movie != null && movie.isPresent())
			userMovieDto.setMovie(movie.get());

		if (userMovieRelation.isRated()){
			userMovieDto.setRated(true);
			userMovieDto.setRating(userMovieRelation.getRating());
		}

		if (userMovieRelation.isWatchlist())
			userMovieDto.setWatchlist(true);

		if (userMovieRelation.isFavorite())
			userMovieDto.setFavorite(true);

		if (userMovieRelation.getMovieType() != null)
			userMovieDto.setMovieType(userMovieRelation.getMovieType());

		return userMovieDto;
	}
}
