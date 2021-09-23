package br.com.cineclube.controller;

import br.com.cineclube.entity.Movie;
import br.com.cineclube.entity.Person;
import br.com.cineclube.service.MovieService;
import br.com.cineclube.util.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final String endPointCollection = "/movie";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	MovieService movieService;

	@PostMapping
	public Movie create(@RequestBody Movie movie){
		return movieService.create(movie);
	}

	@PutMapping("/{uuid}")
	public Movie update(final @PathVariable("uuid") UUID uuid, @RequestBody Movie movie) {
		return  movieService.update(uuid, movie);
	}

	@DeleteMapping("/{uuid}")
	public void remove(final @PathVariable("uuid") UUID uuid) {
		movieService.remove(uuid);
	}

	@GetMapping("/findAllMovies")
	public List<Movie> findAllMovies(){
		return movieService.findAllMovies();
	}

	@GetMapping("/findAllByFilter")
	public List<Movie> findAllByFilter(
			final @RequestParam("title") String title,
			final @RequestParam("country") String country,
			final @RequestParam("yearMin") int yearMin,
			final @RequestParam("yearMax") int yearMax,
			final @RequestParam("durationMin") int durationMin,
			final @RequestParam("durationMax") int durationMax,
			final @RequestParam("numVotesMin") int numVotesMin,
			final @RequestParam("numVotesMax") int numVotesMax,
			final @RequestParam("avgRatingMin") float avgRatingMin,
			final @RequestParam("avgRatingMax") float avgRatingMax
	){
		List<Movie> movieList = new ArrayList<>();
		try {
			movieList = movieService.findAllByFilter(title, country, yearMin, yearMax,
					durationMin, durationMax, numVotesMin, numVotesMax, avgRatingMin, avgRatingMax);
		} catch (Exception e){
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		return movieList;
	}

	@GetMapping("/findTop10")
	public List<Movie> findTop10(){
		return movieService.findTop10();
	}

	@GetMapping("/findTopFavoriteAll")
	public List<Movie> findTopFavoriteAll(){
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findTopFavoriteAll"));

		List<Movie> movieList = new ArrayList<>();
		try {
			movieList = movieService.findTopFavoriteAll();

		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return movieList;
	}

	@GetMapping("/findTopRatingAll")
	public List<Movie> findTopRatingAll(){
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findTopRatingAll"));

		List<Movie> movieList = new ArrayList<>();
		try {
			movieList = movieService.findTopRatingAll();

		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return movieList;
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<Movie> findByUuid(final @PathVariable("uuid") UUID uuid){
		return movieService.findByUuid(uuid);
	}

	@GetMapping("/findAllDocumentary")
	public List<Movie> findAllDocumentary(){
		return movieService.findAllDocumentary();
	}

	@GetMapping("/findAllShort")
	public List<Movie> findAllShort(){
		return movieService.findAllShort();
	}
}
