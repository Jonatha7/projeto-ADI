package gru.ifsp.edu.br.Main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="project")
@JsonDeserialize(as = Project.class)
public class Project implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Column(name="id")
	@Id
	private int id;
	private String name;
	private String login;
	private String description;
	private String html_url;
	private String language;
}
