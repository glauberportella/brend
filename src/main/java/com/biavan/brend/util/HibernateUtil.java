package com.biavan.brend.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static final SessionFactory sessionFactory;  
	  
    static {  
        try {  
        	sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {  
            System.err.println("Não foi possivel criar a SessionFactory." + ex);  
            throw new ExceptionInInitializerError(ex);  
        }  
    }  

    public static Session getSession() {  
        return sessionFactory.openSession();  
    }  
	
	public static void gerarBanco() {
		// Carrega as configurações do arquivo
		// hibernate.cfg.xml
		Configuration conf = new Configuration();
		conf.configure();
		SchemaExport se = new SchemaExport(conf);
		// Executa a operação da criação do Banco de Dados
		se.create(true, true);
	}

}
