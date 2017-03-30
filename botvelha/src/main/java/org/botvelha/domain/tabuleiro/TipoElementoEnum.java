package org.botvelha.domain.tabuleiro;

public enum TipoElementoEnum {
	X("x"),
	O("o");
	
	private TipoElementoEnum(String elemento) {
		this.elemento = elemento;
	}
	
	private String elemento;

	public String getElemento() {
		return elemento;
	}
	
	public static TipoElementoEnum getByValue(String elemento) {
		for (TipoElementoEnum te : TipoElementoEnum.values()) {
			if(te.getElemento().equals(elemento)) {
				return te;
			}
		}
		throw new IllegalArgumentException(String.format("Elemento não encontrado")) ;
	}

}
