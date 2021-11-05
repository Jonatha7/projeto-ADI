package gru.ifsp.edu.br.Main.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gru.ifsp.edu.br.Main.repository.ProjectRepository;

@RestController
@RequestMapping("json")
public class LanguageController {
	
	private final ProjectRepository repository;
	
	public LanguageController(ProjectRepository repository) {
		this.repository = repository;
	}
	
	@CrossOrigin(origins = "http://localhost:8088")
	@GetMapping("/language")
	public List<Map<String, Integer>> language() throws JsonProcessingException {
		List<String> languages = repository.languagesChart();
		List<Map<String, Integer>> listOfMaps = new ArrayList<Map<String, Integer>>();
		
		for (String language : languages) {
			Map<String, Integer> map  = new HashMap<String, Integer>();
			String[] parts = language.split(",");
			map.put(parts[0], Integer.parseInt(parts[1]));	
			listOfMaps.add(map);
	    }
		return listOfMaps;
	}
}