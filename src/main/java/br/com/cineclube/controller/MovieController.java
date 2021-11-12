package br.com.cineclube.controller;

import br.com.cineclube.entity.Movie;
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

		Movie response = new Movie();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection));

		try {
			response = movieService.create(movie);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@PutMapping("/{uuid}")
	public Movie update(final @PathVariable("uuid") UUID uuid, @RequestBody Movie movie) {

		Movie response = new Movie();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("PUT", endPointCollection, 
				"/{uuid}"));

		try {
			response = movieService.update(uuid, movie);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@DeleteMapping("/{uuid}")
	public void remove(final @PathVariable("uuid") UUID uuid) {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("DELETE", endPointCollection, 
				"/{uuid}"));

		try {
			movieService.remove(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));
	}

	@GetMapping("/findAllMovies")
	public List<Movie> findAllMovies() {
		List<Movie> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, 
				"/findAllMovies"));

		try {
			return movieService.findAllMovies();
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
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
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, 
				"/findAllByFilter"));

		try {
			movieList = movieService.findAllByFilter(title, country, yearMin, yearMax,
					durationMin, durationMax, numVotesMin, numVotesMax, avgRatingMin, avgRatingMax);
		} catch (Exception e){
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return movieList;
	}

	@GetMapping("/findTopByLimit")
	public List<Movie> findTopByLimitNumber(final @RequestParam("limit") int limit){

		List<Movie> movieList = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, 
				"/findTop10"));

		try {
			movieList = movieService.findTopByLimitNumber(limit);
		} catch (Exception e){
				logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
			}

			logger.debug(LoggerUtils.calculateExecutionTime(startTime));

			return movieList;
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

		Optional<Movie> optionalMovie = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, 
				"/findByUuid/{uuid}"));

		try {
			optionalMovie = movieService.findByUuid(uuid);
		} catch (Exception e){
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return optionalMovie;
	}

	@GetMapping("/findAllDocumentary")
	public List<Movie> findAllDocumentary(){

		List<Movie> movieList = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, 
				"/findAllDocumentary"));

		try {
			movieList = movieService.findAllDocumentary();
		} catch (Exception e){
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return movieList;
	}

	@GetMapping("/findAllShort")
	public List<Movie> findAllShort(){

		List<Movie> movieList = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, 
				"/findAllShort"));

		try {
			movieList = movieService.findAllShort();
		} catch (Exception e){
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return movieList;
	}

	@GetMapping("/findFirst25MoviesByDateReleasedDesc")
	public List<Movie> findAllByDateReleased(){

		List<Movie> movieList = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findAllByDateReleased"));

		try {
			movieList = movieService.findFirst25MoviesByDateReleasedDesc();
		} catch (Exception e){
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return movieList;
	}
}
