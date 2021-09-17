package br.com.cineclube.service;

import br.com.cineclube.dto.UserPersonDto;
import br.com.cineclube.entity.Person;
import br.com.cineclube.entity.UserPersonRelation;
import br.com.cineclube.repository.UserPersonRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPersonRelationService {

	@Autowired
	UserPersonRelationRepository userPersonRelationRepository;

	@Autowired
	PersonService personService;

	public boolean changeFavorite(UUID uuid) {
		UserPersonRelation userPersonRelation = userPersonRelationRepository.findByUuid(uuid);
		Optional<Person> person = personService.findByUuid(userPersonRelation.getPersonUuid());
		userPersonRelation.setFavorite(!userPersonRelation.isFavorite());
		if (person.isPresent()) {
			if (userPersonRelation.isFavorite()) person.get().setNumFavorites(person.get().getNumFavorites() + 1);
			else person.get().setNumFavorites(person.get().getNumFavorites() - 1);
		}
		userPersonRelation = userPersonRelationRepository.save(userPersonRelation);
		return userPersonRelation.getClass() != null;
	}

	public void delete(UserPersonRelation userPersonRelation){
		userPersonRelationRepository.delete(userPersonRelation);
	}

	public Optional<UserPersonRelation> findByUuid(UUID uuid){
		return userPersonRelationRepository.findById(uuid);
	}

	public Optional<UserPersonRelation> findByUserUuidAndPersonUuid(UUID user, UUID person){
		return userPersonRelationRepository.findByUserUuidAndPersonUuid(user, person);
	}

	public List<UserPersonRelation> findByPersonUuid(UUID uuid){
		return userPersonRelationRepository.findByPersonUuid(uuid);
	}

	public List<UserPersonDto> findByUserUuidAndFavorite(UUID uuid){
		List<UserPersonDto> userPersonDtos = new ArrayList<>();
		List<UserPersonRelation> userPersonRelationList = this.userPersonRelationRepository.findByUserUuidAndFavorite(uuid, true);
		for (UserPersonRelation userPersonRelation : userPersonRelationList) {
			Optional<Person> person = personService.findByUuid(userPersonRelation.getPersonUuid());

			if (person.isPresent()){
				userPersonDtos.add(UserPersonDto.convertToDto(userPersonRelation, person));
			}
		}
		return userPersonDtos;
	}

}
