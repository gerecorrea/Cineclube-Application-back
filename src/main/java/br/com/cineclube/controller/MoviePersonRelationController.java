package br.com.cineclube.controller;

import br.com.cineclube.entity.MoviePersonRelation;
import br.com.cineclube.service.MoviePersonRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/moviePersonRelation")
public class MoviePersonRelationController {

	@Autowired
	MoviePersonRelationService moviePersonRelationService;

	@PostMapping
	public MoviePersonRelation create(@RequestBody MoviePersonRelation moviePersonRelation){
		return moviePersonRelationService.create(moviePersonRelation);
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<MoviePersonRelation> findByUuid(final @PathVariable("uuid") UUID uuid){
		return moviePersonRelationService.findByUuid(uuid);
	}

	@GetMapping("/findByMovieUuid/{uuid}")
	public List<MoviePersonRelation> findByMovieUuid(final @PathVariable("uuid") UUID uuid){
		return moviePersonRelationService.findByMovieUuid(uuid);
	}

	@GetMapping("/findByPersonUuid/{uuid}")
	public List<MoviePersonRelation> findByPersonUuid(final @PathVariable("uuid") UUID uuid){
		return moviePersonRelationService.findByPersonUuid(uuid);
	}

	@GetMapping("/findByMovieUUidAndJob/{uuid}")
	public List<MoviePersonRelation> findByMovieUuidAndJob(final @PathVariable("uuid") UUID uuid, final @RequestParam("job") String job){
		return moviePersonRelationService.findByMovieUuidAndJob(uuid, job);
	}

	public List<MoviePersonRelation> findByPersonUuidAndJob(final @PathVariable("uuid") UUID uuid, final @RequestParam("job") String job){
		return moviePersonRelationService.findByPersonUuidAndJob(uuid, job);
	}

	public List<MoviePersonRelation> findByJob(final @RequestParam("job") String job){
		return moviePersonRelationService.findByJob(job);
	}
}
