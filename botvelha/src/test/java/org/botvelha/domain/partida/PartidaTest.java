package org.botvelha.domain.partida;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.botvelha.domain.jogador.Jogador;
import org.botvelha.domain.jogador.JogadorHumano;
import org.botvelha.domain.jogador.bot.DummyBot;
import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class PartidaTest {
	

	@Test
	public void deveAssociarUsuarioComElemento() throws PartidaFinalizadaException {
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		assertTrue(partida.getElementoPorJogador(guileme13)!=null);
	}
	
	@Test
	public void deveVerificarQueProximoJogadoresNaoIgualAoAnterior() throws PartidaFinalizadaException {
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		Jogador u1 = partida.obterProximoJogador();
		Jogador u2 = partida.obterProximoJogador();
		assertTrue(u1!=u2);
	}
	
	@Test
	public void deveFazerJogadaEJogoContinuaEmAndameto() throws PosicaoJaPreenchidaException, PartidaFinalizadaException, JsonProcessingException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.EM_ANDAMENTO;
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		Jogador jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_0);
		assertEquals(estadoPartidaEsperado, partida.getEstado());
	}
	
	@Test
	public void deveFazerJogadasEJogoDeveSerFinalizado() throws PosicaoJaPreenchidaException, PartidaFinalizadaException, JsonProcessingException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		int contador = 0;
		while(EstadoPartidaEnum.EM_ANDAMENTO.equals(partida.getEstado())) {
			Jogador jogador = partida.obterProximoJogador();
			partida.fazerJogada(jogador,PosicaoTabuleiroEnum.values()[contador++]);
		}
		assertEquals(estadoPartidaEsperado, partida.getEstado());
	}

	@Test(expected=PosicaoJaPreenchidaException.class)
	public void deveFazerJogadaEmPosicaoJaOcupadaEObterErro() throws PosicaoJaPreenchidaException, PartidaFinalizadaException, JsonProcessingException {
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		int contador = 0;
		while(EstadoPartidaEnum.EM_ANDAMENTO.equals(partida.getEstado())) {
			Jogador jogador = partida.obterProximoJogador();
			partida.fazerJogada(jogador,PosicaoTabuleiroEnum.values()[contador]);
		}
	}

	@Test
	public void deveFazerJogadasObterPartidaFinalizadaEObterVencedor() throws PosicaoJaPreenchidaException, PartidaFinalizadaException, JsonProcessingException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		Jogador jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_1);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_1);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_2);
		assertEquals(estadoPartidaEsperado, partida.getEstado());
		assertTrue(partida.obterVencedor()!=null);
		
	}
	
	@Test(expected=PartidaFinalizadaException.class)
	public void deveFazerJogadasEObterExcecaoDePartidaJaFinalizada() throws PosicaoJaPreenchidaException, PartidaFinalizadaException, JsonProcessingException {
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		Jogador jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_1);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_1);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_2);
		jogador = partida.obterProximoJogador();
		
	}
	
	@Test
	public void deveArmazenarJogadas() throws PosicaoJaPreenchidaException, PartidaFinalizadaException, JsonProcessingException {
		List<PosicaoTabuleiroEnum> jogadasArmazendasEsperada = Arrays.asList(PosicaoTabuleiroEnum._0_0,PosicaoTabuleiroEnum._1_0,PosicaoTabuleiroEnum._0_1,PosicaoTabuleiroEnum._1_1,PosicaoTabuleiroEnum._0_2);
		
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		Jogador jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_1);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_1);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_2);
		
		List<PosicaoTabuleiroEnum> listaDeJogadas = partida.getListaDeJogadas();
		boolean resultadoTeste = true;
		for(int i=0;i<jogadasArmazendasEsperada.size();i++) {
			if(!listaDeJogadas.get(i).equals(jogadasArmazendasEsperada.get(i))) {
				resultadoTeste = false;
				break;
			}
		}
		assertTrue(resultadoTeste);
	}
	

}
