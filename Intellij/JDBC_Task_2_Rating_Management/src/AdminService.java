import java.sql.*;
import java.util.*;

public class AdminService {

    public static void viewAverageRatings() {
        String query = "SELECT " + "AVG(ambiance) AS avg_ambiance, " + "AVG(food) AS avg_food, " + "AVG(service) AS avg_service, " + "AVG(cleanliness) AS avg_cleanliness, " + "AVG(drinks) AS avg_drinks, " + "AVG((ambiance + food + service + cleanliness + drinks)/5) AS overall_avg " + "FROM ratings";

        //pehle connection banaya, then statememt banai, then query pass kari
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            //next() moves the pointer to next row 1->2, 2->3...
            if (rs.next()) {
                System.out.println("Average Ratings Report:");
                System.out.println("Ambiance: " + rs.getDouble("avg_ambiance"));
                System.out.println("Food: " + rs.getDouble("avg_food"));
                System.out.println("Service: " + rs.getDouble("avg_service"));
                System.out.println("Cleanliness: " + rs.getDouble("avg_cleanliness"));
                System.out.println("Drinks: " + rs.getDouble("avg_drinks"));
                System.out.println("Overall Average: " + rs.getDouble("overall_avg"));
            }
        } catch (SQLException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }

    public static void filterUsersByRating() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Filter by which rating category?");
        System.out.println("1. Ambiance");
        System.out.println("2. Food");
        System.out.println("3. Service");
        System.out.println("4. Cleanliness");
        System.out.println("5. Drinks");
        System.out.print("Enter your choice (1-5): ");
        int choice = sc.nextInt();

        String column = null;

        switch (choice) {
            case 1:
                column = "ambiance";
                break;
            case 2:
                column = "food";
                break;
            case 3:
                column = "service";
                break;
            case 4:
                column = "cleanliness";
                break;
            case 5:
                column = "drinks";
                break;
            default:
                System.out.println("Invalid choice. Returning to admin panel.");
                return;
        }

        System.out.print("Enter rating to filter by (1-5): ");
        int ratingValue = sc.nextInt();

        if (ratingValue < 1 || ratingValue > 5) {
            System.out.println("Invalid rating value. Must be between 1 and 5.");
            return;
        }

        String query = "SELECT users.name, users.email, r." + column + " FROM users JOIN ratings r ON users.id = r.user_id " + "WHERE r." + column + " = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ratingValue);

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nFiltered Users by " + column.toUpperCase() + " = " + ratingValue);
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Name: " + rs.getString("name") + ", Email: " + rs.getString("email") + ", " + column.substring(0, 1).toUpperCase() + column.substring(1) + ": " + rs.getInt(column));
            }
            if (!found) {
                System.out.println("No users found with " + column + " rating = " + ratingValue);
            }

        } catch (SQLException e) {
            System.out.println("Error filtering users: " + e.getMessage());
        }
    }
}
