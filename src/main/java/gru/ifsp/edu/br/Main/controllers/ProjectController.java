package gru.ifsp.edu.br.Main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView home() {
		ModelAndView mv= new ModelAndView("index");
		return mv;
	}
	
	@PostMapping("**/results")
	public ModelAndView results(@RequestParam("search") String text) throws Exception {
		Project[] projects = new Project[20];
		ModelAndView mv= new ModelAndView("results");
		mv.addObject("projects", search.doGet(projects, text));
		return mv;
	}
	
	
	@GetMapping("/all")
	public List<Project> project() {
		return repository.findAll();
	}
	
	
}
