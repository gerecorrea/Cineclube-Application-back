package br.com.cineclube.repository;

import br.com.cineclube.entity.UserPersonRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserPersonRelationRepository extends JpaRepository<UserPersonRelation, UUID> {

	List<UserPersonRelation> findAll();

	UserPersonRelation findByUuid(UUID uuid);

	Optional<UserPersonRelation> findByUserUuidAndPersonUuid(UUID user, UUID person);

	List<UserPersonRelation> findByPersonUuid(UUID uuid);

	Optional<UserPersonRelation> findByUserUuid(UUID uuid);

	List<UserPersonRelation> findByUserUuidAndFavorite(UUID uuid, boolean favorite);

}
