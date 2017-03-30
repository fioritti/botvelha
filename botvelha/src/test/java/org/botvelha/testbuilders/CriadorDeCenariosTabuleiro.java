package org.botvelha.testbuilders;

import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.botvelha.domain.tabuleiro.Tabuleiro;
import org.botvelha.domain.tabuleiro.TipoElementoEnum;

public class CriadorDeCenariosTabuleiro {
	
	public Tabuleiro emAndamento() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.O);
		return tabuleiro;
	}
	
	public Tabuleiro emAndamento2() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_1, TipoElementoEnum.O);
		return tabuleiro;
	}
	
	
	public Tabuleiro comVitoriaElmentoX() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_2, TipoElementoEnum.X);
		return tabuleiro;
	}
	
	public Tabuleiro comVitoriaElmentoO() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_2, TipoElementoEnum.O);
		return tabuleiro;

	}
	
	public Tabuleiro velha() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_2, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_2, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_2, TipoElementoEnum.O);
		return tabuleiro;
	}
	
	public Tabuleiro comVitoria1Vertical() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_2, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_0, TipoElementoEnum.O);
		return tabuleiro;
	}
	
	public Tabuleiro comVitoria2Vertical() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_1, TipoElementoEnum.X);
		return tabuleiro;
	}

	public Tabuleiro comVitoria3Vertical() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_2, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_2, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_2, TipoElementoEnum.O);
		return tabuleiro;
	}

	public Tabuleiro comVitoria1Horizontal() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_2, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.O);
		return tabuleiro;
	}

	public Tabuleiro comVitoria2Horizontal() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_2, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_1, TipoElementoEnum.X);
		return tabuleiro;
	}

	public Tabuleiro comVitoria3Horizontal() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_0, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_1, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_2, TipoElementoEnum.O);
		return tabuleiro;
	}

	public Tabuleiro comVitoria1Diagonal() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_2, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_2, TipoElementoEnum.X);
		return tabuleiro;
	}

	public Tabuleiro comVitoria2Diagonal() throws PosicaoJaPreenchidaException {
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_0, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_1, TipoElementoEnum.X);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._0_2, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._1_1, TipoElementoEnum.O);
		tabuleiro.jogarNaPosicao(PosicaoTabuleiroEnum._2_0, TipoElementoEnum.O);
		return tabuleiro;
	}

	

}
