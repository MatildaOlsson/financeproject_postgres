package personalfinance.repositories;

import personalfinance.models.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PostgresTransactionRepository {

    private Connection connection;
    protected String tableName = "transactionTableExmample";

    public PostgresTransactionRepository (String tableName) {
        this.tableName = tableName;
    }
    
    public PostgresTransactionRepository(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);

        try (
            Statement createTransactionTable = connection.createStatement()) {
            createTransactionTable.execute("CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                    "id SERIAL PRIMARY KEY," +
                    "amount NUMERIC(10,2) NOT NULL," +
                    "currency VARCHAR(15)," +
                    "is_income BOOLEAN NOT NULL," +
                    "date DATE," +
                    "week INT," +
                    "transaction_info VARCHAR(25))");
        }

    }

    public void save (Transaction transaction) throws Exception {
        String sql = "INSERT INTO " + tableName + "(amount, currency, is_income, date, week, transaction_info) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement saveStatement = connection.prepareStatement(sql)) {
            saveStatement.setBigDecimal(1, transaction.getAmount());
            saveStatement.setString(2, transaction.getCurrency());
            saveStatement.setBoolean(3,transaction.getIsIncome());
            saveStatement.setDate(4, (Date) transaction.getDate());
            saveStatement.setInt(5, transaction.getWeek());
            saveStatement.setString(6, transaction.getTrancastionInfo());
            System.out.println("Success!!");

            if (saveStatement.executeUpdate() != 1) {
                throw new SQLException("Failed to insert user");
            }

        }
    }

    public List<Transaction> getAll() throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        List <Transaction> transactionList = new ArrayList<>();
        try (PreparedStatement getAllStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = getAllStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String currency = resultSet.getString("currency");
                boolean isIncome = resultSet.getBoolean("is_income");
                Date date = resultSet.getDate("date");
                int week = resultSet.getInt("week");
                String transactionInfo = resultSet.getString("transaction_info");
                Transaction transaction = new Transaction(id, amount, isIncome, currency, date, week, transactionInfo);
                transactionList.add(transaction);
            }
            return transactionList;
        }
    }
    public void deleteTransactionById (int id) throws SQLException {
        String sql = "DELETE FROM " + tableName +
                " WHERE id = ?";
        try(PreparedStatement deleteStatement = connection.prepareStatement(sql)) {

            deleteStatement.setInt(1,id);

            if (deleteStatement.executeUpdate() != 1) {
                throw new SQLException("Failed to delete transaction");
            }

        }
    }
}