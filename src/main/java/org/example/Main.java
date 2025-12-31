package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String connectionString = "jdbc:postgresql://localhost/personalfinance?user=postgres&password=mysecretpassword";
        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionString);
            System.out.println("You're connected to the database!");
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }
try {
    Statement createTableStatement = connection.createStatement();
    createTableStatement.execute("CREATE TABLE transactions1 (id SERIAL PRIMARY KEY, amount INT)");
} catch (SQLException e) {
    e.printStackTrace();
    return;
}

    }
}