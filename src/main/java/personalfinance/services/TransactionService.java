package personalfinance.services;

import personalfinance.commands.RegisterTransactionCommand;
import personalfinance.models.Transaction;
import personalfinance.repositories.PostgresTransactionRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private PostgresTransactionRepository transactionRepository;


    public TransactionService() {
        try {
            String url = "jdbc:postgresql://localhost/personalfinance";
            String userName = "postgres";
            String password = "password";
            transactionRepository = new PostgresTransactionRepository(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTransaction(Transaction transaction) {
        try {
            transactionRepository.save(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seeAllTransaction() {
        try {
            List<Transaction> transactions = transactionRepository.getAll();
            System.out.println("Your transactions: ");
            for (Transaction t : transactions) {
                System.out.println("Transaction: " + t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteTransactionById(int id) {
        try {
            transactionRepository.deleteTransactionById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getTransactionSum(boolean isIncome) {
        BigDecimal sum = null;
        try {
            sum = transactionRepository.getSum(isIncome);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public String getTransactionCurrency(int id) {
        String currency = "";
        try {
            currency = transactionRepository.getCurrency(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currency;

    }

    public List<Transaction> getFiltredList(String type, int value) {
        List<Transaction> transactionList = new ArrayList<>();
        try {
            transactionList = transactionRepository.filterByYearMonthOrDay(value, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }
}




