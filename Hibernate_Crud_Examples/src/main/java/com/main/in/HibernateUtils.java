package com.main.in;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

	private static SessionFactory sessionFactory = null;

	private static StandardServiceRegistry standardServiceRegistry = null;

	static {
		try {
			if (sessionFactory == null) {
				standardServiceRegistry = new StandardServiceRegistryBuilder().configure("Hibernate.cfg.xml").build();
				MetadataSources metaDataSources = new MetadataSources(standardServiceRegistry);
				Metadata metaData = metaDataSources.getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (standardServiceRegistry != null) {
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
