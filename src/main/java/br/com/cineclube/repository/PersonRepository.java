package br.com.cineclube.repository;

import br.com.cineclube.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

	List<Person> findAll();
}
