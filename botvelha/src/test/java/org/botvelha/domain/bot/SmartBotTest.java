package org.botvelha.domain.bot;

import static org.junit.Assert.*;

import java.util.List;

import org.botvelha.domain.jogador.Jogador;
import org.botvelha.domain.jogador.JogadorHumano;
import org.botvelha.domain.jogador.bot.Bot;
import org.botvelha.domain.jogador.bot.DummyBot;
import org.botvelha.domain.jogador.bot.SmartBot;
import org.botvelha.domain.partida.Partida;
import org.botvelha.domain.partida.PartidaFinalizadaException;
import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class SmartBotTest {


	@Test
	public void deveEscolherMelhorEstrategiaComecandoPartida() throws PartidaFinalizadaException, JsonProcessingException, PosicaoJaPreenchidaException {
		PosicaoTabuleiroEnum jogadaEsperada = PosicaoTabuleiroEnum._0_1;
		Bot bot = new SmartBot();
		Partida partida = new Partida(new JogadorHumano("guileme13@gmail.com"),bot);
		partida.iniciar();
		Jogador jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_0);
		List<PosicaoTabuleiroEnum> listaDeJogadas = partida.getListaDeJogadas();
		PosicaoTabuleiroEnum jogadaBot =  bot.fazerJogada(listaDeJogadas);
		assertEquals(jogadaEsperada, jogadaBot);
	}

}
