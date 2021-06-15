package br.com.cineclube.controller;

import br.com.cineclube.entity.User;
import br.com.cineclube.service.UserService;
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

	@GetMapping("/{uuid}")
	public Optional<User> findUserByLoginUuid(final @PathVariable("uuid") UUID uuid){
		return userService.findUserByLoginUuid(uuid);
	}

	@GetMapping("/findAll")
	public List<User> findAll(){
		return userService.findAll();
	}

}