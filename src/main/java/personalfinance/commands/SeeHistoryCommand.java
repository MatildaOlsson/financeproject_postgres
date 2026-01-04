package personalfinance.commands;

import personalfinance.services.TransactionService;

public class SeeHistoryCommand extends Command {
    TransactionService service = new TransactionService();

    public SeeHistoryCommand() {
        super("See history command");
    }

    @Override
    public void execute(){
        service.seeTransactionList();
    }
}
