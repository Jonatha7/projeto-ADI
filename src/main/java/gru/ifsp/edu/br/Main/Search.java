package gru.ifsp.edu.br.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class Search {
	
	public Project[] doGet(Project[] projects, String text) throws Exception {
		String txt = "", response = "";
		String baseUrl = "https://api.github.com/search/repositories?q=" + text + "&per_page=3&sort:star"; 
		try {
			URL url = new URL(baseUrl);
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    if (connection.getResponseCode() != 200)
				throw new RuntimeException("HTTP error code : " + connection.getResponseCode());
			BufferedReader buffereReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			while ((response = buffereReader.readLine()) != null) {
				txt += response;
	        }	
		} catch (Exception e) {
			throw new Exception("ERRO: " + e);
		}
		
		JSONObject repositories = new JSONObject(txt);
		JSONArray arrItems = repositories.getJSONArray("items");
		
		for(int i = 0; i < arrItems.length(); i++) {
			JSONObject items = arrItems.getJSONObject(i);
			JSONObject arrAvatar = items.getJSONObject("owner");
			
			projects[i] = new Project();
			projects[i].setId(items.getInt("id"));
			projects[i].setName(items.getString("name"));
			projects[i].setAvatar_url(arrAvatar.getString("avatar_url"));
			projects[i].setDescription(items.getString("description"));
			projects[i].setHtml_url(items.getString("html_url"));
			projects[i].setLanguage(items.getString("language"));
		}
		return projects;
	}
}
