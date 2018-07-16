package br.com.lasa.desafioplanetas.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.lasa.desafioplanetas.model.Planeta;


public interface PlanetaRepository extends 	MongoRepository<Planeta, String>  {

	Planeta findByNomeContaining (String nome);
	
	Boolean findByNomeExists (String nome);
	
	Planeta findByNome (String nome);
	
	
}
