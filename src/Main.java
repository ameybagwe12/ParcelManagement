import java.util.*;

import com.menu.Menu;
import com.model.User;

public class Main {
    // Arraylist to store users
    // loggedInUser to act as session variable
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        // Initial Choice
        int choice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("-------------------------------------------------------------");
            System.out.print("1.Register 2.Login 3.Display Users 4.Exit\nEnter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear invalid input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    // To registerUser Function below
                    registerUser(scanner);
                    break;
                case 2:
                    // To loginUser Function below
                    loginUser(scanner);
                    break;
                case 3:
                    // Displaying all users function below
                    displayUsers();
                    break;
                case 4:
                    System.out.println("Exiting !!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose between 1 and 4.");
            }
        } while (choice != 4);
    }

    // Display users (CASE 3)
    protected static void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users present!");
        } else {
            System.out.println("Registered Users:");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    // Register users (CASE 1)
    protected static void registerUser(Scanner scan) {
        System.out.println("Enter your details:");

        System.out.print("Name: ");
        String name = scan.nextLine();

        System.out.print("Email: ");
        String email = scan.nextLine();

        System.out.print("Mobile Number: ");
        String mobileNumber = scan.nextLine();

        System.out.print("Address: ");
        String address = scan.nextLine();

        System.out.print("Role(Officer/Customer): ");
        String role = scan.nextLine();

        System.out.print("User ID: ");
        String userId = scan.nextLine();

        // Check if User ID already exists
        if (isUserIdTaken(userId)) {
            System.out.println("User ID already exists. Please try a different one.");
            return;
        }

        System.out.print("Password: ");
        String password = scan.nextLine();

        System.out.print("Confirm Password: ");
        String cpassword = scan.nextLine();

        if (!password.equals(cpassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        // Creating new user
        users.add(new User(name, email, mobileNumber, address, userId, password, role));
        System.out.println("User registered successfully!");
    }

    // Login user (CASE 2)
    protected static void loginUser(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (isUserIdTaken(userId)) {
                if (user.getPassword().equals(password)) {
                    System.out.println("Login successful! Welcome, " + user.getName());
                    loggedInUser = user;

                    // Display menu based of role
                    Menu.displayMenu(scanner, loggedInUser); // MENU Package
                    return;
                } else {
                    System.out.println("Incorrect password.");
                }
                return;
            }
        }
        System.out.println("Invalid UserId or Password");
    }

    // MAPPER FUNCTION to map input userId with stored userId
    private static boolean isUserIdTaken(String userId) {
        return users.stream().anyMatch(user -> user.getUserId().equals(userId));
    }
}

