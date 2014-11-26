package org.blueoxygen.cimande.core.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.event.spi.EventType;
import org.meruvian.yama.service.jpa.HibernateListenerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CimandeJpaListenerConfig {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public HibernateListenerConfigurer hibernateListenerConfigurer() {
		HibernateListenerConfigurer config = new HibernateListenerConfigurer();
		config.setEntityManagerFactory(entityManagerFactory);
		config.appendListener(EventType.PRE_INSERT, logInformationListener());
		config.appendListener(EventType.PRE_UPDATE, logInformationListener());

		return config;
	}
	
	@Bean
	public CimandeLogInformationListener logInformationListener() {
		return new CimandeLogInformationListener();
	}
}
