package personalfinance.repositories;

import personalfinance.models.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


public class PostgresTransactionRepository {

    private Connection connection;
    protected String tableName = "transactionTableExmample2";

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
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

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
        try(PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,id);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("Failed to delete transaction");
            }

        }
    }
    private String validateSql(String type) {
        if (type.equalsIgnoreCase("year")) {
            return "YEAR";
        } else if (type.equalsIgnoreCase("month")) {
            return "MONTH";
        } else if (type.equalsIgnoreCase("day")) {
            return "DAY";
        } else {
            throw new InputMismatchException("WRONG INPUT! TYPE MUST BE YEAR, MONTH OR DAY");
        }
    }

    public List<Transaction> filterByYearMonthOrDay(int value, String type) throws SQLException {
        String sqlType = validateSql(type); //A validated SQL kod to avoid SQL-injection and secure right format.
        List <Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName +
                " WHERE EXTRACT( " + sqlType + " FROM date) = ? ;";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, value);

            ResultSet resultSet = statement.executeQuery();

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
    public BigDecimal getSum (boolean isIncome) throws SQLException {
        String sql = "SELECT SUM(amount) FROM " +
                tableName + " WHERE is_income = ?";
        BigDecimal sum;

        try(PreparedStatement sumStatement = connection.prepareStatement(sql)) {   sumStatement.setBoolean(1, isIncome);
            ResultSet resultSet = sumStatement.executeQuery();
            resultSet.next();

             sum = resultSet.getBigDecimal(1);

             if (sum == null) {
                 System.out.println("Found null");
             }
    }
        return sum;
}
public String getCurrency (int id) throws SQLException {
        String sql = "SELECT currency FROM " +
                tableName + " WHERE id = ?";
        String currency;
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            currency = resultSet.getString("currency");

            if (!resultSet.next()) {
                System.out.println("No transaction with that id found");
            }
        }
        return currency;
}

}