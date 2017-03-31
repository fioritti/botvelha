package org.botvelha.repository.dao.partida;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.botvelha.domain.jogador.Jogador;
import org.botvelha.domain.jogador.JogadorHumano;
import org.botvelha.domain.jogador.bot.DummyBot;
import org.botvelha.domain.partida.Partida;
import org.botvelha.domain.partida.PartidaFinalizadaException;
import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.botvelha.repository.dao.PartidaDao;
import org.botvelha.repository.entity.PartidaEntity;
import org.botvelha.repository.infrastructure.CriadorDeSessao;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class PartidaDaoTest {
	
	private PartidaDao partidaDao;
	private Session session;
	
	@Before
	public void antes() {
		session = new CriadorDeSessao().getSession();
		partidaDao = new PartidaDao(session);
		session.beginTransaction();
	}

	@Test
	public void deveSalvarPartida() throws PosicaoJaPreenchidaException, PartidaFinalizadaException, JsonProcessingException {
		Jogador guileme13 = new JogadorHumano("guileme13@gmail.com");
		Jogador bot = new DummyBot();
		Partida partida = new Partida(guileme13,bot);
		partida.iniciar();
		Jogador jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_0);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_1);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._1_1);
		jogador = partida.obterProximoJogador();
		partida.fazerJogada(jogador,PosicaoTabuleiroEnum._0_2);
		
		PartidaEntity partidaEntity = new PartidaEntity(partida);
		partidaDao.salvar(partidaEntity);
		
		PartidaEntity partidaDoBanco = partidaDao.buscar(partidaEntity.getId());
		assertTrue(partidaDoBanco!=null);
	}
	
	
	@Test
	public void deveObterPartidasVencidasPorQuemIniciou() {
		List<PartidaEntity> partidas = partidaDao.obterPartidasVencidasPorQuemIniciou();
		assertTrue(!partidas.isEmpty());
	}
	
	@Test
	public void deveObterPartidasVencidasPorQuemIniciouEmSegundo() {
		List<PartidaEntity> partidas = partidaDao.obterPartidasVencidasPorQuemIniciouEmSegundo();
		assertTrue(!partidas.isEmpty());
	}
	
	@After
	public void depois() {
		session.getTransaction().rollback();
		session.close();
	}	


}
