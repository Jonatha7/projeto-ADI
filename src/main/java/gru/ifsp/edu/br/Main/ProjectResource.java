package gru.ifsp.edu.br.Main;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projects")
public class ProjectResource {
	
	private final ProjectRepository repository;
	
	public ProjectResource(ProjectRepository repository) {
		this.repository = repository;
	}
	
	
	@GetMapping("/all")
	public List<Project> project() {
		return repository.findAll();
	}

	@GetMapping("/123")
	public @ResponseBody Project[] findAllObjects() {
		Project[] projects;
	    projects = new Search().repositories();
	    return projects;
	}
}
