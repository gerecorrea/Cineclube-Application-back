package br.com.cineclube.controller;

import br.com.cineclube.dto.UserMovieDto;
import br.com.cineclube.entity.UserMovieRelation;
import br.com.cineclube.service.UserMovieRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

	@PutMapping("/changeFavorite/{uuid}/{rating}")
	public ResponseEntity<Boolean> changeRating(final @PathVariable("uuid") UUID uuid, final @PathVariable("rating") int rating){
		ResponseEntity<Boolean> response = ResponseEntity.notFound().build();

		try {
			response = ResponseEntity.ok(userMovieRelationService.changeRating(uuid, rating));
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}
		return response;
	}

	@PutMapping("/changeFavorite/{uuid}")
	public ResponseEntity<Boolean> changeFavorite(final @PathVariable("uuid") UUID uuid){
		ResponseEntity<Boolean> response = ResponseEntity.notFound().build();

		try {
			response = ResponseEntity.ok(userMovieRelationService.changeFavorite(uuid));
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}
		return response;
	}

	@PutMapping("/changeWatchlist/{uuid}")
	public ResponseEntity<Boolean> changeWatchlist(final @PathVariable("uuid") UUID uuid){
		ResponseEntity<Boolean> response = ResponseEntity.notFound().build();

		try {
			response = ResponseEntity.ok(userMovieRelationService.changeWatchlist(uuid));
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}
		return response;
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

	@GetMapping("/findFavorites/{uuid}")
	public ResponseEntity<List<UserMovieDto>> findFavorites(final @PathVariable("uuid") UUID uuid){
		ResponseEntity<List<UserMovieDto>> response = ResponseEntity.notFound().build();
		List<UserMovieDto> userMovieRelationList = null;

		try {
			userMovieRelationList = userMovieRelationService.findByUserUuidAndFavorite(uuid);
			if (userMovieRelationList != null) {
				response = ResponseEntity.ok(userMovieRelationList);
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		return response;
	}

	@GetMapping("/findWatchlist/{uuid}")
	public ResponseEntity<List<UserMovieDto>> findWatchlist(final @PathVariable("uuid") UUID uuid){
		ResponseEntity<List<UserMovieDto>> response = ResponseEntity.notFound().build();
		List<UserMovieDto> userMovieRelationList = null;

		try {
			userMovieRelationList = userMovieRelationService.findByUserUuidAndWatchlist(uuid);
			if (userMovieRelationList != null) {
				response = ResponseEntity.ok(userMovieRelationList);
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		return response;
	}
}
