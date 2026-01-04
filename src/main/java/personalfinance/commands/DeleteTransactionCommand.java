package personalfinance.commands;

import personalfinance.services.TransactionService;

import java.util.Scanner;

public class DeleteTransactionCommand extends Command {

    TransactionService service = new TransactionService();

    public DeleteTransactionCommand() {
        super("Delete transaction command");

    }
    @Override
    public void execute() {
        service.seeAllTransaction();
        Scanner scan = new Scanner(System.in);
        System.out.println("Type id for the transaction you want to delete:");
        int id = scan.nextInt();
        scan.nextLine();

        service.DeleteTransactionById(id);
        System.out.println("Transaction id: " + id + " has been deleted");
    }
}
