package personalfinance.commands;

import personalfinance.models.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class RegisterTransactionCommand {
    LocalDate today = LocalDate.now();
    Scanner scan = new Scanner(System.in);
    Boolean isIncome;

    Transaction transactions;

    public RegisterTransactionCommand(Boolean isIncome) {
        this.isIncome = isIncome;
    }

    private Transaction registerTransaction(){
        System.out.println("Enter the amount:");
        BigDecimal amount = scan.nextBigDecimal();
        System.out.println("Enter the currency");
        String currency = scan.nextLine();

        Transaction transaction = new Transaction(amount,)


    }

    private void transaction() {
    }


}
