package gru.ifsp.edu.br.Main;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
public class ProjectResource {
	
	private final ProjectRepository repository;
	
	public ProjectResource(ProjectRepository repository) {
		this.repository = repository;
	}
	
	@Autowired
	private Search search;
	
	
	@GetMapping("/all")
	public List<Project> project() {
		return repository.findAll();
	}
	
	@GetMapping("/search/{text}")
	public Project[] Find(@PathVariable("text") String text) throws Exception {
		Project[] projects = new Project[3];
		projects = search.doGet(projects, text);
		return projects;
	}
}
