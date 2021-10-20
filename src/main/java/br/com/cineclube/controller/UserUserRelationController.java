package br.com.cineclube.controller;

import br.com.cineclube.entity.UserPersonRelation;
import br.com.cineclube.entity.UserUserRelation;
import br.com.cineclube.service.UserUserRelationService;
import br.com.cineclube.util.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/userUserRelation")
public class UserUserRelationController extends LoggedInController {

	private static final String endPointCollection = "/user";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	UserUserRelationService userUserRelationService;

	@PostMapping
	public UserUserRelation create(@RequestBody UserUserRelation userUserRelation){

		UserUserRelation response = new UserUserRelation();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection));

		try {
			response = userUserRelationService.create(userUserRelation);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@DeleteMapping("/{follower}/{followed}")
	public void remove(final @PathVariable("follower") UUID follower, final @PathVariable("followed") UUID followed) {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/{follower}/{followed}"));

		try {
			userUserRelationService.deleteByUuid(follower, followed);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));
	}

	@DeleteMapping("/removeByRelation")
	public void removeByRelation(final @RequestBody UserUserRelation user) {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/removeByRelation"));

		try {
			userUserRelationService.deleteByEntity(user);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));
	}

	@GetMapping("/listFollowersByFollowed/{uuid}")
	public List<UserUserRelation> findFollowersByFollowed(final @PathVariable("uuid") UUID uuid){

		List<UserUserRelation> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/listFollowersByFollowed/{uuid}"));

		try {
			response = userUserRelationService.findByFollowedUuid(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/listFollowedsByFollower/{uuid}")
	public List<UserUserRelation> findFollowedsByFollower(final @PathVariable("uuid") UUID uuid){

		List<UserUserRelation> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/listFollowedByFollower/{uuid}"));

		try {
			response = userUserRelationService.findByFollowerUuid(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/listFollowerFollowed/{follower}/{followed}")
	public List<UserUserRelation> findFollowersByFollowedUuid(final @PathVariable("follower") UUID follower, final @PathVariable("followed") UUID followed){

		List<UserUserRelation> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection,
				"/listFollowerFollowed/{follower}/{followed}"));

		try {
			response = userUserRelationService.findByFollowerUuidAndFollowedUuid(follower, followed);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

}