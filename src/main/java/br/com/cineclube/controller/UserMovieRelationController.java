package br.com.cineclube.controller;

import br.com.cineclube.dto.UserMovieDto;
import br.com.cineclube.entity.UserMovieRelation;
import br.com.cineclube.service.UserMovieRelationService;
import br.com.cineclube.util.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/userMovie")
public class UserMovieRelationController {

	private static final String endPointCollection = "/userMovie";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	UserMovieRelationService userMovieRelationService;

	@PostMapping
	public UserMovieRelation create(@RequestBody UserMovieRelation userMovieRelation){

		UserMovieRelation response = new UserMovieRelation();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection));

		try {
			response = userMovieRelationService.create(userMovieRelation);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@PutMapping("/changeRating/{uuid}/{rating}")
	public ResponseEntity<Boolean> changeRating(final @PathVariable("uuid") UUID uuid, final @PathVariable("rating") int rating){

		ResponseEntity<Boolean> response = ResponseEntity.notFound().build();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("PUT", endPointCollection,
				"/changeRating/{uuid}/{rating}"));

		try {
			response = ResponseEntity.ok(userMovieRelationService.changeRating(uuid, rating));
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@PutMapping("/changeFavorite/{uuid}")
	public ResponseEntity<Boolean> changeFavorite(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<Boolean> response = ResponseEntity.notFound().build();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("PUT", endPointCollection,
				"/changeFavorite/{uuid}/"));

		try {
			response = ResponseEntity.ok(userMovieRelationService.changeFavorite(uuid));
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@PutMapping("/changeWatchlist/{uuid}")
	public ResponseEntity<Boolean> changeWatchlist(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<Boolean> response = ResponseEntity.notFound().build();

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("PUT", endPointCollection,
				"/changeWatchlist/{uuid}"));

		try {
			response = ResponseEntity.ok(userMovieRelationService.changeWatchlist(uuid));
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<UserMovieRelation> findByUuid(final @PathVariable("uuid") UUID uuid){

		Optional<UserMovieRelation> response = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findByUuid/{uuid}"));

		try {
			response = userMovieRelationService.findByUuid(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByUserAndMovie/{userUuid}/{movieUuid}")
	public Optional<UserMovieRelation> findByUserUuidAndMovieUuid(
			final @PathVariable("userUuid") UUID userUuid, final @PathVariable("movieUuid") UUID movieUuid){

		Optional<UserMovieRelation> response = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findByUuid/{uuid}"));

		try {
			response = userMovieRelationService.findByUserUuidAndMovieUuid(userUuid, movieUuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByUuid/ResponseEntity/{uuid}")
	public ResponseEntity<UserMovieRelation> findByUuidResponseEntity(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<UserMovieRelation> response = ResponseEntity.notFound().build();
		Optional<UserMovieRelation> optionalUserMovieRelation = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findByUuid/{uuid}"));

		try {
			optionalUserMovieRelation = userMovieRelationService.findByUuid(uuid);
			if (optionalUserMovieRelation.isPresent()) {
				response = ResponseEntity.ok(optionalUserMovieRelation.get());
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findFavorites/{uuid}")
	public ResponseEntity<List<UserMovieDto>> findFavorites(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<List<UserMovieDto>> response = ResponseEntity.notFound().build();
		List<UserMovieDto> userMovieRelationList = null;
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findFavorites/{uuid}"));


		try {
			userMovieRelationList = userMovieRelationService.findByUserUuidAndFavorite(uuid);
			if (userMovieRelationList != null) {
				response = ResponseEntity.ok(userMovieRelationList);
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findFavoritesByMovieType/{movieType}/{uuid}")
	public ResponseEntity<List<UserMovieDto>> findFavorites(final @PathVariable("movieType") String movieType, final @PathVariable("uuid") UUID uuid){

		ResponseEntity<List<UserMovieDto>> response = ResponseEntity.notFound().build();
		List<UserMovieDto> userMovieRelationList = null;
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findFavoritesByMovieType/{movieType}/{uuid}"));


		try {
			userMovieRelationList = userMovieRelationService.findByUserUuidAndFavoriteByMovieType(uuid, movieType);
			if (userMovieRelationList != null) {
				response = ResponseEntity.ok(userMovieRelationList);
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findWatchlist/{uuid}")
	public ResponseEntity<List<UserMovieDto>> findWatchlist(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<List<UserMovieDto>> response = ResponseEntity.notFound().build();
		List<UserMovieDto> userMovieRelationList = null;
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, "/findWatchlist/{uuid}"));

		try {
			userMovieRelationList = userMovieRelationService.findByUserUuidAndWatchlist(uuid);
			if (userMovieRelationList != null) {
				response = ResponseEntity.ok(userMovieRelationList);
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findRatings/{uuid}")
	public ResponseEntity<List<UserMovieDto>> findRatings(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<List<UserMovieDto>> response = ResponseEntity.notFound().build();
		List<UserMovieDto> userMovieRelationList = null;
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, "/findRatings/{uuid}"));

		try {
			userMovieRelationList = userMovieRelationService.findByUserUuidAndRated(uuid);
			if (userMovieRelationList != null) {
				response = ResponseEntity.ok(userMovieRelationList);
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

}
