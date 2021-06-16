package br.com.cineclube.controller;

import br.com.cineclube.entity.Movie;
import br.com.cineclube.entity.Person;
import br.com.cineclube.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/findAll")
	public List<Person> findAll(){

		return personService.findAll();
	}

}
