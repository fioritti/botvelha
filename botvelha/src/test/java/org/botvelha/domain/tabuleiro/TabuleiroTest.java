package org.botvelha.domain.tabuleiro;

import static org.junit.Assert.*;

import org.junit.Test;

public class TabuleiroTest {

	@Test
	public void deveExibirXnaPosicao11() {
		String[][] estadoEsperado = new String[3][3];
		estadoEsperado[1][1] = TipoElementoEnum.X.getElemento();
		Tabuleiro t = new Tabuleiro();
		try {
			t.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.X);

			assertArrayEquals(estadoEsperado, t.obterEstadoAtual());
		} catch (PosicaoJaPreenchidaException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = PosicaoJaPreenchidaException.class)
	public void deveExibirExcecaoDePosicaoOcupada() throws PosicaoJaPreenchidaException {
		Tabuleiro t = new Tabuleiro();
		t.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.X);
		t.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.X);
	}

}
