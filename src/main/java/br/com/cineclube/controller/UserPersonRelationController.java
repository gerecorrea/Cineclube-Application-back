package br.com.cineclube.controller;

import br.com.cineclube.dto.UserPersonDto;
import br.com.cineclube.entity.UserMovieRelation;
import br.com.cineclube.entity.UserPersonRelation;
import br.com.cineclube.service.UserPersonRelationService;
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

	@Autowired
	UserPersonRelationService userPersonRelationService;


	@PostMapping
	public UserPersonRelation create(@RequestBody UserPersonRelation userPersonRelation){
		return userPersonRelationService.create(userPersonRelation);
	}

	@PostMapping("/changeFavorite/{uuid}")
	public ResponseEntity<Boolean> changeFavorite(final @PathVariable("uuid") UUID uuid){
		ResponseEntity<Boolean> response = ResponseEntity.notFound().build();

		try {
			response = ResponseEntity.ok(userPersonRelationService.changeFavorite(uuid));
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}
		return response;
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<UserPersonRelation> findByUuid(final @PathVariable("uuid") UUID uuid){
		return userPersonRelationService.findByUuid(uuid);
	}

	@GetMapping("/findByUserAndPerson/{userUuid}/{personUuid}")
	public Optional<UserPersonRelation> findByUserUuidAndPersonUuid(
			final @PathVariable("userUuid") UUID userUuid, final @PathVariable("personUuid") UUID personUuid){
		return userPersonRelationService.findByUserUuidAndPersonUuid(userUuid, personUuid);
	}

	@GetMapping("/findByUuid/ResponseEntity/{uuid}")
	public ResponseEntity<UserPersonRelation> findByUuidResponseEntity(final @PathVariable("uuid") UUID uuid){
		ResponseEntity<UserPersonRelation> response = ResponseEntity.notFound().build();
		Optional<UserPersonRelation> optionalUserPersonRelation = Optional.empty();

		try {
			optionalUserPersonRelation = userPersonRelationService.findByUuid(uuid);
			if (optionalUserPersonRelation.isPresent()) {
				response = ResponseEntity.ok(optionalUserPersonRelation.get());
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		return response;
	}

	@GetMapping("/findFavorites/{uuid}")
	public ResponseEntity<List<UserPersonDto>> findFavorites(final @PathVariable("uuid") UUID uuid){
		ResponseEntity<List<UserPersonDto>> response = ResponseEntity.notFound().build();
		List<UserPersonDto> userPersonRelationList = null;

		try {
			userPersonRelationList = userPersonRelationService.findByUserUuidAndFavorite(uuid);
			if (userPersonRelationList != null) {
				response = ResponseEntity.ok(userPersonRelationList);
			}
		} catch (Exception e){
			response = ResponseEntity.badRequest().build();
		}

		return response;
	}

}
