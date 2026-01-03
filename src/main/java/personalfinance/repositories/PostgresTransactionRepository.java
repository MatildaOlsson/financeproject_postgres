//package personalfinance.repositories;
//
//import personalfinance.models.Transactions;
//
//import java.sql.*;
//
//
//public class PostgresTransactionRepository {
//
//    private Connection connection;
//    String tableName = "transactionTable";
//
//    public PostgresTransactionRepository(String url, String user, String password) throws SQLException {
//        connection = DriverManager.getConnection(url, user, password);
//
//        try (
//            Statement createTransactionTable = connection.createStatement()) {
//            createTransactionTable.execute("CREATE TABLE IF NOT EXISTS " + tableName + " (" +
//                    "id SERIAL PRIMARY KEY," +
//                    "amount NUMERIC(10,2) NOT NULL," +
//                    "currency VARCHAR(15)," +
//                    "is_income BOOLEAN NOT NULL," +
//                    "date DATE NOT NULL," +
//                    "transaction_info VARCHAR(25))");
//        }
//
//    }
//
//    public void save (Transactions transaction) throws Exception {
//        String sql = "INSERT INTO " + tableName + "(amount, currency, is_income, date, transaction_info) VALUES (?,?,?,?,?)";
//
//        try (PreparedStatement saveStatement = connection.prepareStatement(sql)) {
//            saveStatement.setBigDecimal(1, transaction.getAmount());
//            saveStatement.setString(2, transaction.getCurrency());
//            saveStatement.setBoolean(3,transaction.getIsIncome());
//            saveStatement.setDate(4, transaction.);
//        }
//    }
//}