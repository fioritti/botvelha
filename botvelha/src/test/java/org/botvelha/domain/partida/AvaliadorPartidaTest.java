package org.botvelha.domain.partida;

import static org.junit.Assert.assertEquals;

import org.botvelha.domain.tabuleiro.DimensaoEnum;
import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.botvelha.domain.tabuleiro.Tabuleiro;
import org.botvelha.domain.tabuleiro.TipoElementoEnum;
import org.botvelha.testbuilders.CriadorDeCenariosTabuleiro;
import org.junit.Before;
import org.junit.Test;

public class AvaliadorPartidaTest {
	
	private CriadorDeCenariosTabuleiro criadorDeCenariosTabuleiro;
	
	@Before
	public void iniciar() {
		this.criadorDeCenariosTabuleiro = new CriadorDeCenariosTabuleiro();
	}

	@Test
	public void deveAvaliarPartidaComEmAndamento() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.EM_ANDAMENTO; 
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.emAndamento());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
	}

	@Test
	public void deveAvaliarPartidaComEmAndamentoCenario2() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.EM_ANDAMENTO; 
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.emAndamentoCenario2());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
	}

	
	@Test
	public void deveAvaliarPartidaFinalizada() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO; 
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoriaElmentoX());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
	}
	
	@Test
	public void deveAvaliarPartidaFinalizada1Vertical() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		DimensaoEnum dimensaoEsperada = DimensaoEnum._1_VERTICAL;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoria1Vertical());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(dimensaoEsperada, avaliadorPartida.obterDimensaoVitoria());
	}

	@Test
	public void deveAvaliarPartidaFinalizada2Vertical() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		DimensaoEnum dimensaoEsperada = DimensaoEnum._2_VERTICAL;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoria2Vertical());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(dimensaoEsperada, avaliadorPartida.obterDimensaoVitoria());
	}

	@Test
	public void deveAvaliarPartidaFinalizada3Vertical() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		DimensaoEnum dimensaoEsperada = DimensaoEnum._3_VERTICAL;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoria3Vertical());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(dimensaoEsperada, avaliadorPartida.obterDimensaoVitoria());
	}

	@Test
	public void deveAvaliarPartidaFinalizada1Horizontal() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		DimensaoEnum dimensaoEsperada = DimensaoEnum._1_HORIZONTAL;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoria1Horizontal());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(dimensaoEsperada, avaliadorPartida.obterDimensaoVitoria());
	}

	@Test
	public void deveAvaliarPartidaFinalizada2Horizontal() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		DimensaoEnum dimensaoEsperada = DimensaoEnum._2_HORIZONTAL;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoria2Horizontal());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(dimensaoEsperada, avaliadorPartida.obterDimensaoVitoria());
	}

	@Test
	public void deveAvaliarPartidaFinalizada3Horizontal() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		DimensaoEnum dimensaoEsperada = DimensaoEnum._3_HORIZONTAL;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoria3Horizontal());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(dimensaoEsperada, avaliadorPartida.obterDimensaoVitoria());
	}
	
	@Test
	public void deveAvaliarPartidaFinalizada1Diagonal() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		DimensaoEnum dimensaoEsperada = DimensaoEnum._1_DIAGONAL;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoria1Diagonal());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(dimensaoEsperada, avaliadorPartida.obterDimensaoVitoria());
	}

	@Test
	public void deveAvaliarPartidaFinalizada2Diagonal() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		DimensaoEnum dimensaoEsperada = DimensaoEnum._2_DIAGONAL;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoria2Diagonal());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(dimensaoEsperada, avaliadorPartida.obterDimensaoVitoria());
	}

	@Test
	public void deveAvaliarPartidaFinalizadaVitoriaX() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		TipoResultadoEnum tipoResultadoEsperado = TipoResultadoEnum.VITORIA_ELEMENTO_X;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoriaElmentoX());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(tipoResultadoEsperado, avaliadorPartida.obterResultado());
	}

	@Test
	public void deveAvaliarPartidaFinalizadaVitoriaO() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		TipoResultadoEnum tipoResultadoEsperado = TipoResultadoEnum.VITORIA_ELEMENTO_O;
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(criadorDeCenariosTabuleiro.comVitoriaElmentoO());
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(tipoResultadoEsperado, avaliadorPartida.obterResultado());
	}

	@Test
	public void deveAvaliarPartidaFinalizadaVelha() throws PosicaoJaPreenchidaException {
		EstadoPartidaEnum estadoPartidaEsperado = EstadoPartidaEnum.FINALIZADO;
		TipoResultadoEnum tipoResultadoEsperado = TipoResultadoEnum.VELHA;
		Tabuleiro tabuleiro = criadorDeCenariosTabuleiro.emAndamento2();
		AvaliadorPartida avaliadorPartida = new AvaliadorPartida(tabuleiro);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_2, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_2, TipoElementoEnum.O);
		assertEquals(estadoPartidaEsperado, avaliadorPartida.obterEstado());
		assertEquals(tipoResultadoEsperado, avaliadorPartida.obterResultado());
	}

}
