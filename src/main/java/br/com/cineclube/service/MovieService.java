package br.com.cineclube.service;

import br.com.cineclube.entity.Movie;
import br.com.cineclube.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	public List<Movie> findAll(){
		return movieRepository.findAll();
	}

}
