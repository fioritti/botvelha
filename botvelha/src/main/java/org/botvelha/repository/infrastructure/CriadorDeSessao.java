package org.botvelha.repository.infrastructure;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CriadorDeSessao {
	
	public Session getSession() {
		Configuration config = new Configuration();
		config.configure(new File("hibernate.cfg.xml"));
		SessionFactory factory = config.buildSessionFactory();
		return factory.openSession();
	}	

}
