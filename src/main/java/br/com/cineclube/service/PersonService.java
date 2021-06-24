package br.com.cineclube.service;

import br.com.cineclube.entity.Person;
import br.com.cineclube.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public Person create(Person person) {
		return personRepository.save( person );
	}

	public Person update(UUID uuid, Person person) {
		person.setUuid( uuid );
		return personRepository.save( person );
	}

	public void remove(UUID uuid) {
		Optional<Person> person = personRepository.findById( uuid );
		person.ifPresent(value -> personRepository.delete(value));
	}

	public List<Person> findAll(){
		return personRepository.findAll();
	}
}
