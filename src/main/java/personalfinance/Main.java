package personalfinance;

//import personalfinance.repositories.PostgresTransactionRepository;

import personalfinance.commands.DeleteTransactionCommand;
import personalfinance.commands.RegisterTransactionCommand;
import personalfinance.commands.SeeHistoryCommand;
import personalfinance.models.Transaction;
import personalfinance.repositories.PostgresTransactionRepository;
import personalfinance.services.TransactionService;

import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        RegisterTransactionCommand incomeRegCommand = new RegisterTransactionCommand(true);
        RegisterTransactionCommand expenseRegCommand = new RegisterTransactionCommand(false);
        DeleteTransactionCommand deleteTransactionCommand = new DeleteTransactionCommand();
        SeeHistoryCommand seeHistoryCommand = new SeeHistoryCommand();
        TransactionService service = new TransactionService();
        PostgresTransactionRepository repository = new PostgresTransactionRepository("jdbc:postgresql://localhost/personalfinance", "postgres", "mysecretpassword");


//        List<Transaction> transactions = repository.getAll();
//        System.out.println(transactions);
//
//        repository.deleteTransactionById(2);
//while (true) {
//    Scanner scan = new Scanner(System.in);
//    int choice = scan.nextInt();
//
//    switch (choice) {
//        case 1: incomeRegCommand.execute();
//        break;
//        case 2: expenseRegCommand.execute();
//        break;
//        case 3: deleteTransactionCommand.execute();
//        break;
//        case 4: seeHistoryCommand.execute();
//        break;
//        case 5:
//            return;
//    }
        incomeRegCommand.execute();
        expenseRegCommand.execute();
        deleteTransactionCommand.execute();
        seeHistoryCommand.execute();
        service.seeTransactionList();
    }
}



//        income.registerTransaction();
//        expense.registerTransaction();



//        try {
//            PostgresTransactionRepository transactionRepository = new PostgresTransactionRepository("jdbc:postgresql://localhost/personalfinance", "postgres", "mysecretpassword");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }



//        String connectionString = "jdbc:postgresql://localhost/personalfinance?user=postgres&password=mysecretpassword";
//        Connection connection;
//        try {
//            connection = DriverManager.getConnection(connectionString);
//            System.out.println("You're connected to the database!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        try {
//            Statement createTableStatement = connection.createStatement();
//            createTableStatement.execute("CREATE TABLE IF NOT EXISTS test33 (id SERIAL PRIMARY KEY, amount INT)");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Please register your income"); // Ändra till dobule
//        int income = scan.nextInt();
//
//        try {
//            PreparedStatement sqlquote = connection.prepareStatement("INSERT INTO test33 (amount) VALUES (?)");
//            sqlquote.setInt(1, income);
//            sqlquote.execute();
//            System.out.println("Registration succeeded");
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//        }








