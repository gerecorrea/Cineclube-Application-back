package br.com.cineclube.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "user_person_relation", uniqueConstraints = { @UniqueConstraint(columnNames = { "uuid" }) })
@AllArgsConstructor
@NoArgsConstructor
public class UserPersonRelation {

	@Id
	@GeneratedValue(generator = "system-uuid", strategy = GenerationType.AUTO)
	@Column(columnDefinition = "uuid", nullable = false)
	private UUID uuid;

	private UUID personUuid;
	private UUID userUuid;
	private String personName;
	private String userName;

	@Column(length = 1024)
	private String imageLink;

	@Column(columnDefinition = "default false")
	private boolean favorite;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "createdDate")
	private Timestamp createdDate;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	@Column(name = "lastUpdate")
	private Timestamp lastUpdate;

}
