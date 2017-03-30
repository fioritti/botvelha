package org.botvelha.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsuarioEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;

	public UsuarioEntity(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + "]";
	}

}
