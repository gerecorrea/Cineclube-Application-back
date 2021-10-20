package br.com.cineclube.controller;

import br.com.cineclube.entity.MoviePersonRelation;
import br.com.cineclube.service.MoviePersonRelationService;
import br.com.cineclube.util.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/moviePersonRelation")
public class MoviePersonRelationController {

	private static final String endPointCollection = "/moviePersonRelation";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	MoviePersonRelationService moviePersonRelationService;

	@PostMapping
	public MoviePersonRelation create(@RequestBody MoviePersonRelation moviePersonRelation){

		MoviePersonRelation response = new MoviePersonRelation();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection));

		try {
			response = moviePersonRelationService.create(moviePersonRelation);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<MoviePersonRelation> findByUuid(final @PathVariable("uuid") UUID uuid){

		Optional<MoviePersonRelation> response = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findByUuid/{uuid}"));

		try {
			response = moviePersonRelationService.findByUuid(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByMovieUuid/{uuid}")
	public List<MoviePersonRelation> findByMovieUuid(final @PathVariable("uuid") UUID uuid){

		List<MoviePersonRelation> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findByMovieUuid/{uuid}"));

		try {
			response = moviePersonRelationService.findByMovieUuid(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByPersonUuid/{uuid}")
	public List<MoviePersonRelation> findByPersonUuid(final @PathVariable("uuid") UUID uuid){

		List<MoviePersonRelation> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findByPersonUuid/{uuid}"));

		try {
			response = moviePersonRelationService.findByPersonUuid(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByMovieUuidAndJob")
	public List<MoviePersonRelation> findByMovieUuidAndJob(
			final @RequestParam("uuid") UUID uuid,
			final @RequestParam("job") String job){

		List<MoviePersonRelation> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findByMovieUuidAndJob"));

		try {
			response = moviePersonRelationService.findByMovieUuidAndJob(uuid, job);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	public List<MoviePersonRelation> findByPersonUuidAndJob(final @PathVariable("uuid") UUID uuid, final @RequestParam("job") String job){

		List<MoviePersonRelation> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, ""));

		try {
			response = moviePersonRelationService.findByPersonUuidAndJob(uuid, job);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	public List<MoviePersonRelation> findByJob(final @RequestParam("job") String job){

		List<MoviePersonRelation> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, ""));

		try {
			response = moviePersonRelationService.findByJob(job);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}
}
