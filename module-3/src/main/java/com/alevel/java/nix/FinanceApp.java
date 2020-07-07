package com.alevel.java.nix;

import com.alevel.java.nix.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FinanceApp {


    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Account login(Long userId, Long accountId) {

        Account account = null;
        List<Account> accounts = null;
        try {

            List<User> userList = session.createQuery("from User ", User.class)
                    .getResultList();
            for (User user : userList) {
                if (user.getId().equals(userId)) {
                    accounts = user.getAccounts();
                }
            }

            if (accounts == null) throw new Exception("Not valid userID");

            for (Account userAccount : accounts) {
                if (userAccount.getId().equals(accountId)) {
                    account = userAccount;
                }
            }

            if (account == null) throw new Exception("Not valid accountId");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }


    private void createIncomeOperation(Account account, Integer categoryId, Long amount) {

        Transaction transaction = session.beginTransaction();

        try {

            IncomeCategory category = null;
            List<IncomeCategory> categoryList = session.createQuery("FROM IncomeCategory ", IncomeCategory.class)
                    .getResultList();
            for (IncomeCategory incomeCategory : categoryList) {
                if (incomeCategory.getId().equals(categoryId)) {
                    category = incomeCategory;
                }
            }
            if (category == null) throw new Exception("Not valid category ID");

            Income operation = new Income(account, amount);
            operation.getCategories().add(category);

            account.setAmount(account.getAmount() + amount);
            session.save(operation);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    private void createIncomeOperation(Account account, String categoryName, Long amount) {

        Transaction transaction = session.beginTransaction();

        try {

            IncomeCategory category = null;
            List<IncomeCategory> categoryList = session.createQuery("FROM IncomeCategory ", IncomeCategory.class)
                    .getResultList();
            for (IncomeCategory incomeCategory : categoryList) {
                if (incomeCategory.getName().equals(categoryName)) {
                    category = incomeCategory;
                }
            }
            if (category == null) throw new Exception("Not valid category Name");

            Income operation = new Income(account, amount);
            operation.getCategories().add(category);

            account.setAmount(account.getAmount() + amount);
            session.save(operation);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    private void createExpanseOperation(Account account, Integer categoryId, Long amount) {

        Transaction transaction = session.beginTransaction();
        try {

            if (account.getAmount() < amount) throw new Exception("Insufficient funds");
            ExpenseCategory category = null;
            List<ExpenseCategory> categoryList = session.createQuery("FROM ExpenseCategory ", ExpenseCategory.class)
                    .getResultList();
            for (ExpenseCategory expenseCategory : categoryList) {
                if (expenseCategory.getId().equals(categoryId)) {
                    category = expenseCategory;
                }
            }
            if (category == null) throw new Exception("Not valid category ID");

            Expense operation = new Expense(account, amount);
            operation.getCategories().add(category);

            account.setAmount(account.getAmount() + amount);
            session.save(operation);

            transaction.commit();


        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    private void createExpanseOperation(Account account, String categoryName, Long amount) {

        Transaction transaction = session.beginTransaction();
        try {

            if (account.getAmount() < amount) throw new Exception("Insufficient funds");
            ExpenseCategory category = null;
            List<ExpenseCategory> categoryList = session.createQuery("FROM ExpenseCategory ", ExpenseCategory.class)
                    .getResultList();
            for (ExpenseCategory expenseCategory : categoryList) {
                if (expenseCategory.getName().equals(categoryName)) {
                    category = expenseCategory;
                }
            }
            if (category == null) throw new Exception("Not valid category name");

            Expense operation = new Expense(account, amount);
            operation.getCategories().add(category);

            account.setAmount(account.getAmount() + amount);
            session.save(operation);


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }


    }

    public void createOperation(Account account, Integer categoryId, Long amount) {
        if (amount == 0) {
            try {
                throw new Exception("Amount cannot be zero");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (amount > 0) {
            createIncomeOperation(account, categoryId, amount);
        }
        if (amount < 0) {
            createExpanseOperation(account, categoryId, amount);
        }


    }

    public void createOperation(Account account, String categoryName, Long amount) {
        if (amount == 0) {
            try {
                throw new Exception("Amount cannot be zero");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (amount > 0) {
            createIncomeOperation(account, categoryName, amount);
        }
        if (amount < 0) {
            createExpanseOperation(account, categoryName, amount);
        }

    }
}
