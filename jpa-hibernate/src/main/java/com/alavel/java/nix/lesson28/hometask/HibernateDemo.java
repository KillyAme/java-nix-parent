package com.alavel.java.nix.lesson28.hometask;


import com.alavel.java.nix.lesson28.hometask.model.InputAndOutputInDataBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDemo {

    public static void main(String[] args) {


        Configuration cfg = new Configuration().configure();

        try(SessionFactory sessionFactory= cfg.buildSessionFactory();
            Session session = sessionFactory.openSession()) {

            InputAndOutputInDataBase example = new InputAndOutputInDataBase();

            example.loadData(session);
            example.createVertexes();
            example.output(session);
        }
    }
}
