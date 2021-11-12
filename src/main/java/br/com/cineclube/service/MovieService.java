package br.com.cineclube.service;

import br.com.cineclube.entity.Movie;
import br.com.cineclube.entity.MoviePersonRelation;
import br.com.cineclube.entity.Person;
import br.com.cineclube.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	PersonService personService;

	@Autowired
	MoviePersonRelationService moviePersonRelationService;

	public Movie create(Movie movie) {

		Optional<Movie> movie1 = movieRepository.findByUuid(movie.getUuid());

		// Delete geral dos já cadastrados:
		if (Objects.nonNull(movie.getUuid())){
			List<MoviePersonRelation> moviePersonRelationList = moviePersonRelationService.findByMovieUuid(movie.getUuid());

			if (!moviePersonRelationList.isEmpty()){
				moviePersonRelationService.deleteByMovieUuid(movie.getUuid());
			}
		}

		for(String i : movie.getDirectors()){
			UUID uuidToSearch = UUID.fromString(i);
			Optional<Person> person = personService.findById(uuidToSearch);
			if (person.isPresent()){
				MoviePersonRelation moviePersonRelation = new MoviePersonRelation();
				moviePersonRelation.setMovieUuid(movie.getUuid());
				moviePersonRelation.setPersonUuid(person.get().getUuid());
				moviePersonRelation.setMovieName(movie.getTitle());
				moviePersonRelation.setPersonName(person.get().getName());
				moviePersonRelation.setImageLinkMovie(movie1.get().getImageLink());
				moviePersonRelation.setImageLinkArtist(person.get().getImageLink());
				moviePersonRelation.setJob("DIRECTOR");
				moviePersonRelationService.create(moviePersonRelation);
			}
		}

		for(String i : movie.getActors()){
			UUID uuidToSearch = UUID.fromString(i);
			Optional<Person> person = personService.findById(uuidToSearch);
			if (person.isPresent()){
				MoviePersonRelation moviePersonRelation = new MoviePersonRelation();
				moviePersonRelation.setMovieUuid(movie.getUuid());
				moviePersonRelation.setPersonUuid(person.get().getUuid());
				moviePersonRelation.setMovieName(movie.getTitle());
				moviePersonRelation.setPersonName(person.get().getName());
				moviePersonRelation.setImageLinkMovie(movie1.get().getImageLink());
				moviePersonRelation.setImageLinkArtist(person.get().getImageLink());
				moviePersonRelation.setJob("ACTOR");
				moviePersonRelationService.create(moviePersonRelation);
			}
		}

		for(String i : movie.getProducers()){
			UUID uuidToSearch = UUID.fromString(i);
			Optional<Person> person = personService.findById(uuidToSearch);
			if (person.isPresent()){
				MoviePersonRelation moviePersonRelation = new MoviePersonRelation();
				moviePersonRelation.setMovieUuid(movie.getUuid());
				moviePersonRelation.setPersonUuid(person.get().getUuid());
				moviePersonRelation.setMovieName(movie.getTitle());
				moviePersonRelation.setPersonName(person.get().getName());
				moviePersonRelation.setImageLinkMovie(movie1.get().getImageLink());
				moviePersonRelation.setImageLinkArtist(person.get().getImageLink());
				moviePersonRelation.setJob("PRODUCER");
				moviePersonRelationService.create(moviePersonRelation);
			}
		}

		for(String i : movie.getWriters()){
			UUID uuidToSearch = UUID.fromString(i);
			Optional<Person> person = personService.findById(uuidToSearch);
			if (person.isPresent()){
				MoviePersonRelation moviePersonRelation = new MoviePersonRelation();
				moviePersonRelation.setMovieUuid(movie.getUuid());
				moviePersonRelation.setPersonUuid(person.get().getUuid());
				moviePersonRelation.setMovieName(movie.getTitle());
				moviePersonRelation.setPersonName(person.get().getName());
				moviePersonRelation.setImageLinkMovie(movie1.get().getImageLink());
				moviePersonRelation.setImageLinkArtist(person.get().getImageLink());
				moviePersonRelation.setJob("WRITER");
				moviePersonRelationService.create(moviePersonRelation);
			}
		}

		for(String i : movie.getSelfs()){
			UUID uuidToSearch = UUID.fromString(i);
			Optional<Person> person = personService.findById(uuidToSearch);
			if (person.isPresent()){
				MoviePersonRelation moviePersonRelation = new MoviePersonRelation();
				moviePersonRelation.setMovieUuid(movie.getUuid());
				moviePersonRelation.setPersonUuid(person.get().getUuid());
				moviePersonRelation.setMovieName(movie.getTitle());
				moviePersonRelation.setPersonName(person.get().getName());
				moviePersonRelation.setImageLinkMovie(movie1.get().getImageLink());
				moviePersonRelation.setImageLinkArtist(person.get().getImageLink());
				moviePersonRelation.setJob("SELF");
				moviePersonRelationService.create(moviePersonRelation);
				moviePersonRelationService.create(moviePersonRelation);
			}
		}

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

	public List<Movie> findAllMovies(){
		return movieRepository.findByMovieTypeOrderByTitleAsc("MOVIE");
	}

	public List<Movie> findAllByFilter(String title, String country, int yearMin, int yearMax,
	                                   int durationMin, int durationMax, int numVotesMin, int numVotesMax,
	                                   float avgRatingMin, float avgRatingMax){
		return movieRepository.findByFilters(
				"MOVIE", title, country, yearMin, yearMax,
				durationMin, durationMax, numVotesMin, numVotesMax, avgRatingMin, avgRatingMax);
	}

	public List<Movie> findTopByLimitNumber(int limit){
		return movieRepository.findTopByLimitNumber(limit);
	}

	public List<Movie> findTopFavoriteAll(){
		return movieRepository.findTopFavoriteAll();
	}

	public List<Movie> findTopRatingAll(){
		return movieRepository.findTopRatingAll();
	}


	public Optional<Movie> findByUuid(UUID uuid){
		return movieRepository.findByUuid(uuid);
	}

	public List<Movie> findAllDocumentary(){
		return movieRepository.findByMovieTypeOrderByTitleAsc("DOCUMENTARY");
	}

	public List<Movie> findAllShort(){
		return movieRepository.findByMovieTypeOrderByTitleAsc("SHORT");
	}

	public List<Movie> findFirst25MoviesByDateReleasedDesc() {return movieRepository.findFirst25MoviesByDateReleasedDesc(); }

}
