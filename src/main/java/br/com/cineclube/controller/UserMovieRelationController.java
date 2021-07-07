package br.com.cineclube.controller;

import br.com.cineclube.entity.UserMovieRelation;
import br.com.cineclube.service.UserMovieRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/userMovie")
public class UserMovieRelationController {

	private static final String endPointCollection = "/userMovie";

	@Autowired
	UserMovieRelationService userMovieRelationService;

	@PostMapping
	public UserMovieRelation create(@RequestBody UserMovieRelation userMovieRelation){
		return userMovieRelationService.create(userMovieRelation);
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<UserMovieRelation> findByUuid(final @PathVariable("uuid") UUID uuid){
		return userMovieRelationService.findByUuid(uuid);
	}

	@GetMapping("/findByUserAndMovie/{userUuid}/{movieUuid}")
	public Optional<UserMovieRelation> findByUserUuidAndMovieUuid(
			final @PathVariable("userUuid") UUID userUuid, final @PathVariable("movieUuid") UUID movieUuid){
		return userMovieRelationService.findByUserUuidAndMovieUuid(userUuid, movieUuid);
	}

	@GetMapping("/findByUuid/ResponseEntity/{uuid}")
	public ResponseEntity<UserMovieRelation> findByUuidResponseEntity(final @PathVariable("uuid") UUID uuid){
		ResponseEntity<UserMovieRelation> response = ResponseEntity.notFound().build();
		Optional<UserMovieRelation> optionalUserMovieRelation = Optional.empty();

		try {
			optionalUserMovieRelation = userMovieRelationService.findByUuid(uuid);
			if (optionalUserMovieRelation.isPresent()) {
				response = ResponseEntity.ok(optionalUserMovieRelation.get());
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		return response;
	}
}
