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

	@JsonFormat(timezone = "America/Sao_Paulo")
	private Timestamp birth;

	private String country;
	private String city;
	private String state;

	@Column(length = 1000)
	private String bio;

	private boolean director;
	private boolean actor;
	private boolean producer;
	private boolean writer;
	private boolean self;

	@Column(length = 1024)
	private String imageLink;

	@Column(columnDefinition = "numeric default 0")
	private Integer numFavorites;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "createdDate")
	private Timestamp createdDate;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "lastUpdate")
	private Timestamp lastUpdate;
}
