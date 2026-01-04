package personalfinance.services;

import personalfinance.commands.RegisterTransactionCommand;
import personalfinance.models.Transaction;
import personalfinance.repositories.PostgresTransactionRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class TransactionService {

    private PostgresTransactionRepository transactionRepository;


    public TransactionService() {
        try {
            transactionRepository = new PostgresTransactionRepository("jdbc:postgresql://localhost/personalfinance", "postgres", "mysecretpassword");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTransaction(Transaction transaction) {
        try {transactionRepository.save(transaction);}
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void seeTransactionList() {
        try {
            List<Transaction> transactions =  transactionRepository.getAll();
            System.out.println(transactions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DeleteTransactionById(int id) {
        try {
            transactionRepository.deleteTransactionById(id); //BORDE kanaske vara "found transaction by id"
        }
        catch (SQLException e) {
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
    public String getTransactionCurrency (int id) {
        String currency = "";
        try{
            currency =transactionRepository.getCurrency(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currency;

    }
}


