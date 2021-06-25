package br.com.cineclube.controller;

import br.com.cineclube.entity.Movie;
import br.com.cineclube.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final String endPointCollection = "/movieController";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	MovieService movieService;

	@PostMapping
	public Movie create(@RequestBody Movie movie){
		return movieService.create(movie);
	}

	@PutMapping("/{uuid}")
	public Movie update(final @PathVariable("uuid") UUID uuid, @RequestBody Movie movie) {
		return  movieService.update(uuid, movie);
	}

	@DeleteMapping("/{uuid}")
	public void remove(final @PathVariable("uuid") UUID uuid) {
		movieService.remove(uuid);
	}

	@GetMapping("/findAll")
	public List<Movie> findAll(){
		return movieService.findAll();
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<Movie> findByUuid(final @PathVariable("uuid") UUID uuid){
		return movieService.findByUuid(uuid);
	}
}
