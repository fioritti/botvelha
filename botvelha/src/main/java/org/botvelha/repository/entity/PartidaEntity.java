package org.botvelha.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.botvelha.domain.partida.Partida;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class PartidaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String elementoVencedor;
	private String elementoIniciouPartida;
	private String jsonListaJogadas;
	
	
	public PartidaEntity() {}
	
	public PartidaEntity(Partida partida) throws JsonProcessingException {
		this.elementoVencedor = partida.getElementoPorJogador(partida.obterVencedor()).getElemento();
		this.elementoIniciouPartida = partida.getElementoPorJogador(partida.obterPrimeroJogador()).getElemento();
		this.jsonListaJogadas = new ObjectMapper().writeValueAsString(partida.getListaDeJogadas()); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getElementoVencedor() {
		return elementoVencedor;
	}

	public void setElementoVencedor(String elementoVencedor) {
		this.elementoVencedor = elementoVencedor;
	}

	public String getElementoIniciouPartida() {
		return elementoIniciouPartida;
	}

	public void setElementoIniciouPartida(String elementoIniciouPartida) {
		this.elementoIniciouPartida = elementoIniciouPartida;
	}

	public String getJsonListaJogadas() {
		return jsonListaJogadas;
	}

	public void setJsonListaJogadas(String jsonListaJogadas) {
		this.jsonListaJogadas = jsonListaJogadas;
	}

	@Override
	public String toString() {
		return "PartidaEntity [id=" + id + ", elementoVencedor=" + elementoVencedor + ", elementoIniciouPartida="
				+ elementoIniciouPartida + ", jsonListaJogadas=" + jsonListaJogadas + "]";
	}

}
