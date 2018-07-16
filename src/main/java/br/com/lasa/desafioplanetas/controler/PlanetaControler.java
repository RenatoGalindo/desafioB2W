package br.com.lasa.desafioplanetas.controler;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import br.com.lasa.desafioplanetas.event.RecursoCriadoEvent;
import br.com.lasa.desafioplanetas.model.Planeta;
import br.com.lasa.desafioplanetas.repository.PlanetaRepository;
import br.com.lasa.desafioplanetas.service.PlanetaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;





@RestController
@RequestMapping("/planetas")
@Api(value="Planetas", description="Crud Responsavel pelo cadastramento de planetas")
public class PlanetaControler {
	
	@Autowired
	private PlanetaRepository planetaRepository;

	@Autowired
	private PlanetaService planetaService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@ApiOperation(value = "Lista todos os planetas",response = Iterable.class)
	@GetMapping
	public Page<Planeta> pesquisar(@RequestParam(value = "page",required = true, defaultValue = "0") int page, @RequestParam(value ="size" , required = false, defaultValue = "10") int size  ) {
		
		
		
		Page<Planeta> planetas = planetaRepository.findAll(new PageRequest(page ,size));
		return  planetas;
	}
	
	
	@ApiOperation(value = "Criar  Planeta ",response = Iterable.class)
	@PostMapping
	public ResponseEntity<Planeta> criar(@Valid @RequestBody Planeta planeta, HttpServletResponse response) {
		Planeta planetaSalvo = planetaService.salvar(planeta);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, planetaSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(planetaSalvo);
	}
	
	@ApiOperation(value = "Buscar Planeta por nome",response = Iterable.class)
	@GetMapping("buscarnome/{nome}")
	public ResponseEntity<Planeta> buscarPeloNome(@PathVariable String nome) {
		
		Planeta planeta = planetaRepository.findByNomeContaining(nome);
		return planeta != null ? ResponseEntity.ok(planeta) : ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Buscar Planeta por codigo",response = Iterable.class)
	@GetMapping("buscarid/{id}")
	public ResponseEntity<Optional<Planeta>> buscarPeloId(@PathVariable String id) {
		
		Optional<Planeta> planeta = planetaRepository.findById(id);
		if (planeta != null)
			return ResponseEntity.ok(planeta);
		else
			return ResponseEntity.notFound().build();
	}
	
	
	@ApiOperation(value = "Deletar planeta por codigo",response = Iterable.class)
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remover(@PathVariable String id) {
		planetaRepository.deleteById(id);
	}
	


}
