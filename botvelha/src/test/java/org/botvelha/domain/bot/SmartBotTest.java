package org.botvelha.domain.bot;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.botvelha.domain.jogador.JogadorHumano;
import org.botvelha.domain.jogador.bot.Bot;
import org.botvelha.domain.jogador.bot.SmartBot;
import org.botvelha.domain.partida.Partida;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.botvelha.repository.dao.PartidaDao;
import org.botvelha.repository.entity.PartidaEntity;
import org.botvelha.repository.infrastructure.CriadorDeSessao;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SmartBotTest {

	private PartidaDao partidaDao;
	private Session session;
	
	@Before
	public void iniciar() {
		session = new CriadorDeSessao().getSession();
		session.beginTransaction();
		partidaDao = new PartidaDao(session);
	}

	@Test
	public void deveEscolherEstrategiaComecandoPartida() throws Exception {
		Bot bot = new SmartBot();
		Partida partida = new Partida(new JogadorHumano("guileme13@gmail.com"),bot);
		partida.iniciar();
		bot.informaQuemIniciaPartida(bot);
		PosicaoTabuleiroEnum jogada = bot.fazerJogada(partida.getListaDeJogadas());
		assertTrue(jogada!=null);
	}

	@Test
	public void deveEscolherEstrategiaIniciandoComoSegudo() throws Exception {
		Bot bot = new SmartBot();
		Partida partida = new Partida(new JogadorHumano("guileme13@gmail.com"),bot);
		partida.iniciar();
		bot.informaQuemIniciaPartida(new JogadorHumano("teste@teste.com"));
		PosicaoTabuleiroEnum jogada = bot.fazerJogada(partida.getListaDeJogadas());
		assertTrue(jogada!=null);
	}
	
	@Test
	public void deveUtilizarEstrategiaIniciandoDaListaDeEstrategiasVencedoras() throws Exception {
		SmartBot bot = new SmartBot();
		JogadorHumano jogadorHumano = new JogadorHumano("guileme13@gmail.com");
		Partida partida = new Partida(jogadorHumano,bot);
		partida.iniciar();
		bot.informaQuemIniciaPartida(bot);
		partida.fazerJogada(bot, bot.fazerJogada(partida.getListaDeJogadas()));
		partida.fazerJogada(jogadorHumano, PosicaoTabuleiroEnum._0_0);
		partida.fazerJogada(bot, bot.fazerJogada(partida.getListaDeJogadas()));
		partida.fazerJogada(jogadorHumano, PosicaoTabuleiroEnum._0_1);
		partida.fazerJogada(bot, bot.fazerJogada(partida.getListaDeJogadas()));
		partida.fazerJogada(jogadorHumano, PosicaoTabuleiroEnum._2_0);
		PosicaoTabuleiroEnum fazerJogada = bot.fazerJogada(partida.getListaDeJogadas());
		
		List<PosicaoTabuleiroEnum> estrategiaBot = bot.getEstrategiaUtilizada();
		assertTrue(estaDentroDasEstrageiasVencdedorasParaOCenario(estrategiaBot,partidaDao.obterPartidasVencidasPorQuemIniciou()));
	}
	
	private boolean estaDentroDasEstrageiasVencdedorasParaOCenario(List<PosicaoTabuleiroEnum> estrategiaBot,
			List<PartidaEntity> obterPartidasVencidasPorQuemIniciou) throws Exception {
		boolean estrategiaFazParteDasEstrategiasVencedoras = false;
		for (PartidaEntity partidaEntity : obterPartidasVencidasPorQuemIniciou) {
			String jsonListaJogadas = partidaEntity.getJsonListaJogadas();
			PosicaoTabuleiroEnum[] estrategia = new ObjectMapper().readValue(jsonListaJogadas, PosicaoTabuleiroEnum[].class);
			if(!(estrategia.length == estrategiaBot.size())) {
				continue;
			}
			for(int i=0;i<estrategia.length;i++) {
				if(!(estrategiaBot.get(i).equals(estrategia[i]))) {
					continue;
				}
			}
			estrategiaFazParteDasEstrategiasVencedoras = true;
			break;
		}
		return estrategiaFazParteDasEstrategiasVencedoras;
	}

	@After
	public void finalizar() {
		session.getTransaction().rollback();
		session.close();
	}
	
	
	
	

	

}
