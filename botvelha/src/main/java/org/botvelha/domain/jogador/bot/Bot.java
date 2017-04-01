package org.botvelha.domain.jogador.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.botvelha.domain.jogador.Jogador;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;

public abstract class Bot implements Jogador {

	protected boolean iniciouPartida;
	
	public  PosicaoTabuleiroEnum fazerJogada(List<PosicaoTabuleiroEnum> jogadasFeitas) throws Exception {
		List<PosicaoTabuleiroEnum> posicoesValidas = obterPosicoesValidas(jogadasFeitas);
		PosicaoTabuleiroEnum posicaoEscolhida = escolherJogada(posicoesValidas);
		return posicaoEscolhida;
	};

	private PosicaoTabuleiroEnum escolherJogada(List<PosicaoTabuleiroEnum> posicoesValidas) {
		int qtdPosicoes = posicoesValidas.size();
		int posicaoEscolhida = ThreadLocalRandom.current().nextInt(0,qtdPosicoes);
		return posicoesValidas.get(posicaoEscolhida);
	}

	
	protected List<PosicaoTabuleiroEnum> obterPosicoesValidas(List<PosicaoTabuleiroEnum> jogadasFeitas) {
		List<PosicaoTabuleiroEnum> posicoesValidas = new ArrayList<PosicaoTabuleiroEnum>();
		for (PosicaoTabuleiroEnum pte : PosicaoTabuleiroEnum.values()) {
			if(!jogadasFeitas.contains(pte)) {
				posicoesValidas.add(pte);
			}
		}
		return posicoesValidas;
	}
	
	public void informaQuemIniciaPartida(Jogador iniciaPartida) {
		if(this.equals(iniciaPartida)) {
			this.iniciouPartida = true;
		}
	}


}
