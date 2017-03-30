package org.botvelha.domain.tabuleiro;

public class Tabuleiro {
	
	private String[][] posicoes;
	
	public Tabuleiro() {
		this.posicoes = new String[3][3];
	}

	public String[][] obterEstadoAtual() {
		return this.posicoes;
	}

	public void jogarNaPosicao(PosicaoTabuleiroEnum pte, TipoElementoEnum tipoElemento) throws PosicaoJaPreenchidaException {
		 String posicao = this.posicoes[pte.getX()][pte.getY()];
		 if(posicao == null) {
			 this.posicoes[pte.getX()][pte.getY()] = tipoElemento.getElemento();
		 }else {
			 throw new PosicaoJaPreenchidaException(String.format("A posição [%d,%d] já está preenchida", pte.getX(),pte.getY()));
		 }
		
	}
	
	
	

}
