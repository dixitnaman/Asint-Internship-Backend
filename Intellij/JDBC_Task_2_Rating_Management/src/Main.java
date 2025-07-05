import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        while (running) {
            System.out.println("===== Welcome to Rating Management System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 3) {
                running = false;
            } else if (choice == 1) {
                // Registration
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                System.out.print("Enter role (user/admin): ");
                String role = sc.nextLine().toLowerCase();

                UserService.registerUser(name, email, password, role);

            } else if (choice == 2) {
                // Login
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();

                User user = UserService.login(email, password);
                if (user != null) {
                    System.out.println("Login successful as " + user.getRole().toUpperCase());

                    if (user.getRole().equals("admin")) {
                        // Admin panel
                        while (true) {
                            System.out.println("\n===== ADMIN PANEL =====");
                            System.out.println("1. View Average Ratings Report");
                            System.out.println("2. Filter Users");
                            System.out.println("3. Logout");
                            System.out.print("Choose option: ");
                            int adminChoice = sc.nextInt();

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
                        // User rating
                        System.out.print("Do you want to rate a service? (yes/no): ");
                        String rate = sc.nextLine();

                        if (rate.equalsIgnoreCase("yes")) {

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

                            RatingService.addRating(user.getId(), ambiance, food, service, cleanliness, drinks);
                        }
                    }

                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }

            } else {
                System.out.println("Invalid option selected.");
            }

        }
        sc.close();
    }
}