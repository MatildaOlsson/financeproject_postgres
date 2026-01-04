package personalfinance.commands;

import personalfinance.models.Transaction;
import personalfinance.services.TransactionService;

import java.math.BigDecimal;

public class ViewAccountBalanceCommand extends Command{

    TransactionService transactionService = new TransactionService();

    public ViewAccountBalanceCommand() {
        super("View account balance command");
    }

    @Override
    public void execute() {
        BigDecimal totalIncome = transactionService.getTransactionSum(true);
        BigDecimal totalExpense = transactionService.getTransactionSum(false);
        String currency = transactionService.getTransactionCurrency(1);

        BigDecimal accountBalance = totalIncome.subtract(totalExpense);
        System.out.println("Your current account balance: " + accountBalance + " " + currency);
    }

}
