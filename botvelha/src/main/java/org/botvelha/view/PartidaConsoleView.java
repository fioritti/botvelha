package org.botvelha.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.botvelha.domain.jogador.Jogador;
import org.botvelha.domain.jogador.JogadorHumano;
import org.botvelha.domain.jogador.bot.Bot;
import org.botvelha.domain.jogador.bot.DummyBot;
import org.botvelha.domain.partida.EntradaInvalidaException;
import org.botvelha.domain.partida.EstadoPartidaEnum;
import org.botvelha.domain.partida.Partida;
import org.botvelha.domain.partida.PartidaFinalizadaException;
import org.botvelha.domain.partida.PosicaoInvalidaException;
import org.botvelha.domain.tabuleiro.PosicaoJaPreenchidaException;
import org.botvelha.domain.tabuleiro.PosicaoTabuleiroEnum;
import org.botvelha.domain.tabuleiro.TipoElementoEnum;

import com.fasterxml.jackson.core.JsonProcessingException;

public class PartidaConsoleView {
	
	private static final String M_SOLICITA_EMAIL_JOGADOR = "Informe seu e-mail:";
	private static final String M_PARTIDA_INICIADA = "Partida Iniciada.";
	private static final String M_ELEMENTOS_DOS_JOGADORES = "Você usará o elemento %s. O Bot será o %s";
	private static final String M_BOT_INICIA_PARTIDA = "O Bot inicia a partida.";
	private static final String M_VOCE_INICIA_PARTIDA = "Você inicia partida.";
	private static final String M_ESCOLHA_UMA_POSICAO_E_FACA_JOGADA = "Faça sua jogada, escolha uma posição de 1 a 9 para jogar.";
	private static final String M_VEZ_DO_BOT = "Agora é a vez do bot";
	private static final String M_SUA_VEZ = "Agora é a sua vez";
	private static final String M_PARTIDA_ENCERRADA = "Terminiou.";
	private static final String M_BOT_VENCEU = "O Bot venceu!!";
	private static final String M_VOCE_VENCEU = "Você venceu";
	
	private Partida partida;
	private JogadorHumano jogadorHumano;
	private Bot bot;
	private Map<Integer, PosicaoTabuleiroEnum> mapNumeroPosicaoTabuleiro;
	private Scanner scanner;
	
	public static void main(String[] args) throws JsonProcessingException, PartidaFinalizadaException, PosicaoJaPreenchidaException, EntradaInvalidaException {
		PartidaConsoleView partidaConsoleView = new PartidaConsoleView();
		partidaConsoleView.executarJogo();
	}
	
	public PartidaConsoleView() {
		this.mapNumeroPosicaoTabuleiro = new HashMap<Integer, PosicaoTabuleiroEnum>();
		for(int i=0;i<PosicaoTabuleiroEnum.values().length;i++) {
			this.mapNumeroPosicaoTabuleiro.put(i+1, PosicaoTabuleiroEnum.values()[i]);
		}
	}
	
	public void executarJogo() throws JsonProcessingException, PartidaFinalizadaException, PosicaoJaPreenchidaException, EntradaInvalidaException {
		this.iniciarPartida();
		while(this.avaliarPartida());
	}
	
	public void solicitarEmailDoJogador() {
		System.out.println(M_SOLICITA_EMAIL_JOGADOR);
		Scanner scanner = new Scanner(System.in);
		String emailJogador = scanner.nextLine();
		scanner.close();
		this.jogadorHumano = new JogadorHumano(emailJogador);
	}
	
	public void iniciarPartida() throws PartidaFinalizadaException, JsonProcessingException, PosicaoJaPreenchidaException, EntradaInvalidaException {
		this.scanner = new Scanner(System.in);
		this.bot = new DummyBot();
		this.partida = new Partida(jogadorHumano, bot);
		this.partida.iniciar();
		System.out.println(M_PARTIDA_INICIADA);
		TipoElementoEnum elementoJogadorHumano = this.partida.getElementoPorJogador(jogadorHumano);
		TipoElementoEnum elementoBot = this.partida.getElementoPorJogador(bot);
		System.out.println(String.format(M_ELEMENTOS_DOS_JOGADORES, elementoJogadorHumano,elementoBot) );
		identificarQuemIniciaPartida();
	}
	
