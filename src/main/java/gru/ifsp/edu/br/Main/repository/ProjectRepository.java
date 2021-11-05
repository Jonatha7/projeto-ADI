package gru.ifsp.edu.br.Main.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import gru.ifsp.edu.br.Main.models.Project;

public interface ProjectRepository extends JpaRepository <Project, Integer>{
	
	@Query("SELECT p.id FROM Project p")
	List<Integer> findAllIdsProject();
	
	
	@Query("SELECT p.language, count(language) as qtde FROM Project p GROUP BY p.language")
	List<String> languagesChart();
	
}
