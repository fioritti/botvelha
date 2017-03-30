package org.botvelha.repository.dao.usuario;

import static org.junit.Assert.*;

import org.botvelha.repository.dao.UsuarioDao;
import org.botvelha.repository.entity.Usuario;
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
		
		Usuario usuario = new Usuario(email);
		usuarioDao.salvar(usuario);
		Usuario usuarioEncontrado = usuarioDao.buscar(email);
		
		assertEquals(email, usuarioEncontrado.getEmail());
		
	}
	
	@After
	public void depois() {
		session.getTransaction().rollback();
		session.close();
	}
	
	
	

}