	public boolean avaliarPartida() throws PartidaFinalizadaException, JsonProcessingException, PosicaoJaPreenchidaException, EntradaInvalidaException {
		if(EstadoPartidaEnum.EM_ANDAMENTO.equals(this.partida.getEstado())) {
			Jogador proximoJogador = this.partida.obterProximoJogador();
			if(proximoJogador instanceof Bot) {
				System.out.println(M_VEZ_DO_BOT);
				PosicaoTabuleiroEnum jogadaBot = this.bot.fazerJogada(partida.getListaDeJogadas());
				this.partida.fazerJogada(bot, jogadaBot);
				exibirTabuleiro();
			}else {
				System.out.println(M_SUA_VEZ);
				obterJogadaHumano();
			}
			
			return true;
		}else {
			System.out.println(M_PARTIDA_ENCERRADA);
			exibirTabuleiro();
			Jogador obterVencedor = this.partida.obterVencedor();
			if(obterVencedor instanceof Bot) {
				System.out.println(M_BOT_VENCEU);
			}else {
				System.out.println(M_VOCE_VENCEU);
			}
			this.scanner.close();
		}
		return false;
	}
	

	private void identificarQuemIniciaPartida() throws JsonProcessingException, PosicaoJaPreenchidaException, PartidaFinalizadaException, EntradaInvalidaException {
		Jogador primeiroJogador = this.partida.obterProximoJogador();
		if(primeiroJogador instanceof Bot) {
			System.out.println(M_BOT_INICIA_PARTIDA);
			PosicaoTabuleiroEnum jogadaBot = this.bot.fazerJogada(partida.getListaDeJogadas());
			this.partida.fazerJogada(bot, jogadaBot);
			exibirTabuleiro();
		}else {
			System.out.println(M_VOCE_INICIA_PARTIDA);
			exibirTabuleiro();
			obterJogadaHumano();
		}
	}

	private void obterJogadaHumano() throws JsonProcessingException,  PartidaFinalizadaException {
		System.out.println(M_ESCOLHA_UMA_POSICAO_E_FACA_JOGADA);
		String jogada = this.scanner.nextLine();
		try {
			PosicaoTabuleiroEnum posicaoTabuleiroEnum =  transformarJogadaEmPosicaoTabuleiro(jogada);
			this.partida.fazerJogada(this.jogadorHumano, posicaoTabuleiroEnum);
			exibirTabuleiro();
		} catch (PosicaoInvalidaException e) {
			System.out.println(e.getMessage());
			obterJogadaHumano();
		} catch (EntradaInvalidaException e) {
			System.out.println(e.getMessage());
			obterJogadaHumano();
		}catch (PosicaoJaPreenchidaException e) {
			System.out.println(e.getMessage());
			obterJogadaHumano();
		}
	}

	private PosicaoTabuleiroEnum transformarJogadaEmPosicaoTabuleiro(String jogada) throws EntradaInvalidaException, PosicaoInvalidaException {
		try {
			Integer posicao = Integer.parseInt(jogada);
			PosicaoTabuleiroEnum pte = deParaNumeroPosicao(posicao);
			return pte;
		} catch (NumberFormatException e) {
			throw new EntradaInvalidaException("Entrada inválida. Informe um número.");
		}
	}

	private PosicaoTabuleiroEnum deParaNumeroPosicao(Integer posicao) throws PosicaoInvalidaException {
		PosicaoTabuleiroEnum pte = this.mapNumeroPosicaoTabuleiro.get(posicao);
		if(pte==null) {
			throw new PosicaoInvalidaException("Posicao inválida. Escolha uma posição válida");
		}
		return pte;
	}

	public String getEmailJogador() {
		return this.jogadorHumano.getEmail();
	}
	
	public void exibirTabuleiro() {
		TabuleiroView tabuleiroView = new TabuleiroView(this.partida.getAvaliadorPartida());
		System.out.println(tabuleiroView.verTabuleiro());
	}

	
	
	
	
}
