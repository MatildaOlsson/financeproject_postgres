package personalfinance.services;

import personalfinance.commands.RegisterTransactionCommand;
import personalfinance.models.Transaction;
import personalfinance.repositories.PostgresTransactionRepository;

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
            transactionRepository.deleteTransactionById(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


