package br.com.cineclube.controller;

import br.com.cineclube.entity.User;
import br.com.cineclube.service.UserService;
import br.com.cineclube.util.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
		return userService.create(user);
	}

	@PostMapping("/register")
	public User createUnauthenticated(@RequestBody User user){
		return userService.create(user);
	}

	@PutMapping("/{uuid}")
	public User update(final @PathVariable("uuid") UUID uuid, @RequestBody User user) {
		return  userService.update(uuid, user);
	}

	@DeleteMapping("/{uuid}")
	public void remove(final @PathVariable("uuid") UUID uuid) {
		userService.remove(uuid);
	}

	@PutMapping("/activateInactivate/{uuid}")
	public void activateUser(final @PathVariable("uuid") UUID uuid) {
		userService.activateInactivateUser(uuid);
	}

	@PutMapping("/admin/{uuid}")
	public void changeAdmin(final @PathVariable("uuid") UUID uuid) {
			userService.changeAdmin(uuid);
	}

	@GetMapping("/login/{uuid}")
	public Optional<User> findUserByLoginUuid(final @PathVariable("uuid") UUID uuid){
			return userService.findUserByLoginUuid(uuid);
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<User> findByUuid(final @PathVariable("uuid") UUID uuid){
		return userService.findById(uuid);
	}

	@GetMapping("/findAll")
	public List<User> findAll(){
			return userService.findAll();
	}

}