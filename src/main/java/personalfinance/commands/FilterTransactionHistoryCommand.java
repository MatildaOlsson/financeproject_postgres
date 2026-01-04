package personalfinance.commands;

import personalfinance.models.Transaction;
import personalfinance.services.TransactionService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FilterTransactionHistoryCommand extends Command {
    TransactionService transactionService = new TransactionService();

    public FilterTransactionHistoryCommand() {
        super("Filter transaction command");
    }

    @Override
    public void execute() {
        Scanner scan = new Scanner(System.in);
        String type = "";
        int value = 0;
        System.out.println("Write year, month or day to filter your transactions");
        try {
            type = scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
        System.out.println("Type the number of your " + type);
        try {
            value = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }

        List<Transaction> transactionList = transactionService.getFiltredList(type, value);
        System.out.println("Your transactions: ");
        for (Transaction t : transactionList) {
            System.out.println("Transaction: " + t);
        }
    }
}
