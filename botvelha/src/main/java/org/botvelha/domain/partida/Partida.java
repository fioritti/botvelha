package org.botvelha.domain.partida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.botvelha.domain.jogador.Jogador;
import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.botvelha.domain.tabuleiro.Tabuleiro;
import org.botvelha.domain.tabuleiro.TipoElementoEnum;
import org.botvelha.repository.dao.PartidaDao;
import org.botvelha.repository.entity.PartidaEntity;
import org.botvelha.repository.infrastructure.CriadorDeSessao;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Partida {

	private Jogador[] jogadores;
	private Map<Jogador, TipoElementoEnum> mapJogadorElemento;
	private List<PosicaoTabuleiroEnum> listaDeJogadas;
	private int jogadorInicial;
	private int jogadorAtual;
	private AvaliadorPartida avaliadorPartida;
	private Jogador jogadorVencedor;
	private PartidaDao partidaDao;
	
	
	public Partida(Jogador jogador, Jogador boot) {
		this.jogadores = new Jogador[2];
		this.jogadores[0]=jogador;
		this.jogadores[1]=boot;
		this.avaliadorPartida = new AvaliadorPartida(new Tabuleiro());
		this.partidaDao = new PartidaDao(new CriadorDeSessao().getSession());
		
	}


	public void iniciar() throws PartidaFinalizadaException {
		sortearJogadorInicial();
		this.mapJogadorElemento = new HashMap<Jogador, TipoElementoEnum>();
		this.mapJogadorElemento.put(jogadores[this.jogadorInicial], TipoElementoEnum.O);
		this.mapJogadorElemento.put(obterProximoJogador(), TipoElementoEnum.X);
		this.listaDeJogadas = new ArrayList<PosicaoTabuleiroEnum>();
	}

	public Jogador obterProximoJogador() throws PartidaFinalizadaException {
		validarEstadoPartida();
		this.jogadorAtual = jogadorAtual==0?1:0;
		return jogadores[jogadorAtual];
	}


	private void validarEstadoPartida() throws PartidaFinalizadaException {
		if(EstadoPartidaEnum.FINALIZADO.equals(this.getEstado())) {
			throw new PartidaFinalizadaException("Partida já está finalizada");
		}
	}


	private void sortearJogadorInicial() {
		this.jogadorInicial = ThreadLocalRandom.current().nextInt(0,1 + 1);
		this.jogadorAtual = this.jogadorInicial;
	}


	public Jogador obterPrimeroJogador() {
		return jogadores[jogadorInicial];
	}

	public Jogador obterVencedor() {
		return this.jogadorVencedor;
	}

	public TipoElementoEnum getElementoPorJogador(Jogador jogador) {
		return mapJogadorElemento.get(jogador);
	}


	public void fazerJogada(Jogador jogador, PosicaoTabuleiroEnum pte) throws PosicaoJaPreenchidaException, PartidaFinalizadaException, JsonProcessingException {
		validarEstadoPartida();
		this.avaliadorPartida.getTabuleiro().jogarNaPosicao(pte, mapJogadorElemento.get(jogador));
		this.listaDeJogadas.add(pte);
		avaliarSituacaoPartida();
	}

	private void avaliarSituacaoPartida() throws JsonProcessingException {
		if(EstadoPartidaEnum.FINALIZADO.equals(this.getEstado())) {
			if(!TipoResultadoEnum.VELHA.equals(this.avaliadorPartida.obterResultado())) {
				String elemento = this.avaliadorPartida.obterResultado().getElemento();
				for(Jogador j: mapJogadorElemento.keySet()) {
					if(TipoElementoEnum.getByValue(elemento).equals(mapJogadorElemento.get(j))) {
						this.jogadorVencedor = j;
						this.partidaDao.salvar(new PartidaEntity(this));
						break;
					}
				}
				
			}
			
		}
	}


	public EstadoPartidaEnum getEstado() {
		return this.avaliadorPartida.obterEstado();
	}


	public List<PosicaoTabuleiroEnum> getListaDeJogadas() {
		return listaDeJogadas;
	}

}
