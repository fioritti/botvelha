package org.botvelha.domain.tabuleiro;

public enum DimensaoEnum {
	_1_VERTICAL(1,"vertical"),
	_2_VERTICAL(2,"vertical"),
	_3_VERTICAL(3,"vertical"),
	_1_HORIZONTAL(1,"horizontal"),
	_2_HORIZONTAL(2,"horizontal"),
	_3_HORIZONTAL(3,"horizontal"),
	_1_DIAGONAL(1,"diagonal"),
	_2_DIAGONAL(2,"diagonal");
	
	private int posicao;
	private String nome;
	
	private DimensaoEnum(int posicao,String nome) {
		this.posicao = posicao;
		this.nome = nome;
	}

	public int getPosicao() {
		return posicao;
	}

	public String getNome() {
		return nome;
	}
	
	public static DimensaoEnum getByPosicaoENome(int posicao,String nome) {
		for (DimensaoEnum d : DimensaoEnum.values()) {
			if(d.getPosicao() == posicao && d.getNome().equals(nome)) {
				return d;
			}
		}
		throw new IllegalArgumentException(String.format("Dimensão não encontrada. posição: %d , nome: %s",posicao,nome)) ;
	}

	
	

}
