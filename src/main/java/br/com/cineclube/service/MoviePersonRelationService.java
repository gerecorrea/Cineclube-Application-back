package br.com.cineclube.service;

import br.com.cineclube.entity.MoviePersonRelation;
import br.com.cineclube.repository.MoviePersonRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoviePersonRelationService {

	@Autowired
	MoviePersonRelationRepository moviePersonRelationRepository;

	public MoviePersonRelation create(MoviePersonRelation moviePersonRelation){
		return moviePersonRelationRepository.save(moviePersonRelation);
	}

	public Optional<MoviePersonRelation> findByUuid(UUID uuid){
		return moviePersonRelationRepository.findByUuid(uuid);
	}

	public List<MoviePersonRelation> findByMovieUuid(UUID uuid){
		return moviePersonRelationRepository.findByMovieUuid(uuid);
	}

	public List<MoviePersonRelation> findByPersonUuid(UUID uuid){
		return moviePersonRelationRepository.findByPersonUuid(uuid);
	}

	public List<MoviePersonRelation> findByMovieUuidAndJob(UUID uuid, String job){
		return moviePersonRelationRepository.findByMovieUuidAndJobContainingIgnoreCase(uuid, job);
	}

	public List<MoviePersonRelation> findByPersonUuidAndJob(UUID uuid, String job){
		return moviePersonRelationRepository.findByPersonUuidAndJobContainingIgnoreCase(uuid, job);
	}

	public List<MoviePersonRelation> findByJob(String job){
		return moviePersonRelationRepository.findByJob(job);
	}

	public void deleteByMovieUuid(UUID uuid){
		moviePersonRelationRepository.deleteByMovieUuid(uuid);
	}


}
