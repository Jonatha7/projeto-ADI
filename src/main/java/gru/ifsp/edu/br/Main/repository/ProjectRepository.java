package gru.ifsp.edu.br.Main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gru.ifsp.edu.br.Main.models.Project;

public interface ProjectRepository extends JpaRepository <Project, Integer>{
	
}
