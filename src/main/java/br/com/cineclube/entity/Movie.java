package br.com.cineclube.entity;

import br.com.cineclube.enums.GenreTypes;
import br.com.cineclube.enums.MovieType;
import br.com.cineclube.enums.StreamingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "movie", uniqueConstraints = { @UniqueConstraint(columnNames = { "uuid" }) })
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(generator = "system-uuid", strategy = GenerationType.AUTO)
	@Column(columnDefinition = "uuid", nullable = false)
	private UUID uuid;

	@Column(name = "title", nullable = false)
	private String title;

	private Integer year;

	@Column(columnDefinition = "boolean default false")
	private Boolean isReleased;

	@Column(name = "synopsis", length = 1000)
	private String synopsis;

	@ElementCollection
	private List<String> genres  = new ArrayList<>();

	//Formato dos gêneros por string com separação por comma virando vetor
	private String genresByComma;

	private Integer runtime;

	private String language;

	private String country;

	private MovieType type;

	// Tentativa com uma tabela intermediária de conexão - maneira boa para relacionar os filmes de cada pessoa, watchlist, etc?
//	@ManyToMany(mappedBy = "Movie")
//	private List<Person> persons = new ArrayList<>();

	@ElementCollection
	private List<Person> personsList = new ArrayList<>();

	// Lista de Person de uuid por comma para directors, actors, writers e producers;
	private String directors;

	@Column(length = 2048)
	private String actors;

	private String writers;

	private String producers;

//	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
//	@JoinTable(name = "movie_directors", joinColumns = { @JoinColumn(name = "movie_uuid") }, inverseJoinColumns = { @JoinColumn(name = "person_uuid") })
//	private List<Person> directors;

	@Column(nullable = false)
	private BigDecimal avgRating;

	@Column(columnDefinition = "numeric default 0")
	private int numVotes;

	//Deve virar uma entidade própria dps, com uuid, nome, link para clique, etc. Aí aqui uma lista disso.
	//private List<StreamingType> streamingChannels;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "createdDate")
	private Timestamp createdDate;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "lastUpdate")
	private Timestamp lastUpdate;
}
