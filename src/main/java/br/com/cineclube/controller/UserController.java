package br.com.cineclube.controller;

import br.com.cineclube.entity.User;
import br.com.cineclube.service.UserService;
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
@RequestMapping("/user")
public class UserController extends LoggedInController {

	private static final String endPointCollection = "/user";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@PostMapping
	public User create(@RequestBody User user){

		User response = new User();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection));

		try {
			response = userService.create(user);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@PostMapping("/register")
	public User createUnauthenticated(@RequestBody User user){

		User response = new User();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection));

		try {
			response = userService.create(user);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@PutMapping("/{uuid}")
	public User update(final @PathVariable("uuid") UUID uuid, @RequestBody User user) {

		User response = new User();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("PUT", endPointCollection));

		try {
		response = userService.update(uuid, user);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@DeleteMapping("/{uuid}")
	public void remove(final @PathVariable("uuid") UUID uuid) {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("PUT", endPointCollection));

		try {
			userService.remove(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));
	}

	@PutMapping("/activateInactivate/{uuid}")
	public void activateUser(final @PathVariable("uuid") UUID uuid) {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("PUT", endPointCollection, "/activateInactive/{uuid}"));

		try {
			userService.activateInactivateUser(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));
	}

	@PutMapping("/admin/{uuid}")
	public void changeAdmin(final @PathVariable("uuid") UUID uuid) {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("PUT", endPointCollection, "/admin/{uuid}"));

		try {
			userService.changeAdmin(uuid);
		}catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));
	}

	@GetMapping("/login/{uuid}")
	public Optional<User> findUserByLoginUuid(final @PathVariable("uuid") UUID uuid){

		Optional<User> response = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, "/login/{uuid}"));

		try {
			response = userService.findUserByLoginUuid(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<User> findByUuid(final @PathVariable("uuid") UUID uuid){

		Optional<User> response = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, "/findByUuid/{uuid}"));

		try {
			response = userService.findById(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@GetMapping("/findAll")
	public List<User> findAll() {

		List<User> response = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, "/findAll"));

		try {
			return userService.findAll();
		}catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

}