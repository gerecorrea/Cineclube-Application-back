package br.com.cineclube.repository;

import br.com.cineclube.entity.User;
import br.com.cineclube.entity.UserUserRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserUserRelationRepository extends JpaRepository<UserUserRelation, UUID> {

	List<UserUserRelation> findByFollowerUuid(UUID uuid);

	List<UserUserRelation> findByFollowedUuid(UUID uuid);

	List<UserUserRelation> findByFollowerUuidAndFollowedUuid(UUID follower, UUID followed);
}