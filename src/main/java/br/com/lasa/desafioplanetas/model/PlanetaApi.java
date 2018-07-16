package br.com.lasa.desafioplanetas.model;





import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaApi  {


private Long count;
private Long next;
private Long previous;

private List<ApiResult> results = new ArrayList<ApiResult>();

public PlanetaApi() {}

public Long getCount() {
	return count;
}

public void setCount(Long count) {
	this.count = count;
}

public Long getNext() {
	return next;
}

public void setNext(Long next) {
	this.next = next;
}

public Long getPrevious() {
	return previous;
}

public void setPrevious(Long previous) {
	this.previous = previous;
}

public List<ApiResult> getResults() {
	return results;
}

public void setResults(List<ApiResult> results) {
	this.results = results;
}



	
    
}
