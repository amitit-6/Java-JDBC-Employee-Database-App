import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeDBApp {
    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root";
    static final String PASSWORD = "";

    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("\nConnected to database.");

                int choice;
                do {

                    System.out.println("\n=== Employee DB Menu ===");
                    System.out.println("1. Add Employee");
                    System.out.println("2. View Employees");
                    System.out.println("3. Update Employee");
                    System.out.println("4. Delete Employee");
                    System.out.println("5. Exit");

                    System.out.print("\nEnter your choice: ");
                    choice = sc.nextInt();

                    switch(choice) {
                        case 1: 
				addEmployee();
				break;

                        case 2:
                            	viewEmployees();
				break;

                        case 3:
                            	updateEmployee();
				break;

                        case 4:
                            	deleteEmployee();
				break;

                        case 5:
                            	System.out.println("Exiting..");
				break;

                        default:
                            System.out.println("Invalid choice.");
                    }                 
                } while(choice != 5);

                con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addEmployee() throws SQLException{

        System.out.print("\nEnter ID: ");
        int id = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();   

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        String sql = "insert into employee values(?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setDouble(3, salary);
        pstmt.executeUpdate();

        System.out.println("\nEmployee added successfully.");
    }

    public static void viewEmployees() throws SQLException {

        String sql = "select * from employee";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        System.out.println("\n --- Employee List ---");
        while(rs.next()) {
            System.out.println("ID: "+ rs.getInt("id") + " | Name: " + rs.getString("name") + " | Salary: " + rs.getDouble("salary"));
        }
    }

    public static void updateEmployee() throws SQLException{

        System.out.print("\nEnter Employee ID to Update: ");
        int id = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Salary: ");
        double salary = sc.nextDouble();

        String sql = "update employee set name = ?, salary = ? where id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, name);
        pstmt.setDouble(2, salary);
	pstmt.setInt(3, id);  	
      
        int result = pstmt.executeUpdate();

        if (result > 0) {
            System.out.println("\nEmployee updated.");
        } else {
            System.out.println("\nEmployee not found.");
        }
    }

    public static void deleteEmployee() throws SQLException {

        System.out.print("\nEnter Employee ID to delete: ");
        int id = sc.nextInt();

        String sql = "delete from employee where id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);

        int result = pstmt.executeUpdate();

        if (result > 0) {
            System.out.println("\nEmployee deleted.");
        } else {
            System.out.println("\nEmployee not found.");
        }
    }
}




































/*
import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {
    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "root";           // change if needed
    static final String PASSWORD = "password";   // change if needed

    static Connection connection;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database.");

            int choice;
            do {
                System.out.println("\n=== Employee DB Menu ===");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> addEmployee();
                    case 2 -> viewEmployees();
                    case 3 -> updateEmployee();
                    case 4 -> deleteEmployee();
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice.");
                }
            } while (choice != 5);

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addEmployee() throws SQLException {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        String sql = "INSERT INTO employee VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setDouble(3, salary);
        stmt.executeUpdate();

        System.out.println("Employee added successfully.");
    }

    public static void viewEmployees() throws SQLException {
        String sql = "SELECT * FROM employee";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Employee List ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                               " | Name: " + rs.getString("name") +
                               " | Salary: ₹" + rs.getDouble("salary"));
        }
    }

    public static void updateEmployee() throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Salary: ");
        double salary = scanner.nextDouble();

        String sql = "UPDATE employee SET name = ?, salary = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setDouble(2, salary);
        stmt.setInt(3, id);
        int rows = stmt.executeUpdate();

        if (rows > 0) {
            System.out.println("Employee updated.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void deleteEmployee() throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM employee WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        int rows = stmt.executeUpdate();

        if (rows > 0) {
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}

*/