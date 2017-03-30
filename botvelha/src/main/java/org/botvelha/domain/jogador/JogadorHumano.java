package org.botvelha.domain.jogador;

public class JogadorHumano implements Jogador {

	private String email;

	public JogadorHumano(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
