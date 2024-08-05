package com.employeemanagement.connectionManager;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class HibernateConnection {
    private static SessionFactory factory = null;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Unable to create session Factory." + e);
        }
    }

    public static SessionFactory getFactory() {
        return factory;
    }

}