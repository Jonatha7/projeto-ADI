package gru.ifsp.edu.br.Main.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gru.ifsp.edu.br.Main.models.Project;
import gru.ifsp.edu.br.Main.repository.ProjectRepository;
import gru.ifsp.edu.br.Main.services.Search;

@Controller
@RequestMapping("project")
public class ProjectController {
	
	private final ProjectRepository repository;
	
	public ProjectController(ProjectRepository repository) {
		this.repository = repository;
	}
	
	@Autowired
	private Search search;
	
	@GetMapping("/home")
	public ModelAndView teste() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	
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
