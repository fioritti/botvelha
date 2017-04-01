package org.botvelha.domain.bot;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.botvelha.domain.jogador.Jogador;
import org.botvelha.domain.jogador.JogadorHumano;
import org.botvelha.domain.jogador.bot.Bot;
import org.botvelha.domain.jogador.bot.DummyBot;
import org.botvelha.domain.partida.Partida;
import org.botvelha.domain.partida.PartidaFinalizadaException;
import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DummyBotTest {

	@Test
	public void deveEscolherUmaPosicaoParaJogar() throws Exception {
		Bot bot = new DummyBot();
		Partida partida = new Partida(new JogadorHumano("guileme13@gmail.com"),bot);
		partida.iniciar();
		Jogador jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_1);
		List<PosicaoTabuleiroEnum> listaDeJogadas = partida.getListaDeJogadas();
		PosicaoTabuleiroEnum jogadaBot =  bot.fazerJogada(listaDeJogadas);
		boolean jogadaPermitida = true;
		for (PosicaoTabuleiroEnum pte : listaDeJogadas) {
			if(pte.equals(jogadaBot)) {
				jogadaPermitida = false;
				break;
			}
		}
		assertTrue(jogadaPermitida);
	}
	
	

}
