package org.botvelha.repository.dao;

import java.util.List;

import org.botvelha.infrastructure.logger.Logger;
import org.botvelha.infrastructure.logger.LoggerFactory;
import org.botvelha.repository.entity.PartidaEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PartidaDao {

	private final Session session;
	private Logger logger = LoggerFactory.getLogger(PartidaDao.class);

	
	public PartidaDao(Session session) {
		this.session = session;
	}

	public void salvar(PartidaEntity partida) {
		logger.debug("Salvando partida : {}", partida);
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

	public List<PartidaEntity> obterPartidasVencidasPorQuemIniciou() {
		logger.info("Buscando partida vencidas por quem iniciou");
		List list = session
		.createQuery(
				"from PartidaEntity p where p.elementoVencedor = p.elementoIniciouPartida")
		.list();
		return list;
	}

	public List<PartidaEntity> obterPartidasVencidasPorQuemIniciouEmSegundo() {
		logger.info("Buscando partida vencidas por quem iniciou em segundo");
		List list = session
		.createQuery(
				"from PartidaEntity p where p.elementoVencedor != p.elementoIniciouPartida")
		.list();
		return list;
	}


	
}
