package org.botvelha.domain.jogador.bot;

import java.util.ArrayList;
import java.util.List;

import org.botvelha.domain.jogador.Jogador;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;

public abstract class Bot implements Jogador {

	public abstract PosicaoTabuleiroEnum fazerJogada(List<PosicaoTabuleiroEnum> list);
	
	protected List<PosicaoTabuleiroEnum> obterPosicoesValidas(List<PosicaoTabuleiroEnum> jogadasFeitas) {
		List<PosicaoTabuleiroEnum> posicoesValidas = new ArrayList<PosicaoTabuleiroEnum>();
		for (PosicaoTabuleiroEnum pte : PosicaoTabuleiroEnum.values()) {
			if(!jogadasFeitas.contains(pte)) {
				posicoesValidas.add(pte);
			}
		}
		return posicoesValidas;
	}


}
