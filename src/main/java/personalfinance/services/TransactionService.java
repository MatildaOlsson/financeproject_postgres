package personalfinance.services;

import personalfinance.commands.RegisterTransactionCommand;
import personalfinance.models.Transaction;
import personalfinance.repositories.PostgresTransactionRepository;

import java.sql.SQLException;

public class TransactionService {

    private PostgresTransactionRepository transactionRepository;
    private RegisterTransactionCommand income = new RegisterTransactionCommand(true);

    public TransactionService() {
        try {
            transactionRepository = new PostgresTransactionRepository("jdbc:postgresql://localhost/personalfinance", "postgres", "mysecretpassword");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTransaction() {
        Transaction tran = income.registerTransaction();
        try {transactionRepository.save(tran);}
        catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}


