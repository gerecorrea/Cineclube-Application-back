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

@RestController
@RequestMapping("/movie")
public class MovieController {

	private static final String endPointCollection = "/movieController";

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	MovieService movieService;

	@GetMapping("/findAll")
	public List<Movie> findAll(){
		return movieService.findAll();
	}
}