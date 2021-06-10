package br.com.cineclube.service;

import br.com.cineclube.config.JwtTokenUtil;
import br.com.cineclube.dto.JwtResponseDto;
import br.com.cineclube.entity.Login;
import br.com.cineclube.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class LoginService implements UserDetailsService {

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	public List<Login> findAll() {
		return this.loginRepository.findAll();
	}

	public Login findByUsername(String username) {
		return this.loginRepository.findByUsername( username );
	}

	public Login save(Login login) {
		return this.loginRepository.save( login );
	}

	public List<Login> saveOrUpdateAll(List<Login> list) {
		return this.loginRepository.saveAll( list );
	}

	public void remove(Login login) {
		this.loginRepository.delete( login );
	}

	public void deleteById(Integer id_login) {
		this.loginRepository.deleteById( id_login );
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUsername( username );
	}

	public Login findByUuid(UUID uuid) {
		return loginRepository.findByUuid(uuid);
	}

	public boolean validateUsername(String userName) {
		return this.loginRepository.existsByUsername(userName);
	}

	public ResponseEntity<?> authenticate(String username, String password, Login login) throws JSONException {
		try {

			if (!login.isActive())
				throw new DisabledException("Usuário bloqueado, entre em contato com a administração ou suporte do PontoCob");

			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( username, password ) );
			final String token = jwtTokenUtil.generateToken( login );

			if (!token.isEmpty()) {
				login.setLastLoginDate(new Timestamp(new Date().getTime()));
				loginRepository.save(login);
			}

			return ResponseEntity.ok( new JwtResponseDto( token ) );
		} catch (DisabledException e) {
			JSONObject obj = new JSONObject();
			obj.put("message", e.getMessage());
			obj.put("hasError", true);
			return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body(obj.toString());
		} catch (BadCredentialsException e) {
			return ResponseEntity.status( HttpStatus.FORBIDDEN ).build();
		}
	}
}