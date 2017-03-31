package org.botvelha.domain.jogador.bot;

import java.util.List;

import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.botvelha.repository.dao.PartidaDao;
import org.botvelha.repository.infrastructure.CriadorDeSessao;

public class SmartBot extends Bot {
	
	private PartidaDao partidaDao;
	
	
	public SmartBot() {
		this.partidaDao = new PartidaDao(new CriadorDeSessao().getSession());
	}
	

	@Override
	public PosicaoTabuleiroEnum fazerJogada(List<PosicaoTabuleiroEnum> list) {
		
		return null;
	}

}
