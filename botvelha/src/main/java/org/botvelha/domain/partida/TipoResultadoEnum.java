package org.botvelha.domain.partida;

public enum TipoResultadoEnum {
	VELHA(""),
	VITORIA_ELEMENTO_X("x"),
	VITORIA_ELEMENTO_O("o");
	
	private String elemento;
	
	private TipoResultadoEnum(String elemento) {
		this.elemento = elemento;
	}
	
	
	public static TipoResultadoEnum getResultadoByTipo(String elemento) {
		for (TipoResultadoEnum tre : TipoResultadoEnum.values()) {
			if(tre.getElemento().equals(elemento)) {
				return tre;
			}
		}
		throw new IllegalArgumentException(String.format("Tipo de resultado não existe")) ;
	}


	public String getElemento() {
		return elemento;
	}

}
