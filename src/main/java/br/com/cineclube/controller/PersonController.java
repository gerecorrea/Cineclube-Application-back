package br.com.cineclube.controller;

import br.com.cineclube.entity.Person;
import br.com.cineclube.service.PersonService;
import br.com.cineclube.util.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {

	private static final String endPointCollection = "/person";

	private final Logger logger = LogManager.getLogger(this.getClass());

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
		return personService.findById(uuid);
	}

	@GetMapping("/findById/{uuid}")
	public Optional<Person> findById(final @PathVariable("uuid") UUID uuid){
		return personService.findById(uuid);
	}

	@GetMapping("/findTopArtists")
	public List<Person> findTopArtists() {
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findTopArtists"));

		List<Person> personList = new ArrayList<>();
		try {
			personList = personService.findTopArtists();

		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return personList;
	}
}
