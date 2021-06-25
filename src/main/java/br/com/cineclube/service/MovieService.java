package br.com.cineclube.service;

import br.com.cineclube.entity.Movie;
import br.com.cineclube.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	public Movie create(Movie movie) {
		return movieRepository.save( movie );
	}

	public Movie  update(UUID uuid, Movie movie) {
		movie.setUuid( uuid );
		return movieRepository.save( movie );
	}

	public void remove(UUID uuid) {
		Optional<Movie> movie = movieRepository.findById( uuid );
		movie.ifPresent(value -> movieRepository.delete(value));
	}

	public List<Movie> findAll(){
		return movieRepository.findAll();
	}

	public Optional<Movie> findByUuid(UUID uuid){
		return movieRepository.findByUuid(uuid);
	}

}
