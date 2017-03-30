package org.botvelha.domain.tabuleiro;

import static org.junit.Assert.*;

import org.botvelha.domain.partida.AvaliadorPartida;
import org.botvelha.testbuilders.CriadorDeCenariosTabuleiro;
import org.botvelha.testbuilders.CriadorDeCenariosTabuleiroView;
import org.botvelha.view.TabuleiroView;
import org.junit.Test;

public class TabulerioViewTest {

	@Test
	public void deveExibirTabuleiroVazio() {
		String visaoEsperada = TabuleiroView.TABULEIRO_VAZIO;
		TabuleiroView tabuleiroView = new TabuleiroView();
		assertEquals(visaoEsperada, tabuleiroView.verTabuleiro());
	}
	
	@Test
	public void deveExibirTabuleiroEmAndamento() throws PosicaoJaPreenchidaException {
		String visaoEsperada = new CriadorDeCenariosTabuleiroView().comVisaoEmAndamento();
		TabuleiroView tabuleiroView = new TabuleiroView(new AvaliadorPartida(new CriadorDeCenariosTabuleiro().emAndamento()));
		assertEquals(visaoEsperada, tabuleiroView.verTabuleiro());
	}
	
	@Test
	public void deveExibirTabuleiroEmAndamento2() throws PosicaoJaPreenchidaException {
		String visaoEsperada = new CriadorDeCenariosTabuleiroView().comVisaoEmAndamento2();
		TabuleiroView tabuleiroView = new TabuleiroView(new AvaliadorPartida(new CriadorDeCenariosTabuleiro().emAndamento2()));
		assertEquals(visaoEsperada, tabuleiroView.verTabuleiro());
	}
	
	
	@Test
	public void deveExibirTabuleiroFinalizadoVertical1() throws PosicaoJaPreenchidaException {
		String visaoEsperada = new CriadorDeCenariosTabuleiroView().comVisaoComVitoria1Vertical();
		TabuleiroView tabuleiroView = new TabuleiroView(new AvaliadorPartida(new CriadorDeCenariosTabuleiro().comVitoria1Vertical()));
		assertEquals(visaoEsperada, tabuleiroView.verTabuleiro());
	}

	@Test
	public void deveExibirTabuleiroFinalizadoHorizontal2() throws PosicaoJaPreenchidaException {
		String visaoEsperada = new CriadorDeCenariosTabuleiroView().comVisaoComVitoria2Horizontal();
		TabuleiroView tabuleiroView = new TabuleiroView(new AvaliadorPartida(new CriadorDeCenariosTabuleiro().comVitoria2Horizontal()));
		assertEquals(visaoEsperada, tabuleiroView.verTabuleiro());
	}
	
	@Test
	public void deveExibirTabuleiroFinalizadoDiagonal1() throws PosicaoJaPreenchidaException {
		String visaoEsperada = new CriadorDeCenariosTabuleiroView().comVisaoComVitoria1Diagonal();
		TabuleiroView tabuleiroView = new TabuleiroView(new AvaliadorPartida(new CriadorDeCenariosTabuleiro().comVitoria1Diagonal()));
		assertEquals(visaoEsperada, tabuleiroView.verTabuleiro());
	}

	@Test
	public void deveExibirTabuleiroFinalizadoVelha() throws PosicaoJaPreenchidaException {
		String visaoEsperada = new CriadorDeCenariosTabuleiroView().comVisaoComVelha();
		TabuleiroView tabuleiroView = new TabuleiroView(new AvaliadorPartida(new CriadorDeCenariosTabuleiro().velha()));
		assertEquals(visaoEsperada, tabuleiroView.verTabuleiro());
	}
	
	

}
