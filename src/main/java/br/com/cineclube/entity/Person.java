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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "birth")
	private Timestamp birth;

//	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
//	@JoinTable(name = "person_movies", joinColumns = { @JoinColumn(name = "person_uuid") }, inverseJoinColumns = { @JoinColumn(name = "movie_uuid") })
//	private List<Movie> movies;

	// Tabela intermediária de conexão entre filmes e pessoas - maneira boa para relacionar os filmes de cada pessoa, watchlist, etc?
//	@ManyToMany
//	@JoinTable(name="person_movies",
//			joinColumns=@JoinColumn(name="person_uuid"),
//			inverseJoinColumns=@JoinColumn(name="movie_uuid"))
//	private List<Movie> movies = new ArrayList<>();

	@ElementCollection
	private List<Movie> moviesList = new ArrayList<>();

	// Tentativa dos filmes de cada actor/etc com uuid por comma virando vetor:
	@Column(length = 2048)
	private String moviesString;


	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "createdDate")
	private Timestamp createdDate;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "lastUpdate")
	private Timestamp lastUpdate;
}
