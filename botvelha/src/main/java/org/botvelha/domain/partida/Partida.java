package org.botvelha.domain.partida;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.botvelha.domain.tabuleiro.TipoElementoEnum;
import org.botvelha.repository.entity.Usuario;

public class Partida {

	private Usuario[] jogadores;
	private Map<Usuario, TipoElementoEnum> mapJogadorElemento;
	private int jogadorInicial;
	private int jogadorAtual;
	
	
	public Partida(Usuario jogador, Usuario boot) {
		this.jogadores = new Usuario[2];
		this.jogadores[0]=jogador;
		this.jogadores[1]=boot;
	}


	public void iniciar() {
		sortearJogadorInicial();
		this.mapJogadorElemento = new HashMap<Usuario, TipoElementoEnum>();
		this.mapJogadorElemento.put(jogadores[this.jogadorInicial], TipoElementoEnum.O);
		this.mapJogadorElemento.put(obterProximoJogador(), TipoElementoEnum.X);
	}

	public Usuario obterProximoJogador() {
		this.jogadorAtual = jogadorAtual==0?1:0;
		return jogadores[jogadorAtual];
	}


	private void sortearJogadorInicial() {
		this.jogadorInicial = ThreadLocalRandom.current().nextInt(0,1 + 1);
		this.jogadorAtual = this.jogadorInicial;
	}


	public Usuario obterPrimeroJogador() {
		return jogadores[jogadorInicial];
	}


	public TipoElementoEnum getElementoPorJogador(Usuario jogador) {
		return mapJogadorElemento.get(jogador);
	}

}
