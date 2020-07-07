package com.alevel.java.nix;

import com.alevel.java.nix.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

public class Demo {
    public static void main(String[] args) {


        Configuration cfg = new Configuration().configure();

        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            FinanceApp financeApp = new FinanceApp();
            financeApp.setSession(session);
            Account account = financeApp.login(1L, 1L);
            financeApp.createOperation(account, 2, 1000L);
            financeApp.createOperation(account, 4, -1500L);

            AccountStatement accountStatement = new AccountStatement();
            accountStatement.createAccountStatement(account, LocalDateTime.of(2020,6,15,0,0), LocalDateTime.of(2020,7,15,0,0));


        }

    }
}
