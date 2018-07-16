package br.com.lasa.desafioplanetas.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.lasa.desafioplanetas.model.Planeta;
import br.com.lasa.desafioplanetas.model.PlanetaApi;
import br.com.lasa.desafioplanetas.repository.PlanetaRepository;
import br.com.lasa.desafioplanetas.repository.filter.PlanetaFilter;
import br.com.lasa.desafioplanetas.service.exception.PlanetaExistenteException;
import br.com.lasa.desafioplanetas.service.exception.PlanetaNaoInexistenteException;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository planetaRepository;

	public Planeta salvar(Planeta planeta) {

		if (planetaCadastrado(planeta) == true) {

			throw new PlanetaExistenteException();
		}

		if (planetaExiteGalaxia(planeta) == false) {

			throw new PlanetaNaoInexistenteException();

		}

		return planetaRepository.save(planeta);

	}
	

	

	public Boolean planetaCadastrado(Planeta planeta) {

		boolean existe = false;
		Planeta buscarPlaneta = planetaRepository.findByNome(planeta.getNome());

		if (buscarPlaneta != null) {

			existe = true;

		}

		return existe;

	}

	public Boolean planetaExiteGalaxia(Planeta planeta) {

		boolean existe = false;

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String url = "https://swapi.co/api/planets/?search=" + planeta.getNome();
		ResponseEntity<PlanetaApi> res = restTemplate.exchange(url, HttpMethod.GET, entity, PlanetaApi.class);

		PlanetaApi planetaApi = res.getBody();

		if (planetaApi.getCount() > 0) {

			for (int i = 0; i < planetaApi.getCount(); i++) {

				if (planetaApi.getResults().get(i).getName().equals(planeta.getNome())) {

					Planeta planetaSalvar = planeta;

					planetaSalvar.setQuantidadeAparicoes(planetaApi.getResults().get(i).getFilms().size());

					planetaRepository.save(planeta);

					existe = true;
				}

			}

		}

		return existe;

	}
}
