import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner sc = new Scanner(System.in); // Scanner object input lene ke liye

        while (running) {
            System.out.println("===== Welcome to Rating Management System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // nextInt() ke baad ka new line ke liye

            if (choice == 3) {
                running = false; // Exit option chosen

            } else if (choice == 1) {
                // === Registration Process ===
                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter email: ");
                String email = sc.nextLine();

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                System.out.print("Enter role (user/admin): ");
                String role = sc.nextLine().toLowerCase();

                // UserService class ka method call kiya for registration
                UserService.registerUser(name, email, password, role);

            } else if (choice == 2) {
                System.out.print("Enter email: ");
                String email = sc.nextLine();

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                // Login attempt kar rahe hain
                User user = UserService.login(email, password);

                if (user != null) {
                    // Token check kar rahe hain security ke liye
                    if (user.getToken() == null) {
                        System.out.println("Token generation failed. Cannot access secure features.");
                        continue; // token na hone par wapas loop mein jao
                    }

                    System.out.println("Login successful as " + user.getRole().toUpperCase());
                    //System.out.println("Your access token: " + user.getToken()); // debugging ke liye

                    if (user.getRole().equals("admin")) {
                        // === Admin panel ===
                        while (true) {
                            System.out.println("\n===== ADMIN PANEL =====");
                            System.out.println("1. View Average Ratings Report");
                            System.out.println("2. Filter Users");
                            System.out.println("3. Logout");
                            System.out.print("Choose option: ");
                            int adminChoice = sc.nextInt();
                            sc.nextLine();

                            if (adminChoice == 1) {
                                AdminService.viewAverageRatings();
                            } else if (adminChoice == 2) {
                                AdminService.filterUsersByRating();
                            } else if (adminChoice == 3) {
                                System.out.println("Logged out.");
                                break;
                            } else {
                                System.out.println("Invalid choice.");
                            }
                        }

                    } else if (user.getRole().equals("user")) {
                        System.out.print("Do you want to rate a service? (yes/no): ");
                        String rate = sc.nextLine();

                        if (rate.equalsIgnoreCase("yes")) {
                            // Agar token available hai tabhi rating dene milega
                            if (user.getToken() != null) {
                                System.out.print("Ambiance (1-5): ");
                                int ambiance = sc.nextInt();

                                System.out.print("Food (1-5): ");
                                int food = sc.nextInt();

                                System.out.print("Service (1-5): ");
                                int service = sc.nextInt();

                                System.out.print("Cleanliness (1-5): ");
                                int cleanliness = sc.nextInt();

                                System.out.print("Drinks (1-5): ");
                                int drinks = sc.nextInt();
                                sc.nextLine();

                                // RatingService class ka method call
                                RatingService.addRating(user.getId(), ambiance, food, service, cleanliness, drinks);
                            } else {
                                System.out.println("Access denied. Token required."); // token mandatory hai
                            }
                        }
                    }

                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }

            } else {
                System.out.println("Invalid option selected.");
            }

        }

        // Program ke end mein scanner close kar diya
        sc.close();
    }
}
