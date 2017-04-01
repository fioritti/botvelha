package org.botvelha.domain.jogador.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.botvelha.repository.dao.PartidaDao;
import org.botvelha.repository.entity.PartidaEntity;
import org.botvelha.repository.infrastructure.CriadorDeSessao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SmartBot extends Bot {
	
	private PartidaDao partidaDao;
	private Integer contadorDePosicoes;
 	private List<PosicaoTabuleiroEnum> estrategiaUtilizada;
 	public List<PosicaoTabuleiroEnum> getEstrategiaUtilizada() {
		return estrategiaUtilizada;
	}


	private List<List<PosicaoTabuleiroEnum>> catalogoDeEstrategias;
	
	public SmartBot() {
		this.partidaDao = new PartidaDao(new CriadorDeSessao().getSession());
	}
	

	@Override
	public PosicaoTabuleiroEnum fazerJogada(List<PosicaoTabuleiroEnum> listaJogadasNoTabuleiro) throws Exception {
		List<PosicaoTabuleiroEnum> estrategia =  obterEstrategiaUtilizada();
		
		while(estrategia !=null) {
			PosicaoTabuleiroEnum pte = null;
			try {
				 pte = estrategia.get(obterProximaPosicao());
			} catch (Exception e) {}
			if(pte!=null && essaPosicaoEstaLivre(pte,listaJogadasNoTabuleiro)) {
				return pte;
			}else {
				estrategia = obterEstrategiaComCenarioAtual(listaJogadasNoTabuleiro);
			}
		}
		return super.fazerJogada(listaJogadasNoTabuleiro);
	}

	private List<PosicaoTabuleiroEnum> obterEstrategiaComCenarioAtual(List<PosicaoTabuleiroEnum> listaJogadasNoTabuleiro) throws Exception {
		List<List<PosicaoTabuleiroEnum>> estragegiasRefinadas = new ArrayList<List<PosicaoTabuleiroEnum>>();
		int qtdJogadas = listaJogadasNoTabuleiro.size();
		for (List<PosicaoTabuleiroEnum> lista : this.catalogoDeEstrategias) {
			if(lista.size() <= qtdJogadas) {
				continue;
			}
			boolean estrategiaUtil = true;
			for(int i=0;i<qtdJogadas;i++) {
				
				if(!listaJogadasNoTabuleiro.get(i).equals(lista.get(i))) {
					estrategiaUtil = false;
					break;
				}
			}
			if(estrategiaUtil) {
				estragegiasRefinadas.add(lista);
			}
		}
		if(estragegiasRefinadas.size()>0) {
			this.catalogoDeEstrategias = estragegiasRefinadas;
			this.estrategiaUtilizada = procurarPorEstrategiaMaisVencedora(this.catalogoDeEstrategias);
			return this.estrategiaUtilizada;
		}else {
			return null;
		}
	}


	private boolean essaPosicaoEstaLivre(PosicaoTabuleiroEnum pte, List<PosicaoTabuleiroEnum> listaJogadasNoTabuleiro) {
		for (PosicaoTabuleiroEnum pteNoTabuleiro : listaJogadasNoTabuleiro) {
			if(pte.equals(pteNoTabuleiro)) {
				return false;
			}
		}
		return true;
	}


	private int obterProximaPosicao() {
		if(contadorDePosicoes == null) {
			contadorDePosicoes = iniciouPartida?0:1;
			return contadorDePosicoes;
		}else {
			return ++contadorDePosicoes;
		}
	}


	private List<PosicaoTabuleiroEnum> obterEstrategiaUtilizada() throws Exception {
		if(estrategiaUtilizada == null) {
			consultarCatalogoDeEstrategias();
			if(this.catalogoDeEstrategias !=null && this.catalogoDeEstrategias.size() > 0) {
				this.estrategiaUtilizada = procurarPorEstrategiaMaisVencedora(this.catalogoDeEstrategias);
			}else {
				return null;
			}
		}
		return this.estrategiaUtilizada;
	}

	private List<PosicaoTabuleiroEnum> procurarPorEstrategiaMaisVencedora(
			List<List<PosicaoTabuleiroEnum>> catalogo) throws Exception {
		Map<String, Integer> mapContadorDeEstrategia = new HashMap<String, Integer>();
		for (List<PosicaoTabuleiroEnum> list : catalogo) {
			Integer contador = mapContadorDeEstrategia.get(new ObjectMapper().writeValueAsString(list));
			if(contador == null) {
				contador = 0;
				mapContadorDeEstrategia.put(new ObjectMapper().writeValueAsString(list), contador);
			}
			contador++;
		}
		String keyMelhorEstrategia = "";
		Integer qtdMaiorAtual = null;
		for(String key : mapContadorDeEstrategia.keySet()) {
			Integer qtd = mapContadorDeEstrategia.get(key);
			if(qtdMaiorAtual == null) {
				keyMelhorEstrategia = key;
				qtdMaiorAtual = qtd;
			}else {
				if(qtd > qtdMaiorAtual) {
					keyMelhorEstrategia = key;
					qtdMaiorAtual = qtd;
				}
			}
		}
		return Arrays.asList(new ObjectMapper().readValue(keyMelhorEstrategia, PosicaoTabuleiroEnum[].class));
	}


	private void consultarCatalogoDeEstrategias() throws Exception {
		if(this.catalogoDeEstrategias==null) {
			this.catalogoDeEstrategias = new ArrayList<List<PosicaoTabuleiroEnum>>();
			List<PartidaEntity> listaPartidas = null;
			if(this.iniciouPartida) {
				listaPartidas = this.partidaDao.obterPartidasVencidasPorQuemIniciou();
			}else {
				listaPartidas = this.partidaDao.obterPartidasVencidasPorQuemIniciouEmSegundo();
			}
			for (PartidaEntity partidaEntity : listaPartidas) {
				this.catalogoDeEstrategias.add(transformarEmListaPosicaoEnum(partidaEntity.getJsonListaJogadas()));
			}
		}
	}


	private List<PosicaoTabuleiroEnum> transformarEmListaPosicaoEnum(String jsonListaJogadas) throws Exception {
		PosicaoTabuleiroEnum[] jogadas = new ObjectMapper().readValue(jsonListaJogadas, PosicaoTabuleiroEnum[].class);
		return Arrays.asList(jogadas);
	}

}
