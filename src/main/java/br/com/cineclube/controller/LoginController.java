package br.com.cineclube.controller;

import br.com.cineclube.dto.JwtRequestDto;
import br.com.cineclube.entity.Login;
import br.com.cineclube.service.LoginService;
import br.com.cineclube.util.LoggerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
@Api(tags = { "Login" })
public class LoginController extends LoggedInController {

	private static final String endPointCollection = "/login";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	LoginService loginService;

	@Transactional
	@ApiOperation(value = "Login para sistema", nickname = "login", notes = "Login para sistema")
	@PostMapping
	public ResponseEntity<?> auth(@RequestBody JwtRequestDto auth) throws JSONException {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("POST", endPointCollection));

		Login login = loginService.findByUsername( auth.getUsername() );
		if (login == null)
			return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).build();

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return loginService.authenticate( auth.getUsername(), auth.getPassword(), login );
	}

	@GetMapping("/loggedUser")
	public Login findLoggedUserByUuid() {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, "/loggedUser"));

		Login login =  getLoggedUser();
		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return login;
	}
}