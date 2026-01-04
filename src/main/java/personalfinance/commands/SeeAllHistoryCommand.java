package personalfinance.commands;

import personalfinance.services.TransactionService;

public class SeeAllHistoryCommand extends Command {
    TransactionService service = new TransactionService();

    public SeeAllHistoryCommand() {
        super("See all history command");
    }

    @Override
    public void execute(){
        service.seeAllTransaction();
    }
}
