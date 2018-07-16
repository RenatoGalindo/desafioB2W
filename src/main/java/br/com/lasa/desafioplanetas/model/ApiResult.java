package br.com.lasa.desafioplanetas.model;

import java.util.ArrayList;
import java.util.List;

public class ApiResult {

	

 
	

	private String name;
    
    private List<String> films =new ArrayList<String>();
    

    
    
    public ApiResult() {}
    
    

	




	public List<String> getFilms() {
		return films;
	}



	public void setFilms(List<String> films) {
		this.films = films;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
    
    
    
}
