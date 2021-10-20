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

		Person response = new Person();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection));

		try {
			response = personService.create(person);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@PutMapping("/{uuid}")
	public Person update(final @PathVariable("uuid") UUID uuid, @RequestBody Person person) {

		Person response = new Person();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, "/{uuid}"));

		try {
			response = personService.update(uuid, person);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return response;
	}

	@DeleteMapping("/{uuid}")
	public void remove(final @PathVariable("uuid") UUID uuid) {

		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection, "/{uuid}"));

		try {
			personService.remove(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));
	}

	@GetMapping("/findAll")
	public List<Person> findAll(){

		List<Person> personList = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findTopArtists"));

		try {
		personList = personService.findAll();
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return personList;
	}

	@GetMapping("/findByUuid/{uuid}")
	public Optional<Person> findByUuid(final @PathVariable("uuid") UUID uuid){

		Optional<Person> optionalPerson = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findTopArtists"));

		try {
			return personService.findById(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return optionalPerson;
	}

	@GetMapping("/findById/{uuid}")
	public Optional<Person> findById(final @PathVariable("uuid") UUID uuid){

		Optional<Person> optionalPerson = Optional.empty();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findTopArtists"));

		try {
			optionalPerson = personService.findById(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return optionalPerson;
	}

	@GetMapping("/findTopArtists")
	public List<Person> findTopArtists() {

		List<Person> personList = new ArrayList<>();
		long startTime = System.currentTimeMillis();
		logger.info(LoggerUtils.notificationEndpointRequested("GET", endPointCollection,
				"/findTopArtists"));

		try {
			personList = personService.findTopArtists();

		} catch (Exception e) {
			logger.error(e.getMessage() + LoggerUtils.printStackTrace(e));
		}

		logger.debug(LoggerUtils.calculateExecutionTime(startTime));

		return personList;
	}
}
