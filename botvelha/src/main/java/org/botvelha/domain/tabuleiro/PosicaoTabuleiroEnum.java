package org.botvelha.domain.tabuleiro;

public enum PosicaoTabuleiroEnum {
	_0_0(0,0),
	_0_1(0,1),
	_0_2(0,2),
	_1_0(1,0),
	_1_1(1,1),
	_1_2(1,2),
	_2_0(2,0),
	_2_1(2,1),
	_2_2(2,2);
	
	private PosicaoTabuleiroEnum(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public static PosicaoTabuleiroEnum getPosicaoPorValor(int x,int y) {
		for (PosicaoTabuleiroEnum pte : PosicaoTabuleiroEnum.values()) {
			if(pte.getX() == x && pte.getY() == y) {
				return pte;
			}
		}
		throw new IllegalArgumentException(String.format("A posição x:%d e y:%s, é invalida", x,y)) ;
	}
	

}
