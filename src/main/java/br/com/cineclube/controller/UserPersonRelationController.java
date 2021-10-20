package br.com.cineclube.controller;

import br.com.cineclube.dto.UserPersonDto;
import br.com.cineclube.entity.UserPersonRelation;
import br.com.cineclube.service.UserPersonRelationService;
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
@RequestMapping("/userPerson")
public class UserPersonRelationController {

	private static final String endPointCollection = "/userPerson";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	UserPersonRelationService userPersonRelationService;

	@PostMapping
	public UserPersonRelation create(@RequestBody UserPersonRelation userPersonRelation){

		UserPersonRelation response = new UserPersonRelation();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection));

		try {
			response = userPersonRelationService.create(userPersonRelation);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@PostMapping("/changeFavorite/{uuid}")
	public ResponseEntity<Boolean> changeFavorite(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<Boolean> response = ResponseEntity.notFound().build();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/changeFavorite/{uuid}"));

		try {
			response = ResponseEntity.ok(userPersonRelationService.changeFavorite(uuid));
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<UserPersonRelation> findByUuid(final @PathVariable("uuid") UUID uuid){

		Optional<UserPersonRelation> response = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/findByUuid/{uuid}"));

		try {
			response = userPersonRelationService.findByUuid(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByUserAndPerson/{userUuid}/{personUuid}")
	public Optional<UserPersonRelation> findByUserUuidAndPersonUuid(
			final @PathVariable("userUuid") UUID userUuid, final @PathVariable("personUuid") UUID personUuid){

		Optional<UserPersonRelation> response = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/findByUserAndPerson/{userUuid}/{personUuid}"));

		try {
			response = userPersonRelationService.findByUserUuidAndPersonUuid(userUuid, personUuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByUuid/ResponseEntity/{uuid}")
	public ResponseEntity<UserPersonRelation> findByUuidResponseEntity(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<UserPersonRelation> response = ResponseEntity.notFound().build();
		Optional<UserPersonRelation> optionalUserPersonRelation = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/findByUuid/ResponseEntity/{uuid}"));

		try {
			optionalUserPersonRelation = userPersonRelationService.findByUuid(uuid);
			if (optionalUserPersonRelation.isPresent()) {
				response = ResponseEntity.ok(optionalUserPersonRelation.get());
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findFavorites/{uuid}")
	public ResponseEntity<List<UserPersonDto>> findFavorites(final @PathVariable("uuid") UUID uuid){

		ResponseEntity<List<UserPersonDto>> response = ResponseEntity.notFound().build();
		List<UserPersonDto> userPersonRelationList = null;
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/findFavorites/{uuid}"));

		try {
			userPersonRelationList = userPersonRelationService.findByUserUuidAndFavorite(uuid);
			if (userPersonRelationList != null) {
				response = ResponseEntity.ok(userPersonRelationList);
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

}
