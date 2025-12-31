package personalfinance;

import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String connectionString = "jdbc:postgresql://localhost/personalfinance?user=postgres&password=mysecretpassword";
        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionString);
            System.out.println("You're connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            Statement createTableStatement = connection.createStatement();
            createTableStatement.execute("CREATE TABLE IF NOT EXISTS test33 (id SERIAL PRIMARY KEY, amount INT)");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Please register your income"); // Ändra till dobule
        int income = scan.nextInt();

        try {
            PreparedStatement sqlquote = connection.prepareStatement("INSERT INTO test33 (amount) VALUES (?)");
            sqlquote.setInt(1, income);
            sqlquote.execute();
            System.out.println("Registration succeeded");
        } catch (
                SQLException e) {
            e.printStackTrace();
        }








    }
}