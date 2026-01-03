package personalfinance.commands;

import personalfinance.models.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Scanner;

public class RegisterTransactionCommand extends Command{
    LocalDate today = LocalDate.now();
    int week = today.get(WeekFields.ISO.weekOfWeekBasedYear());
    Scanner scan = new Scanner(System.in);
    Boolean isIncome;

    public RegisterTransactionCommand(Boolean isIncome) {
        this.isIncome = isIncome;
    }


    public Transaction registerTransaction() {
        System.out.println("Enter the amount:");
        BigDecimal amount = scan.nextBigDecimal();
        scan.nextLine();
        System.out.println("Enter the currency");
        String currency = scan.nextLine();


        Transaction transaction = new Transaction(amount, isIncome, currency, today, week);
        System.out.println("Your transaction will be saved on date: " + today);
        return transaction;
    }

}
