package org.botvelha.domain.partida;

import static org.junit.Assert.*;

import org.botvelha.repository.entity.Usuario;
import org.junit.Test;

public class PartidaTest {

	@Test
	public void deveAssociarUsuarioComElemento() {
		Usuario guileme13 = new Usuario("guileme13@gmail.com");
		Usuario boot = new Usuario("bot@botvelha.com");
		Partida partida = new Partida(guileme13,boot);
		partida.iniciar();
		assertTrue(partida.getElementoPorJogador(guileme13)!=null);
	}
	
	@Test
	public void deveVerificarQueProximoJogadoresNaoIgualAoAnterior() {
		Usuario guileme13 = new Usuario("guileme13@gmail.com");
		Usuario boot = new Usuario("bot@botvelha.com");
		Partida partida = new Partida(guileme13,boot);
		partida.iniciar();
		Usuario u1 = partida.obterProximoJogador();
		Usuario u2 = partida.obterProximoJogador();
		assertTrue(u1!=u2);
	}
	
	public void deveFazerJogadaEJogoContinuaEmAndameto() {
		
		
	}
	
	
	public void deveArmazenarJogadas() {
		
	}
	
	public void deveArmazenarVencedor() {
		
	}
	
	public void deveSalvarPartida() {
		
	}

}
