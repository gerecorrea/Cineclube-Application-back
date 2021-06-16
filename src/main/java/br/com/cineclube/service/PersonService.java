package br.com.cineclube.service;

import br.com.cineclube.entity.Person;
import br.com.cineclube.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public List<Person> findAll(){
		return personRepository.findAll();
	}
}
