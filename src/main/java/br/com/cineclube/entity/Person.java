package br.com.cineclube.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "person", uniqueConstraints = { @UniqueConstraint(columnNames = { "uuid" }) })
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Id
	@GeneratedValue(generator = "system-uuid", strategy = GenerationType.AUTO)
	@Column(columnDefinition = "uuid", nullable = false)
	private UUID uuid;

	@Column(name = "name", nullable = false)
	private String name;

	private Integer birthYear;

	private String birthDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Timestamp birth;

	private String country;

	@Column(length = 1024)
	private String imageLink;

	// Tabela intermediária de conexão entre filmes e pessoas - maneira boa para relacionar os filmes de cada pessoa, watchlist, etc?
//	@ManyToMany
//	@JoinTable(name="person_movies",
//			joinColumns=@JoinColumn(name="person_uuid"),
//			inverseJoinColumns=@JoinColumn(name="movie_uuid"))
//	private List<Movie> movies = new ArrayList<>();

	private ArrayList<String> jobTypes = new ArrayList<>();

	private String jobRoles;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "createdDate")
	private Timestamp createdDate;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "lastUpdate")
	private Timestamp lastUpdate;
}
