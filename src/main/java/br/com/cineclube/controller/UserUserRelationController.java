package br.com.cineclube.controller;

import br.com.cineclube.entity.UserUserRelation;
import br.com.cineclube.service.UserUserRelationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
		return userUserRelationService.create(userUserRelation);
	}

	@DeleteMapping("/{uuid}")
	public void remove(final @PathVariable("uuid") UUID uuid) {
		userUserRelationService.deleteByUuid(uuid);
	}

	@DeleteMapping("/removeByRelation")
	public void removeByRelation(final @RequestBody UserUserRelation user) {
		userUserRelationService.deleteByEntity(user);
	}

	@GetMapping("/listFollowersByFollowed/{uuid}")
	public List<UserUserRelation> findFollowersByFollowed(final @PathVariable("uuid") UUID uuid){
			return userUserRelationService.findByFollowedUuid(uuid);
	}

	@GetMapping("/listFollowedsByFollower/{uuid}")
	public List<UserUserRelation> findFollowedsByFollower(final @PathVariable("uuid") UUID uuid){
		return userUserRelationService.findByFollowerUuid(uuid);
	}

	@GetMapping("/listFollowerFollowed/{follower}/{followed}")
	public List<UserUserRelation> findFollowersByFollowedUuid(final @PathVariable("follower") UUID follower, final @PathVariable("followed") UUID followed){
		return userUserRelationService.findByFollowerUuidAndFollowedUuid(follower, followed);
	}

}