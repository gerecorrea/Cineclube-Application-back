package br.com.cineclube.service;

import br.com.cineclube.entity.UserUserRelation;
import br.com.cineclube.repository.UserUserRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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

	@Modifying
	public void deleteByUuid(UUID follower, UUID followed){
		List<UserUserRelation> userUserRelation = userUserRelationRepository.findByFollowerUuidAndFollowedUuid(follower, followed);

		if (userUserRelation.size() > 0){
			userUserRelationRepository.deleteAll(userUserRelation);
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
