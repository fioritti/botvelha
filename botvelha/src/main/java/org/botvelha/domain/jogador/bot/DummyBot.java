package org.botvelha.domain.jogador.bot;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;

public class DummyBot extends Bot {

	public PosicaoTabuleiroEnum fazerJogada(List<PosicaoTabuleiroEnum> jogadasFeitas) {
		List<PosicaoTabuleiroEnum> posicoesValidas = obterPosicoesValidas(jogadasFeitas);
		PosicaoTabuleiroEnum posicaoEscolhida = escolherJogada(posicoesValidas);
		return posicaoEscolhida;
	}

	private PosicaoTabuleiroEnum escolherJogada(List<PosicaoTabuleiroEnum> posicoesValidas) {
		int qtdPosicoes = posicoesValidas.size();
		int posicaoEscolhida = ThreadLocalRandom.current().nextInt(0,qtdPosicoes);
		return posicoesValidas.get(posicaoEscolhida);
	}

}
