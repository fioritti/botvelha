package org.botvelha.repository.dao.usuario;

import static org.junit.Assert.*;

import org.botvelha.repository.dao.UsuarioDao;
import org.botvelha.repository.entity.UsuarioEntity;
import org.botvelha.repository.infrastructure.CriadorDeSessao;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsuarioDaoTest {
	
	private Session session;
	private UsuarioDao usuarioDao;
	
	@Before
	public void antes() {
		session = new CriadorDeSessao().getSession();
		usuarioDao = new UsuarioDao(session);
		session.beginTransaction();
	}
	

	@Test
	public void deveCriarUsuario() {
		String email = "guileme13@gmail.com";
		
		UsuarioEntity usuario = new UsuarioEntity(email);
		usuarioDao.salvar(usuario);
		UsuarioEntity usuarioEncontrado = usuarioDao.buscar(email);
		
		assertEquals(email, usuarioEncontrado.getEmail());
		
	}
	
	@After
	public void depois() {
		session.getTransaction().rollback();
		session.close();
	}
	
	
	

}
