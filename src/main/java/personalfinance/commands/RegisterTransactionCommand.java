package personalfinance.commands;

import personalfinance.models.Transaction;
import personalfinance.services.TransactionService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Scanner;

public class RegisterTransactionCommand extends Command{
    TransactionService transactionService = new TransactionService();

    Scanner scan = new Scanner(System.in);
    Boolean isIncome;

    public RegisterTransactionCommand(Boolean isIncome) {
        super("Register transaction command");
        this.isIncome = isIncome;
    }

    @Override
    public void execute() {
        System.out.println("Enter the amount:");
        BigDecimal amount = scan.nextBigDecimal();
        scan.nextLine(); // Clear the scanner

        System.out.println("Enter the currency");
        String currency = scan.nextLine();
        System.out.println("Enter transaction information:");
        String transactionInfo = scan.nextLine();

        LocalDate todayDate = LocalDate.now();
        int week = todayDate.get(WeekFields.ISO.weekOfWeekBasedYear());

        Date date = java.sql.Date.valueOf(todayDate);

        Transaction transaction = new Transaction(0, amount, isIncome, currency, date, week, transactionInfo);
        System.out.println("Your transaction will be saved on date: " + todayDate);

        transactionService.saveTransaction(transaction);
    }





}
