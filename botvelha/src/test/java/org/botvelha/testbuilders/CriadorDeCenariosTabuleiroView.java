package org.botvelha.testbuilders;

import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;

public class CriadorDeCenariosTabuleiroView {
	
	public String comVisaoEmAndamento() throws PosicaoJaPreenchidaException {
		 String tabuleiro = 
		 "x|o| \r\n" + 
		 "-------\r\n" + 
		 " | | \r\n" + 
		 "-------\r\n" + 
		 " | | \r\n";
		return tabuleiro;
	}
	
	public String comVisaoEmAndamento2() throws PosicaoJaPreenchidaException {
		 String tabuleiro = 
		 "x|o| \r\n" + 
		 "-------\r\n" + 
		 "x|x| \r\n" + 
		 "-------\r\n" + 
		 " |o| \r\n";
		return tabuleiro;
	}

	public String comVisaoComVitoria1Vertical() {
		 String tabuleiro = 
		 "*| |x\r\n" + 
		 "-------\r\n" + 
		 "*|x| \r\n" + 
		 "-------\r\n" + 
		 "*| | \r\n";
		return tabuleiro;
	}

	public String comVisaoComVitoria2Horizontal() {
		 String tabuleiro = 
		 " | | \r\n" + 
		 "-------\r\n" + 
		 "*|*|*\r\n" + 
		 "-------\r\n" + 
		 "x|x| \r\n";
		return tabuleiro;
	}

	public String comVisaoComVitoria1Diagonal() {
		 String tabuleiro = 
		 "*|o| \r\n" + 
		 "-------\r\n" + 
		 " |*|o\r\n" + 
		 "-------\r\n" + 
		 " | |*\r\n";
		return tabuleiro;
	}

	public String comVisaoComVelha() {
		 String tabuleiro = 
		 "x|o|x\r\n" + 
		 "-------\r\n" + 
		 "o|x|o\r\n" + 
		 "-------\r\n" + 
		 "o|x|o\r\n";
		return tabuleiro;
	}

	

}
