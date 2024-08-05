package com.employeemanagement.connectionmanager;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * <p>
 * This class is used for creating the connection using hibernate
 * </p>
 *
 * @author JeevithaKesavaraj
 * @version 1.0
 * @since 2024/07/30
 */
public class HibernateConnection {
    private static SessionFactory factory = null;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Unable to create session Factory." + e);
        }
    }

    /**
     * This method is for getting session fatory object
     */
    public static SessionFactory getFactory() {
        return factory;
    }

}