package gru.ifsp.edu.br.Main;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class Search {
	
	public Project[] repositories() {
		RestTemplate restTemplate = new RestTemplate();
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		restTemplate = restTemplateBuilder.build();
		
		Project[] responseEntity = restTemplate.getForObject("https://api.github.com/search/repositories?q=laravel", Project[].class);
		return responseEntity;
	}
}
