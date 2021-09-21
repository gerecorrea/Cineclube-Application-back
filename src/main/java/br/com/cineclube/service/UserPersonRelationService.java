package br.com.cineclube.service;

import br.com.cineclube.dto.UserPersonDto;
import br.com.cineclube.entity.Person;
import br.com.cineclube.entity.User;
import br.com.cineclube.entity.UserMovieRelation;
import br.com.cineclube.entity.UserPersonRelation;
import br.com.cineclube.repository.UserPersonRelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserPersonRelationService {

	@Autowired
	UserPersonRelationRepository userPersonRelationRepository;

	@Autowired
	PersonService personService;

	@Autowired
	UserService userService;

	public UserPersonRelation create(UserPersonRelation userPersonRelation) {
		Optional<Person> person = personService.findById(userPersonRelation.getPersonUuid());
		Optional<User> user = userService.findById(userPersonRelation.getUserUuid());

		if (Objects.isNull(userPersonRelation.getUuid())){
			userPersonRelation.setFavorite(true);
			if (person.isPresent()){
				userPersonRelation.setPersonName(person.get().getName());
				userPersonRelation.setImageLink((person.get().getImageLink()));

				if (userPersonRelation.isFavorite()) person.get().setNumFavorites(person.get().getNumFavorites() + 1);
			}
			if (user.isPresent()){
				userPersonRelation.setUserName((user.get().getName()));
			}
		} else {
			if (person.isPresent()) {
				if (userPersonRelation.isFavorite()) person.get().setNumFavorites(person.get().getNumFavorites() + 1);
				else person.get().setNumFavorites(person.get().getNumFavorites() - 1);
			}
		}
		if (person.isPresent())
			personService.create(person.get());

		userPersonRelation = userPersonRelationRepository.save(userPersonRelation);
		return userPersonRelation;
	}

	public boolean changeFavorite(UUID uuid) {
		UserPersonRelation userPersonRelation = userPersonRelationRepository.findByUuid(uuid);
		userPersonRelation.setFavorite(!userPersonRelation.isFavorite());
		userPersonRelationRepository.save(userPersonRelation);
		return true;
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
		//List<UserPersonRelation> userPersonRelationList = this.userPersonRelationRepository.findFavoritesByUserUuid(uuid);
		List<UserPersonRelation> userPersonRelationList = this.userPersonRelationRepository.findByUserUuidAndFavorite(uuid, true);
		for (UserPersonRelation userPersonRelation : userPersonRelationList) {
			Optional<Person> person = personService.findById(userPersonRelation.getPersonUuid());

			if (person.isPresent()){
				userPersonDtos.add(UserPersonDto.convertToDto(userPersonRelation, person));
			}
		}
		return userPersonDtos;
	}

}
