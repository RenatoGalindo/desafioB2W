package br.com.lasa.desafioplanetas.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "planetas")
public class Planeta {
	

	@Id
    private String id;
	
	@NotNull(message = "Nome é obrigatório")
    private String nome;
	@NotNull(message = "Clima é obrigatório")
    private String clima;
	@NotNull(message = "Terreno é obrigatório")
    private String terreno;
    private int quantidadeAparicoes;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public int getQuantidadeAparicoes() {
		return quantidadeAparicoes;
	}
	public void setQuantidadeAparicoes(int quantidadeAparicoes) {
		this.quantidadeAparicoes = quantidadeAparicoes;
	}

	
    
    
	

}
