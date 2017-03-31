package org.botvelha.view;

import static org.junit.Assert.*;

import org.junit.Test;

public class PartidaConsoleViewTest {

	@Test
	public void deveObterEmailJogador() {
		String emailEsperado = "guileme13@gmail.com";
		PartidaConsoleView consoleView = new PartidaConsoleView();
		consoleView.solicitarEmailDoJogador();
		assertEquals(emailEsperado, consoleView.getEmailJogador());
	}

}
