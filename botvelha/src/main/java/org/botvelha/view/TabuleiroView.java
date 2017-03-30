package org.botvelha.view;

import java.util.HashMap;
import java.util.Map;

import org.botvelha.domain.partida.AvaliadorPartida;
import org.botvelha.domain.partida.EstadoPartidaEnum;
import org.botvelha.domain.partida.TipoResultadoEnum;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;

public class TabuleiroView {
	
	public static final String TABULEIRO_VAZIO =
			 " | | \r\n" + 
			 "-------\r\n" + 
			 " | | \r\n" + 
			 "-------\r\n" + 
			 " | | \r\n";

	private static final CharSequence CHAR_ELEMETO_VITORIA = "*";
	
	private final Map<PosicaoTabuleiroEnum, Integer> mapaTabuleiro;
	
	private StringBuilder estado;
	
	public TabuleiroView() {
		this.estado = new StringBuilder();
		this.estado.append(TABULEIRO_VAZIO);
		
		this.mapaTabuleiro = new HashMap<PosicaoTabuleiroEnum, Integer>();
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._0_0, 0);
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._0_1, 2);
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._0_2, 4);
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._1_0, 16);
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._1_1, 18);
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._1_2, 20);
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._2_0, 32);
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._2_1, 34);
		this.mapaTabuleiro.put(PosicaoTabuleiroEnum._2_2, 36);
	}

	public TabuleiroView(AvaliadorPartida avaliadorPartida) {
		this();
		desenharView(avaliadorPartida);
	}


	private void desenharView(AvaliadorPartida avaliadorPartida) {
		String[][] estadoTabuleiro = avaliadorPartida.getTabuleiro().obterEstadoAtual();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				String elemento =  estadoTabuleiro[i][j];
				if(elemento!=null) {
					preencherPosicao(elemento,PosicaoTabuleiroEnum.getPosicaoPorValor(i, j));
				}
			}
		}
		verificarSePartidaFoiFinalizada(avaliadorPartida);
	}

	private void verificarSePartidaFoiFinalizada(AvaliadorPartida avaliadorPartida) {
		if(EstadoPartidaEnum.FINALIZADO.equals(avaliadorPartida.obterEstado())){
			TipoResultadoEnum tipoResultado = avaliadorPartida.obterResultado();
			if(!TipoResultadoEnum.VELHA.equals(tipoResultado)) {
				String elemento = tipoResultado.getElemento();
				this.estado = new StringBuilder(this.estado.toString().replace(elemento, CHAR_ELEMETO_VITORIA));
			}
		}
	}


	private void preencherPosicao(String elemento, PosicaoTabuleiroEnum posicaoElemento) {
		Integer indexPosicao = mapaTabuleiro.get(posicaoElemento);
		this.estado.replace(indexPosicao, indexPosicao+1, elemento);
	}

	public String verTabuleiro() {
		return this.estado.toString();
	}
	


}
