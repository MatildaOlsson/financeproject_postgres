package personalfinance;

import personalfinance.commands.*;
import personalfinance.models.Transaction;
import personalfinance.services.CommandService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    CommandService terminalCommandService = new CommandService();
    Scanner scan = new Scanner(System.in);

    ArrayList<Transaction> transactions = new ArrayList<>();
    ArrayList<Command> commandoList = terminalCommandService.getListOfCommands();

    public void start() {

        terminalCommandService.registerCommand(new RegisterTransactionCommand(true, "income"));
        terminalCommandService.registerCommand(new RegisterTransactionCommand(false, "expense"));
        terminalCommandService.registerCommand(new DeleteTransactionCommand());
        terminalCommandService.registerCommand(new ViewAccountBalanceCommand());
        terminalCommandService.registerCommand(new SeeAllHistoryCommand());
        terminalCommandService.registerCommand(new FilterTransactionHistoryCommand());

        while (true) {
            System.out.println("====YOUR PERSONAL-FINANCE-APPLICATION====");
            System.out.println("Choice one of following commands:");
            //Kommandomenyn printas ut
            int i = 1;
            for (Command c : commandoList) {
                System.out.println(i + ". " + c.getName());
                i += 1;
            }
            System.out.println("0. Quit application");

            try {
                int choice = scan.nextInt();

                if (choice == 0) return;
                else {
                    int index = choice - 1;
                    terminalCommandService.executeCommand(index, commandoList);
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input. Try again using a number");
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
    }

}
