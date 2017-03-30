package org.botvelha.repository.dao;

import org.botvelha.infrastructure.logger.Logger;
import org.botvelha.infrastructure.logger.LoggerFactory;
import org.botvelha.repository.entity.PartidaEntity;
import org.hibernate.Session;

public class PartidaDao {

	private final Session session;
	private Logger logger = LoggerFactory.getLogger(PartidaDao.class);

	
	public PartidaDao(Session session) {
		this.session = session;
	}

	public void salvar(PartidaEntity partida) {
		logger.info("Salvando partida : {}", partida);
		session.save(partida);
	}
	
	public PartidaEntity buscar(Long id) {
		logger.info("Buscando partida com ID : {}", id);
		return (PartidaEntity) session
				.createQuery(
						"from PartidaEntity p where p.id = :id ")
				.setParameter("id", id)
				.uniqueResult();
	}

	
}
