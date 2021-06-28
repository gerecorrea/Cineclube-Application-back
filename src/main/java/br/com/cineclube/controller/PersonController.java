package br.com.cineclube.controller;

import br.com.cineclube.entity.Movie;
import br.com.cineclube.entity.Person;
import br.com.cineclube.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;

	@PostMapping
	public Person create(@RequestBody Person person){
		return personService.create(person);
	}

	@PutMapping("/{uuid}")
	public Person update(final @PathVariable("uuid") UUID uuid, @RequestBody Person person) {
		return personService.update(uuid, person);
	}

	@DeleteMapping("/{uuid}")
	public void remove(final @PathVariable("uuid") UUID uuid) {
		personService.remove(uuid);
	}

	@GetMapping("/findAll")
	public List<Person> findAll(){
		return personService.findAll();
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<Person> findByUuid(final @PathVariable("uuid") UUID uuid){
		return personService.findByUuid(uuid);
	}

}
