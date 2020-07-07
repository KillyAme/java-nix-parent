package com.alevel.java.nix;

import com.alevel.java.nix.entity.Account;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class AccountStatement {

    public static final String PATH = "module-3/src/main/resources/statement.csv";

    public void createAccountStatement(Account account, LocalDateTime from, LocalDateTime to) {
        Connection connection = getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT amount, timestamp FROM operations WHERE (account_id =?) AND timestamp BETWEEN ? AND ?")) {
            statement.setLong(1, account.getId());
            statement.setTimestamp(2, new Timestamp(from.toInstant(ZoneOffset.UTC).toEpochMilli()));
            statement.setTimestamp(3, new Timestamp(to.toInstant(ZoneOffset.UTC).toEpochMilli()));
            ResultSet resultSet = statement.executeQuery();
            long amountOfIncome = 0;
            long amountOfExpense = 0;
            long net;

            try (FileWriter writer = new FileWriter(PATH)) {

                while (resultSet.next()) {
                    long amount = resultSet.getLong(1);
                    if (amount > 0) {
                        amountOfIncome = amountOfIncome + amount;
                    }
                    if (amount < 0) {
                        amountOfExpense = amountOfExpense + amount;
                    }
                    writer.write(amount + ",");
                    writer.write(resultSet.getTimestamp(2).toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\n");
                }
                net = amountOfIncome + amountOfExpense;
                writer.write("Amount of income" + "," + amountOfIncome + "\n");
                writer.write("Amount of expence" + "," + amountOfExpense + "\n");
                writer.write("Net" + "," + net + "\n");
                writer.write("Balance" + "," + account.getAmount());
            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Properties props = loadProperties();
        String url = props.getProperty("url");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Properties loadProperties() {
        Properties props = new Properties();
        try (var input = new FileInputStream("module-3/src/main/resources/com.alevel.java.nix/AccountStatement.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return props;
    }
}

