package br.com.cineclube.service;

import br.com.cineclube.entity.UserUserRelation;
import br.com.cineclube.repository.UserUserRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserUserRelationService {

	@Autowired
	UserUserRelationRepository userUserRelationRepository;

	@Autowired
	MovieService movieService;

	public UserUserRelation create(UserUserRelation userUserRelation){
		return userUserRelationRepository.save(userUserRelation);
	}

	public void deleteByUuid(UUID uuid){
		Optional<UserUserRelation> userUserRelation = userUserRelationRepository.findById(uuid);

		if (userUserRelation.isPresent()){
			userUserRelationRepository.delete(userUserRelation.get());
		}
	}

	public void deleteByEntity(UserUserRelation userUserRelation){
		userUserRelationRepository.delete(userUserRelation);
	}


	public List<UserUserRelation> findByFollowerUuid(UUID uuid){
		return userUserRelationRepository.findByFollowerUuid(uuid);
	}

	public List<UserUserRelation> findByFollowedUuid(UUID uuid){
		return userUserRelationRepository.findByFollowedUuid(uuid);
	}

	public List<UserUserRelation> findByFollowerUuidAndFollowedUuid(UUID follower, UUID followed){
		return userUserRelationRepository.findByFollowerUuidAndFollowedUuid(follower, followed);
	}

}
