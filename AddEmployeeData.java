import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddEmployeeData {
    public static void main(String[] args) {
        try {
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/table_localization_demonstration", "root", "Test12");

            // Getting user input for language selection
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select language:");
            System.out.println("1. English");
            System.out.println("2. Farsi");
            System.out.println("3. Japanese");
            int choice = scanner.nextInt();

            // Getting employee details from the user
            System.out.println("Enter employee ID:");
            int empId = scanner.nextInt();
            System.out.println("Enter employee name:");
            String name = scanner.next();
            System.out.println("Enter employee age:");
            int age = scanner.nextInt();
            System.out.println("Enter employee salary:");
            double salary = scanner.nextDouble();

            // Prepare the SQL statement based on the selected language
            String sql;
            switch (choice) {
                case 1:
                    sql = "INSERT INTO employees_en (emp_id, name, age, salary) VALUES (?, ?, ?, ?)";
                    break;
                case 2:
                    sql = "INSERT INTO employees_fa (emp_id, name, age, salary) VALUES (?, ?, ?, ?)";
                    break;
                case 3:
                    sql = "INSERT INTO employees_ja (emp_id, name, age, salary) VALUES (?, ?, ?, ?)";
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }

            // Creating a PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, empId);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setDouble(4, salary);

            // Executing the statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee data inserted successfully!");
            } else {
                System.out.println("Failed to insert employee data.");
            }

            // Closing resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
