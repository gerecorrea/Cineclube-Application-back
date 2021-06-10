package br.com.cineclube.repository;

import br.com.cineclube.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

	Login findByUuid(UUID uuid);

	Login findByUsername(String username);

	Boolean existsByUsername(String username);
}