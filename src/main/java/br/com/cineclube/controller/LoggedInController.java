package br.com.cineclube.controller;

import br.com.cineclube.entity.Login;
import br.com.cineclube.entity.User;
import br.com.cineclube.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public abstract class LoggedInController {

	@Autowired
	UserService userService;

	@Transactional
	public Login getLoggedUser() {

		Login login = (Login) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Optional<User> optionalUser = userService.findUserByLoginUuid(login.getUuid());

		optionalUser.ifPresent(login::setUser);

		return login;
	}
}