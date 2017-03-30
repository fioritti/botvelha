package org.botvelha.repository.dao;

import org.botvelha.infrastructure.logger.Logger;
import org.botvelha.infrastructure.logger.LoggerFactory;
import org.botvelha.repository.entity.UsuarioEntity;
import org.hibernate.Session;

public class UsuarioDao {
	
	private final Session session;
	private Logger logger = LoggerFactory.getLogger(UsuarioDao.class);

	public UsuarioDao(Session session) {
		this.session = session;
	}

	public void salvar(UsuarioEntity usuario) {
		logger.info("Criando novo usuário : {}", usuario);
		session.save(usuario);
	}

	public UsuarioEntity buscar(String email) {
		logger.info("Buscando usuário com email : {}", email);
		return (UsuarioEntity) session
				.createQuery(
						"from Usuario u where u.email = :email ")
				.setParameter("email", email)
				.uniqueResult();
	}

}
