package br.com.cineclube.dto;

import br.com.cineclube.entity.Person;
import br.com.cineclube.entity.UserPersonRelation;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class UserPersonDto {

	private UUID uuid;

	private Person person;

	private boolean favorite;

	public static UserPersonDto convertToDto(UserPersonRelation userPersonRelation, Optional<Person> person) {
		UserPersonDto userPersonDto = new UserPersonDto();

		if (userPersonRelation.getUuid() != null)
			userPersonRelation.setUuid(userPersonRelation.getUuid());

		if(person != null && person.isPresent())
			userPersonDto.setPerson(person.get());


		if (userPersonRelation.isFavorite())
			userPersonDto.setFavorite(true);

		return userPersonDto;
	}
}
