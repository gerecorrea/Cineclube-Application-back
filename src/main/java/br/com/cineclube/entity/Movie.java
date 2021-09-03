package br.com.cineclube.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	@JsonFormat(timezone = "America/Sao_Paulo")
	private Timestamp dateReleased;

	@Column(columnDefinition = "boolean default false")
	private Boolean isReleased;

	@Column(name = "synopsis", length = 1000)
	private String synopsis;

	//@ElementCollection
	//private List<String> genres  = new ArrayList<>();

	//Formato dos gêneros por string com separação por comma virando vetor, salvando string por escrita mesmo.
	//Valores possíveis em GenreTypes, naquela formato
	private String genres;

	private ArrayList<String> genresArray = new ArrayList<>();

	private Integer runtime;

	private String language;

	private String country;

	private String movieType;

	@Column(length = 1024)
	private String imageLink;

	@Column(nullable = false)
	private float avgRating;

	@Column(columnDefinition = "numeric default 0")
	private int numVotes;

	@Column(columnDefinition = "numeric default 0")
	private Integer numFavorites;

	// Would turn a entity later, with uuid, name, link to click, etc. And probabily a entity intermediating these two.
	//private List<StreamingType> streamingChannels;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "createdDate")
	private Timestamp createdDate;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "lastUpdate")
	private Timestamp lastUpdate;

	@Transient
	private ArrayList<String> directors = new ArrayList<>();

	@Transient
	private ArrayList<String> actors = new ArrayList<>();

	@Transient
	private ArrayList<String> producers = new ArrayList<>();

	@Transient
	private ArrayList<String> writers = new ArrayList<>();

	@Transient
	private ArrayList<String> selfs = new ArrayList<>();
}
