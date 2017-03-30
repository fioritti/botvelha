package org.botvelha.domain.partida;

import org.botvelha.domain.tabuleiro.DimensaoEnum;
import org.botvelha.domain.tabuleiro.Tabuleiro;

public class AvaliadorPartida {

	private Tabuleiro tabuleiro;
	private DimensaoEnum dimensaoVitoria;
	private TipoResultadoEnum tipoResultado;
	private EstadoPartidaEnum estadoPartida = EstadoPartidaEnum.EM_ANDAMENTO;
	private boolean existePosicaoNaoPreenchida = false;


	public AvaliadorPartida(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public EstadoPartidaEnum obterEstado() {
		String[][] estadoTabuleiro = tabuleiro.obterEstadoAtual();
		if (EstadoPartidaEnum.FINALIZADO.equals(avaliarVertical(estadoTabuleiro))
				|| EstadoPartidaEnum.FINALIZADO.equals(avaliarHorizontal(estadoTabuleiro))
				|| EstadoPartidaEnum.FINALIZADO.equals(avaliarDiagonais(estadoTabuleiro))) {
			this.estadoPartida = EstadoPartidaEnum.FINALIZADO; 
			return this.estadoPartida;
		}
		avaliarEmpate();
		return this.estadoPartida;
	}

	private void avaliarEmpate() {
		if(EstadoPartidaEnum.EM_ANDAMENTO.equals(this.estadoPartida) && !this.existePosicaoNaoPreenchida) {
			this.tipoResultado = TipoResultadoEnum.VELHA;
			this.estadoPartida = EstadoPartidaEnum.FINALIZADO;
		}
	}

	private Object avaliarDiagonais(String[][] estadoTabuleiro) {
		String[] primeiraDiagnoal = new String[3];
		String[] segundaDiagnoal = new String[3];
		for (int i = 0,j=2; i < 3; i++,j--) {
			primeiraDiagnoal[i] = estadoTabuleiro[i][i];
			segundaDiagnoal[i] = estadoTabuleiro[i][j];
		}
		if(EstadoPartidaEnum.FINALIZADO.equals(avaliarDimensao(primeiraDiagnoal,DimensaoEnum._1_DIAGONAL))
		|| EstadoPartidaEnum.FINALIZADO.equals(avaliarDimensao(segundaDiagnoal,DimensaoEnum._2_DIAGONAL))) {
			return EstadoPartidaEnum.FINALIZADO; 
		}
		
		return EstadoPartidaEnum.EM_ANDAMENTO;
	}

	private EstadoPartidaEnum avaliarVertical(String[][] estadoTabuleiro) {
		for (int i = 0; i < 3; i++) {
			String[] dimensao = new String[3];
			for (int j = 0; j < 3; j++) {
				dimensao[j] = estadoTabuleiro[j][i];
			}
			if(EstadoPartidaEnum.FINALIZADO.equals(avaliarDimensao(dimensao,DimensaoEnum.getByPosicaoENome(i+1, DimensaoEnum._1_VERTICAL.getNome())))) {
				return EstadoPartidaEnum.FINALIZADO; 
			}
		}
		return EstadoPartidaEnum.EM_ANDAMENTO;
	}

	private EstadoPartidaEnum avaliarHorizontal(String[][] estadoTabuleiro) {
		for (int i = 0; i < 3; i++) {
			if(EstadoPartidaEnum.FINALIZADO.equals(avaliarDimensao(estadoTabuleiro[i],DimensaoEnum.getByPosicaoENome(i+1, DimensaoEnum._1_HORIZONTAL.getNome())))) {
				return EstadoPartidaEnum.FINALIZADO; 
			}
		}
		return EstadoPartidaEnum.EM_ANDAMENTO;
	}

	private EstadoPartidaEnum avaliarDimensao(String[] dimensao,DimensaoEnum tipoDimensao) {
		String elemento = null;
		EstadoPartidaEnum estadoDimensao = EstadoPartidaEnum.FINALIZADO;
		for (String item : dimensao) {
			if (item == null) {
				this.existePosicaoNaoPreenchida = true;
				estadoDimensao = EstadoPartidaEnum.EM_ANDAMENTO;
				break;
			} else {
				if (elemento == null) {
					elemento = item;
				} else if (!elemento.equals(item)) {
					estadoDimensao = EstadoPartidaEnum.EM_ANDAMENTO;
					break;
				}
			}
		}
		if(EstadoPartidaEnum.FINALIZADO.equals(estadoDimensao)) {
			definirParametrosResultado(elemento,tipoDimensao);
		}
		return estadoDimensao;
	}

	private void definirParametrosResultado(String elemento, DimensaoEnum tipoDimensao) {
		this.dimensaoVitoria = tipoDimensao;
		this.tipoResultado = TipoResultadoEnum.getResultadoByTipo(elemento);
	}

	public DimensaoEnum obterDimensaoVitoria() {
		return this.dimensaoVitoria;
	}

	public TipoResultadoEnum obterResultado() {
		return this.tipoResultado;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

}
